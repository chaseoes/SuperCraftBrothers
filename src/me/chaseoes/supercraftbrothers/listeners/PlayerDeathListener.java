package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGameManager;
import me.chaseoes.supercraftbrothers.SuperCraftBrothers;

import me.chaseoes.supercraftbrothers.events.SCBDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    public void onPlayerDeath(PlayerDeathEvent event) {
        if (SCBGameManager.getInstance().isInGame(event.getEntity().getName())) {
            CraftBrother bro1 = SCBGameManager.getInstance().getCraftBrother(event.getEntity());
            CraftBrother bro2 = SCBGameManager.getInstance().getCraftBrother(bro1.getLastDamagedBy());
            Bukkit.getPluginManager().callEvent(new SCBDeathEvent(bro1, bro2, bro1.getCurrentGame()));
            bro1.setLastDamagedBy(null);
            //event.setDeathMessage(ChatColor.DARK_AQUA + event.getDeathMessage());
            //SuperCraftBrothers.getInstance().getServer().broadcastMessage(ChatColor.DARK_AQUA + event.getEntity().getName() + " has " + SCBGameManager.getInstance().getCraftBrother(event.getEntity()).getLivesLeft() + " lives left.");
        }
    }
    
}
