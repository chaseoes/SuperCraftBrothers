package me.chaseoes.supercraftbrothers;

import me.chaseoes.supercraftbrothers.listeners.PlayerDamageListener;
import me.chaseoes.supercraftbrothers.listeners.PlayerInteractListener;
import me.chaseoes.supercraftbrothers.listeners.SignChangeListener;
import me.chaseoes.supercraftbrothers.utilities.mysql.Mysql;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperCraftBrothers extends JavaPlugin {
    
    private static SuperCraftBrothers instance;
    
    public static SuperCraftBrothers getInstance() {
        return instance;
    }
    
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Mysql.getInstance().setup(this);
        
        // Listener Registration
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDamageListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new SignChangeListener(), this);
        
        // HUB Signs
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                HUBSigns.update();
            }
        }, 0L, 20L);

        //Reloads happen
        for (Player player : Bukkit.getOnlinePlayers()) {
            SCBGameManager.getInstance().getCraftBrother(player.getName());
        }
    }
    
    public void onDisable() {
        instance = null;
        getServer().getScheduler().cancelTasks(this);
        
        //Probably make this the last method, maybe even sync / lock it to lock this thread until all mysql stuff is through
        Mysql.getInstance().close();
    }

}
