package me.chaseoes.supercraftbrothers;

import me.chaseoes.supercraftbrothers.classes.SCBClass;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CraftBrother {
    
    private String name;
    private int lives;
    private int kills;
    private boolean isInLobby;
    private CraftBrother lastDamagedBy;
    private String currentClass;
    private SCBGame currentGame;
    
    public CraftBrother(String name) {
        name = this.name;
        lives = 4;
        kills = 0;
    }
    
    public Player getPlayer() {
        return Bukkit.getPlayer(name);
    }
    
    public int getLivesLeft() {
        return lives;
    }
    
    public void setLivesLeft(int i) {
        if (i != -1) {
            lives = i;
        } else {
            lives--;
        }
    }
    
    public int getKills() {
        return kills;
    }
    
    public void setKills(int i) {
        if (i != -1) {
            kills = i;
        } else {
            kills--;
        }
    }
    
    public void setInLobby(boolean b) {
        isInLobby = b;
    }
    
    public boolean isInLobby() {
        return isInLobby;
    }
    
    public SCBGame getCurrentGame() {
        return currentGame;
    }
    
    public void setCurrentGame(SCBGame game) {
        currentGame = game;
    }
    
    public String getCurrentClass() {
        return currentClass;
    }
    
    public void setCurrentClass(SCBClass c) {
        currentClass = c.getName();
    }

    public CraftBrother getLastDamagedBy() {
        return lastDamagedBy;
    }

    public void setLastDamagedBy(CraftBrother lastDamagedBy) {
        this.lastDamagedBy = lastDamagedBy;
    }
}
