package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import me.chaseoes.supercraftbrothers.SCBMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[SCB]")) {
            Player player = event.getPlayer();
            if (player.hasPermission("scb.create")) {
                if (event.getLine(2).equalsIgnoreCase("")) {
                    String map = event.getLine(1);
                    SCBMap m = SCBGameManager.getInstance().getGame(map).getMap();
                    m.setLobbySign(event.getBlock().getLocation());
                    player.sendMessage(ChatColor.DARK_GREEN + "Successfully created HUB sign for the map " + m.getGame().getName() + ".");
                }
                if (event.getLine(1).equalsIgnoreCase("class")) {
                    String className = event.getLine(2);
                    // TODO: Classes
                }
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do that, brother.");
            }
        }
    }

}
