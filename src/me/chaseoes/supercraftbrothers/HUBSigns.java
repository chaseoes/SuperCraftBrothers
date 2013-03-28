package me.chaseoes.supercraftbrothers;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;

public class HUBSigns {

    public static void update() {
        for (SCBGame game : SCBGameManager.getInstance().getAllGames()) {
            Sign s = (Sign) game.getMap().getLobbySign().getBlock().getState();
            if (game.isInLobby()) {
                s.setLine(0, "SuperCraftBros");
                s.setLine(1, "Click to Join");
                s.setLine(2, ChatColor.BOLD + game.getName());
                s.setLine(3, "Players: " + game.getNumberIngame() + "/4");
            } else {
                s.setLine(0, ChatColor.DARK_GREEN + "In Progress");
                s.setLine(1, ChatColor.BOLD + game.getName());
                s.setLine(2, "Players Alive: " + game.getAlive());
                s.setLine(3, "Players Dead: " + game.getDead());
            }
        }
    }

}
