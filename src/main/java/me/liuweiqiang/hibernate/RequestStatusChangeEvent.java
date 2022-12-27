package me.liuweiqiang.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "event")
@AttributeOverrides({
        @AttributeOverride(name = "request.invoker", column = @Column(name = "consumer", nullable = false, length = 10)),
        @AttributeOverride(name = "request.id", column = @Column(name = "request_id", nullable = false, length = 45))
})
public class RequestStatusChangeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private InvokerRequestId request;

    @Convert(converter = EventStatusConvertor.class)
    @Column(name = "status", length = 2)
    private EventStatus status;

    public RequestStatusChangeEvent() {
    }

    public RequestStatusChangeEvent(InvokerRequestId request, EventStatus status) {
        this.request = request;
        this.status = status;
    }

    public void sent() {
        this.status = EventStatus.SENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatusChangeEvent that = (RequestStatusChangeEvent) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RequestStatusChangeEvent{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
