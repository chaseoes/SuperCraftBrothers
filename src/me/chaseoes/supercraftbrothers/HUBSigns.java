package me.chaseoes.supercraftbrothers;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;

public class HUBSigns {
    
    public static void update() {
        for (SCBGame game : SCBGameManager.getInstance().getAllGames()) {
            Sign s = (Sign) new SCBMap(game, SuperCraftBrothers.getInstance()).getLobbySign().getBlock().getState();
            s.setLine(0, "SuperCraftBros");
            s.setLine(0, "Click to Join");
            s.setLine(0, ChatColor.BOLD + game.getName());
            s.setLine(0, "Players: " + game.getNumberIngame() + "/4");
        }
    }
    
}
