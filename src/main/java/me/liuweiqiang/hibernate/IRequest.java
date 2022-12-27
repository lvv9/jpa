package me.liuweiqiang.hibernate;

public interface IRequest {

    RequestId getRequest();

    interface RequestId {

        String getId();
    }
}
