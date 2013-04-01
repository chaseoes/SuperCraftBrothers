package me.chaseoes.supercraftbrothers.utilities.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import me.chaseoes.supercraftbrothers.SuperCraftBrothers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Mysql {

    private static final Mysql instance = new Mysql();
    private SuperCraftBrothers plugin;
    private Connection conn;
    private boolean connected = false;
    private BukkitTask consumerTask;
    private BukkitTask connTask;

    public static Mysql getInstance() {
        return instance;
    }

    public void setup(final SuperCraftBrothers plugin) {
        this.plugin = plugin;
        final String username = plugin.getConfig().getString("mysql.username");
        final String password = plugin.getConfig().getString("mysql.password");
        final String host = plugin.getConfig().getString("mysql.hostname");
        final int port = plugin.getConfig().getInt("mysql.port");
        final String db = plugin.getConfig().getString("mysql.database");

        connTask = this.plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {

                String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

                try {
                    conn = DriverManager.getConnection(url, username, password);
                    Statement st = conn.createStatement();
                    String table = "CREATE TABLE IF NOT EXISTS players(id INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(id), username VARCHAR(16) UNIQUE, money INT)";
                    st.executeUpdate(table);
                    connected = true;
                } catch (Exception e) {
                    connected = false;
                    plugin.getLogger().log(Level.SEVERE, "Could not connect to database! Verify your database details in the configuration are correct.");
                    plugin.getServer().getPluginManager().disablePlugin(plugin);
                }
                if (connected) {
                    consumerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, MysqlConsumer.getInstance(), 1L, 5L);
                }
            }
        });
    }

    public void close() {
        if (consumerTask != null) {
            consumerTask.cancel();
        }
        if (connTask != null) {
            connTask.cancel();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {

        }
    }

    public ResultSet getResultSet(String statement) {
        if (!connected) {
            return null;
        }
        ResultSet result = null;
        try {
            Statement st;
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = st.executeQuery(statement);
        } catch (SQLException e) {

        }
        return result;
    }

    public void execUpdate(String statement) {
        if (!connected) {
            return;
        }
        Statement st;
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate(statement);
        } catch (SQLException e) {

        }
    }
}
