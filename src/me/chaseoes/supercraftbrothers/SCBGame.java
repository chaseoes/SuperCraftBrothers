package me.chaseoes.supercraftbrothers;

import java.util.*;

import me.chaseoes.supercraftbrothers.classes.SCBClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SCBGame {

    private String name;
    private SCBMap map;
    private final SuperCraftBrothers plugin;
    private final HashMap<String, CraftBrother> ingame = new HashMap<String, CraftBrother>();
    // !inLobby implies inGame
    private boolean inLobby = true;

    public SCBGame(SuperCraftBrothers plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        map = new SCBMap(this, plugin);
        map.load();
    }

    public String getName() {
        return name;
    }

    public SCBMap getMap() {
        return map;
    }

    public void joinLobby(Player bro) {
        if (ingame.containsKey(bro.getName().toLowerCase())) {
            return;
        }
        if (getNumberIngame() > 3) {
            bro.sendMessage(ChatColor.RED + getName() + " is full!");
            return;
        }
        if (isInGame()) {
            bro.sendMessage(ChatColor.RED + getName() + " is currently running!");
            return;
        }
        CraftBrother cBro = SCBGameManager.getInstance().addCraftBrother(bro.getName());
        cBro.setInLobby(true);
        cBro.setCurrentGame(this);
        ingame.put(bro.getName().toLowerCase(), cBro);
        bro.teleport(map.getClassLobby());
        bro.sendMessage(ChatColor.DARK_GREEN + "You have joined " + getName() + ", select a class to continue.");
        if (getNumberIngame() == 4) {
            Schedulers.getInstance().startLobbyCountdown(this);
        }
    }

    public void leaveLobby(Player bro) {
        if (!ingame.containsKey(bro.getName().toLowerCase())) {
            return;
        }
        ingame.remove(bro.getName().toLowerCase());
        bro.teleport(SCBGameManager.getInstance().getMainLobby());
        bro.sendMessage("You left the lobby");
        SCBGameManager.getInstance().removeCraftBrother(bro.getPlayer().getName());
    }

    // Do we even need this?
    public void joinGame(Player bro) {
        // TODO: Class settin and shizz
    }

    public void leaveGame(Player bro) {
        if (!ingame.containsKey(bro.getName().toLowerCase())) {
            return;
        }
        ingame.remove(bro.getName().toLowerCase());
        bro.teleport(SCBGameManager.getInstance().getMainLobby());
        bro.sendMessage("You left the game");
        SCBGameManager.getInstance().removeCraftBrother(bro.getName());
        checkWin();
    }

    public void checkWin() {
        if (ingame.size() == 1) {
            winGame((CraftBrother) ingame.values().toArray()[0]);
        }
    }

    public void startGame() {
        List<Location> spawns = Arrays.asList(map.getSp1(), map.getSp2(), map.getSp3(), map.getSp4());
        List<CraftBrother> bros = new ArrayList<CraftBrother>(ingame.values());
        for (int i = 0; i < bros.size(); i++) {
            bros.get(i).getPlayer().teleport(spawns.get(i));
        }
        for (CraftBrother bro : bros) {
            if (bro.getCurrentClass() == null) {
                //TODO: random class selection or kicking, whichever works
            }
            new SCBClass(bro.getCurrentClass()).apply(bro);
            bro.getPlayer().sendMessage(ChatColor.DARK_BLUE + "Round started on" + getName());
        }
        //TODO: Scoreboard initialization stuff when it comes out

    }

    public void winGame(CraftBrother bro) {
        Bukkit.broadcastMessage(bro.getPlayer().getName() + " has won on " + getName());
        bro.getPlayer().teleport(SCBGameManager.getInstance().getMainLobby());
        SCBGameManager.getInstance().removeCraftBrother(bro.getPlayer().getName());
        ingame.clear();
        setInLobby(true);
    }

    public int getNumberIngame() {
        return ingame.size();
    }

    public Collection<CraftBrother> getIngame() {
        return ingame.values();
    }

    public void broadcast(String message) {
        for (CraftBrother bro : ingame.values()) {
            bro.getPlayer().sendMessage(message);
        }
    }

    public boolean isInLobby() {
        return inLobby;
    }

    public boolean isInGame() {
        return !inLobby;
    }

    public void setInLobby(boolean b) {
        inLobby = b;
    }

    public int getAlive() {
        return getNumberIngame();
    }

    public int getDead() {
        return 4 - getNumberIngame();
    }
}
