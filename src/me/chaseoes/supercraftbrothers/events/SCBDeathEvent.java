package me.chaseoes.supercraftbrothers.events;

import me.chaseoes.supercraftbrothers.CraftBrother;
import me.chaseoes.supercraftbrothers.SCBGame;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SCBDeathEvent extends Event {

    private static HandlerList handlers = new HandlerList();

    // Will never be null
    private CraftBrother killed;

    // Can be null if player was killed by something other than a player or
    // himself
    private CraftBrother killer;

    private SCBGame game;

    public SCBDeathEvent(CraftBrother killed, CraftBrother killer, SCBGame game) {
        this.killed = killed;
        this.killer = killer;
        this.game = game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public CraftBrother getKilled() {
        return killed;
    }

    public CraftBrother getKiller() {
        return killer;
    }

    public SCBGame getGame() {
        return game;
    }
}
