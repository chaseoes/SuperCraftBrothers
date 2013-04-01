package me.chaseoes.supercraftbrothers.utilities.mysql;

public class MysqlUpdateRequest {

    private final String name;
    private final MysqlUpdateCause cause;
    private final Object val;

    public MysqlUpdateRequest(String name, MysqlUpdateCause cause, Object val) {
        this.name = name;
        this.cause = cause;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public MysqlUpdateCause getCause() {
        return cause;
    }

    public Object getVal() {
        return val;
    }
}
