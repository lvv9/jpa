package me.liuweiqiang.hibernate;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

@RepositoryDefinition(domainClass = RequestStatusChangeEvent.class, idClass = Long.class)
public interface EventRepository {

    Optional<RequestStatusChangeEvent> findById(Long id);

    RequestStatusChangeEvent save(RequestStatusChangeEvent entity);

    Iterable<RequestStatusChangeEvent> saveAll(Iterable<RequestStatusChangeEvent> entities);
}
