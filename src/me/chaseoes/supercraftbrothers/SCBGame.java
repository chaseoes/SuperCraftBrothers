package me.chaseoes.supercraftbrothers;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

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
        if (ingame.containsKey(bro.getName().toLowerCase()))
            return;
        if (getNumberIngame() > 3)
            throw new RuntimeException("SOMEONE BROKE SOMETHING JOINING GAMES");
        CraftBrother cBro = SCBGameManager.getInstance().addCraftBrother(bro.getName());
        ingame.put(bro.getName().toLowerCase(), cBro).setCurrentGame(this);
        bro.teleport(map.getClassLobby());
    }

    public void leaveLobby(CraftBrother bro) {
        if (!ingame.containsValue(bro))
            return;
        ingame.remove(bro);
        bro.getPlayer().teleport(SCBGameManager.getInstance().getMainLobby());
        SCBGameManager.getInstance().removeCraftBrother(bro.getPlayer().getName());
    }

    public void joinGame(CraftBrother bro) {
        //TODO: Class settin and shizz
    }

    public void leaveGame(CraftBrother bro) {

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
