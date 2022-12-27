package me.liuweiqiang.hibernate;

public enum EventStatus {

    INITIAL("10", "初始"),
    SENT("20", "已投递");

    private final String code;
    private final String msg;

    EventStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
