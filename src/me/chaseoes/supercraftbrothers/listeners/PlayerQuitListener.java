package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGame;
import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        CraftBrother bro = SCBGameManager.getInstance().getCraftBrother(event.getPlayer());
        if (bro != null) {
            SCBGame game = bro.getCurrentGame();
            if (game.isInGame()) {
                game.leaveGame(event.getPlayer());
            } else {
                game.leaveLobby(event.getPlayer());
            }
        }
    }
}
