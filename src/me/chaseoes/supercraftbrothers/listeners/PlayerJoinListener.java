package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    public void onPlayerJoin(PlayerJoinEvent event) {
        SCBGameManager.getInstance().getCraftBrother(event.getPlayer().getName());
    }
}
