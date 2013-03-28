package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    public void onPlayerQuit(PlayerQuitEvent event) {
        SCBGameManager.getInstance().playerLogout(event.getPlayer().getName());
    }
}
