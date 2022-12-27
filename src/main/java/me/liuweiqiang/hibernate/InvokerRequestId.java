package me.liuweiqiang.hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvokerRequestId implements Serializable {

    private String invoker;

    private String id;

    public InvokerRequestId() {
    }

    public InvokerRequestId(String invoker, String id) {
        this.invoker = invoker;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvokerRequestId that = (InvokerRequestId) o;
        return invoker.equals(that.invoker) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoker, id);
    }

    @Override
    public String toString() {
        return "InvokerRequest{" +
                "invoker='" + invoker + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
