package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGame;
import me.chaseoes.supercraftbrothers.events.SCBDeathEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SCBDeathListener implements Listener {

    @EventHandler
    public void onSCBDeath(SCBDeathEvent event) {
        SCBGame game = event.getGame();
        CraftBrother killed = event.getKilled();
        CraftBrother killer = event.getKiller();
        killed.setLivesLeft(-1);
        if (killer != null) {
            killer.setKills(-1);
            game.broadcast(ChatColor.DARK_AQUA + killed.getPlayer().getName() + " was killed by " + killer.getPlayer().getName());
        } else {
            game.broadcast(ChatColor.DARK_AQUA + killed.getPlayer().getName() + " killed himself");
        }
        game.broadcast(ChatColor.DARK_AQUA + killed.getPlayer().getName() + " has " + killed.getLivesLeft() + " lives left.");
        if (killed.getLivesLeft() <= 0) {
            game.playerEliminated(killed);
        }
    }
}
