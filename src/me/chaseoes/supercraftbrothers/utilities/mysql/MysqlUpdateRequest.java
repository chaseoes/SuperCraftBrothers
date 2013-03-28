package me.chaseoes.supercraftbrothers.utilities.mysql;

public class MySQLUpdateRequest {

    private final String name;
    private final MySQLUpdateCause cause;
    private final Object val;

    public MySQLUpdateRequest(String name, MySQLUpdateCause cause, Object val) {
        this.name = name;
        this.cause = cause;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public MySQLUpdateCause getCause() {
        return cause;
    }

    public Object getVal() {
        return val;
    }
}
