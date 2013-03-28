package me.chaseoes.supercraftbrothers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Schedulers {

    private static final Schedulers instance = new Schedulers();
    private SuperCraftBrothers plugin;
    private HashMap<String, Integer> lobbyCountdowns = new HashMap<String, Integer>();

    private Schedulers() {
    }

    public static Schedulers getInstance() {
        return instance;
    }

    public void setup(SuperCraftBrothers plugin) {
        this.plugin = plugin;
    }

    public void startLobbyCountdown(final SCBGame game) {
        if (lobbyCountdowns.containsKey(game.getName())) {
            return;
        }
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {

            int i = 30;

            @Override
            public void run() {
                if (i > 0) {
                    if (i % 10 == 0 || i < 6) {
                        game.broadcast("Game starting in " + i + " second" + (i == 1 ? "" : "s"));
                    }
                    i--;
                } else {
                    stopLobbyCountdown(game);
                }
            }
        }, 0L, 20L);

    }

    public void stopLobbyCountdown(SCBGame game) {
        if (lobbyCountdowns.containsKey(game.getName())) {
            Bukkit.getScheduler().cancelTask(lobbyCountdowns.get(game.getName()));
            lobbyCountdowns.remove(game.getName());
        }
    }
}
