package me.chaseoes.supercraftbrothers;

import java.util.HashSet;
import java.util.Set;

public class SCBGame {

    private String name;
    private final SuperCraftBrothers plugin;
    private final Set<CraftBrother> ingame = new HashSet<CraftBrother>();

    public SCBGame(SuperCraftBrothers plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void joinLobby(CraftBrother bro) {
        if (ingame.contains(bro))
            return;
        if (getNumberIngame() > 3)
            throw new RuntimeException("SOMEONE BROKE SOMETHING JOINING GAMES");
        ingame.add(bro);
    }

    public void leaveLobby(CraftBrother bro) {
        if (!ingame.contains(bro))
            return;
        ingame.remove(bro);
    }

    public void joinGame(CraftBrother bro) {
        //TODO: Class settin and shizz
    }

    public void leaveGame(CraftBrother bro) {

    }

    public int getNumberIngame() {
        return ingame.size();
    }
}
