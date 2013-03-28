package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import me.chaseoes.supercraftbrothers.SuperCraftBrothers;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    public void onPlayerDeath(PlayerDeathEvent event) {
        if (SCBGameManager.getInstance().isInGame(event.getEntity().getName())) {
            event.setDeathMessage(ChatColor.DARK_AQUA + event.getDeathMessage());
            SuperCraftBrothers.getInstance().getServer().broadcastMessage(ChatColor.DARK_AQUA + event.getEntity().getName() + " has " + SCBGameManager.getInstance().getCraftBrother(event.getEntity()).getLivesLeft() + " lives left.");
        }
    }
    
}
