package me.liuweiqiang.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "request",
        indexes = @Index(name = "uidx_req", columnList = "consumer, request_id", unique = true))
@AttributeOverrides({
        @AttributeOverride(name = "request.invoker", column = @Column(name = "consumer", nullable = false, length = 10)),
        @AttributeOverride(name = "request.id", column = @Column(name = "request_id", nullable = false, length = 45)),
        @AttributeOverride(name = "requestOrigin.invoker", column = @Column(name = "consumer_origin", length = 10)),
        @AttributeOverride(name = "requestOrigin.id", column = @Column(name = "request_id_origin", length = 45))
})
@NamedQueries({@NamedQuery(name = "Request.findByRequestId",
        query = "select r from Request r where request_id = :requestId order by consumer asc, request_id asc")})
public class Request {

    private static final Logger logger = LoggerFactory.getLogger(Request.class);

    @EmbeddedId
    protected InvokerRequestId request;

    @Embedded
    protected InvokerRequestId requestOrigin;

    @Convert(converter = RequestStatusConvertor.class)
    @Column(name = "status", length = 4)
    protected Status status;

    @Version
    protected Integer version;

    @Transient
    protected Collection<Object> events = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request1 = (Request) o;
        return request.equals(request1.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request);
    }

    public void changeStatus() {
        status = Arrays.stream(Status.values()).filter(st -> !st.equals(status)).findFirst().get();
        RequestStatusChangeEvent event = new RequestStatusChangeEvent(request, EventStatus.INITIAL);
        events.add(event);
    }

    @DomainEvents
    public Collection<Object> domainEvents() {
        logger.info("events");
        return events;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        logger.info("clear");
        events = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Request{" +
                "request=" + request +
                ", requestOrigin=" + requestOrigin +
                ", status=" + status +
                ", version=" + version +
                '}';
    }
}
