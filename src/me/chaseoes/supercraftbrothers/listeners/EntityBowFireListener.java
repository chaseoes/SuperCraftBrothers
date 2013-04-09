package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityBowFireListener implements Listener {

    @EventHandler
    public void onBowFire(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (SCBGameManager.getInstance().isInGame(((Player) event.getEntity()).getName())) {
                if (SCBGameManager.getInstance().getCraftBrother(((Player) event.getEntity()).getName()).isInLobby()) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
