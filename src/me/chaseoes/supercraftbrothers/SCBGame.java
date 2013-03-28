package me.chaseoes.supercraftbrothers;

import java.util.HashSet;
import java.util.Set;

public class SCBGame {

    private String name;
    private SCBMap map;
    private final SuperCraftBrothers plugin;
    private final Set<CraftBrother> ingame = new HashSet<CraftBrother>();
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

    public void joinLobby(CraftBrother bro) {
        if (ingame.contains(bro))
            return;
        if (getNumberIngame() > 3)
            throw new RuntimeException("SOMEONE BROKE SOMETHING JOINING GAMES");
        ingame.add(bro);
        bro.getPlayer().teleport(map.getClassLobby());
    }

    public void leaveLobby(CraftBrother bro) {
        if (!ingame.contains(bro))
            return;
        ingame.remove(bro);
        bro.getPlayer().teleport(SCBGameManager.getInstance().getMainLobby());
    }

    public void joinGame(CraftBrother bro) {
        //TODO: Class settin and shizz
    }

    public void leaveGame(CraftBrother bro) {

    }

    public int getNumberIngame() {
        return ingame.size();
    }

    public void broadcast(String message) {
        for (CraftBrother bro : ingame) {
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
}
