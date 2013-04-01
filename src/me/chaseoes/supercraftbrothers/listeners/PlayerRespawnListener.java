package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;

public class PlayerRespawnListener implements Listener {

    private Random rand = new Random();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (SCBGameManager.getInstance().isInGame(event.getPlayer().getName())) {
            CraftBrother bro = SCBGameManager.getInstance().getCraftBrother(event.getPlayer());
            if (bro.getLivesLeft() > 0) {
                Location resp;
                switch (rand.nextInt(4)) {
                    case 0:
                        resp = bro.getCurrentGame().getMap().getSp1();
                        break;
                    case 1:
                        resp = bro.getCurrentGame().getMap().getSp2();
                        break;
                    case 2:
                        resp = bro.getCurrentGame().getMap().getSp3();
                        break;
                    case 3:
                    default:
                        resp = bro.getCurrentGame().getMap().getSp4();
                        break;
                }
                event.setRespawnLocation(resp);
                event.getPlayer().sendMessage(ChatColor.DARK_BLUE + "You have " + bro.getLivesLeft() + " live" + (bro.getLivesLeft() == 1 ? "left" : "s left"));
            }
        }
    }
}
