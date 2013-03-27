package me.chaseoes.supercraftbrothers;

import org.bukkit.plugin.java.JavaPlugin;

public class SuperCraftBrothers extends JavaPlugin {
    
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    
    public void onDisable() {
        
    }

}
