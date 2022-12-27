package me.liuweiqiang.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Collection;

@Component
public class EventListener {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RequestRepository repository;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(RequestStatusChangeEvent event) {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Request> page = repository.findByRequestId("testReqId0", pageable);
        logger.info("handled {}, {}", page.toString(), page.get().map(Object::toString).reduce(String::concat).orElse(""));
        page = repository.testFind("testReqId0", pageable);
        logger.info("handled {}, {}", page.toString(), page.get().map(Object::toString).reduce(String::concat).orElse(""));
        Page<IRequest> pageId = repository.findRequestId("testReqId0", pageable);
        logger.info(pageId.getContent().get(0).getRequest().getId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional
    public void save(RequestStatusChangeEvent event) {
        logger.info("save {}", event.toString());
        event.sent();
        eventRepository.save(event);
//        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void start(){
        Request request = repository.findById(new InvokerRequestId("test", "testReqId0")).get();
        logger.info(request.toString());
        request.changeStatus();
        Collection<Object> domainEvents = request.domainEvents();
        repository.save(request); // no need for jpa, needed by spring data
        eventRepository.saveAll(() -> domainEvents.stream().map(event -> (RequestStatusChangeEvent) event).iterator());
        repository.test();
    }
}
