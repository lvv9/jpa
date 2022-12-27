package me.liuweiqiang.hibernate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

@RepositoryDefinition(domainClass = Request.class, idClass = InvokerRequestId.class)
public interface RequestRepository extends RequestCustomizedRepository {

    Optional<Request> findById(InvokerRequestId id);

    Request save(Request entity);

    @Lock(LockModeType.NONE)
    Page<Request> findByRequestId(String requestId, Pageable pageable);

    @Query("select r from Request r where request_id = :requestId order by consumer desc, request_id desc")
    Page<Request> testFind(@Param("requestId") String id, Pageable pageable);

    @Query("select r from Request r where request_id = :requestId order by consumer desc, request_id desc")
    Page<IRequest> findRequestId(@Param("requestId") String id, Pageable pageable);
}
