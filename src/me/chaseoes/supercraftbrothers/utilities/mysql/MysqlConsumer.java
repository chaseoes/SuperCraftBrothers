package me.chaseoes.supercraftbrothers.utilities.mysql;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MySQLConsumer implements Runnable {

    private static final MySQLConsumer instance = new MySQLConsumer();
    private ConcurrentLinkedQueue<MySQLUpdateRequest> dataQueue = new ConcurrentLinkedQueue<MySQLUpdateRequest>();
    private MySQL mysql;

    private MySQLConsumer() {
    }

    public void setup(MySQL mysql) {
        this.mysql = mysql;
    }

    public static MySQLConsumer getInstance() {
        return instance;
    }

    public void addRequest(MySQLUpdateRequest req) {
        dataQueue.add(req);
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        MySQLUpdateRequest req;
        while ((req = dataQueue.poll()) != null) {
            builder.append("UPDATE players SET ").append(req.getCause().getColName()).append("='").append(req.getVal()).append("' WHERE username='").append(req.getName()).append("';");
            mysql.execUpdate(builder.toString());
            builder = new StringBuilder();
        }
    }
}
