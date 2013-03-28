package me.chaseoes.supercraftbrothers.utilities.mysql;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MysqlConsumer implements Runnable {

    private static final MysqlConsumer instance = new MysqlConsumer();
    private ConcurrentLinkedQueue<MysqlUpdateRequest> dataQueue = new ConcurrentLinkedQueue<MysqlUpdateRequest>();
    private Mysql mysql;

    private MysqlConsumer() { }

    public void setup(Mysql mysql) {
        this.mysql = mysql;
    }

    public static MysqlConsumer getInstance() {
        return  instance;
    }

    public void addRequest(MysqlUpdateRequest req) {
        dataQueue.add(req);
    }

    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        MysqlUpdateRequest req;
        while ((req = dataQueue.poll()) != null) {
            builder.append("UPDATE players SET ").append(req.getCause().getColName()).append("='").append(req.getVal()).append("' WHERE username='").append(req.getName()).append("';");
            mysql.execUpdate(builder.toString());
            builder = new StringBuilder();
        }
    }
}
