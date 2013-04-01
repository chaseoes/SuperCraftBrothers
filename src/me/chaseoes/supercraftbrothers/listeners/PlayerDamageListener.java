package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGame;
import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (SCBGameManager.getInstance().isInGame(player.getName())) {
                CraftBrother bro = SCBGameManager.getInstance().getCraftBrother(player.getName());
                SCBGame game = bro.getCurrentGame();
                if (game.isInLobby()) {
                    event.setCancelled(true);
                }
                if (event instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) event;
                    if (edbee.getDamager() instanceof Player) {
                        Player damager = (Player) edbee.getDamager();
                        CraftBrother cbDamager = SCBGameManager.getInstance().getCraftBrother(damager.getName());
                        if (cbDamager.getCurrentGame().getName().equalsIgnoreCase(game.getName()) && game.isInGame()) {
                            bro.setLastDamagedBy(cbDamager.getPlayer().getName());
                        } else {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

}
