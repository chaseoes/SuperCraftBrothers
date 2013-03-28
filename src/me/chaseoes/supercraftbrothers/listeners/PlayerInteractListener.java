package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGame;
import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    public void onPlayerInteract(PlayerInteractEvent event) {
        for (SCBGame game : SCBGameManager.getInstance().getAllGames()) {

        }
    }

}
