package me.chaseoes.supercraftbrothers.utilities.mysql;

public final class MysqlUpdateCause {

    //Constants that declare changeable row names
    public static final MysqlUpdateCause MONEY = new MysqlUpdateCause("money");

    private String colName;

    private MysqlUpdateCause(String colName) {
        this.colName = colName;
    }

    public String getColName() {
        return colName;
    }
}
