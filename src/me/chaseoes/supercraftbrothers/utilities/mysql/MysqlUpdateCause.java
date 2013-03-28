package me.chaseoes.supercraftbrothers.utilities.mysql;

public final class MySQLUpdateCause {

    // Constants that declare changeable row names
    public static final MySQLUpdateCause MONEY = new MySQLUpdateCause("money");

    private String colName;

    private MySQLUpdateCause(String colName) {
        this.colName = colName;
    }

    public String getColName() {
        return colName;
    }
}
