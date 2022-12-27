package me.liuweiqiang.hibernate;

public enum Status {

    SUCCESS("0000", "成功"),
    FAIL("0001", "失败");

    private final String code;
    private final String msg;

    Status(String code, String msg) {
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
