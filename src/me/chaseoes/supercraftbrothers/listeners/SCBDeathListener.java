package me.chaseoes.supercraftbrothers.listeners;

import me.chaseoes.supercraftbrothers.events.SCBDeathEvent;

import org.bukkit.event.Listener;

public class SCBDeathListener implements Listener {

    public void onSCBDeath(SCBDeathEvent event) {

        event.getKilled().setLastDamagedBy(null);
    }
}
