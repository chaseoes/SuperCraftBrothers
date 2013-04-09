package me.chaseoes.supercraftbrothers;

import me.chaseoes.supercraftbrothers.commands.CommandManager;
import me.chaseoes.supercraftbrothers.listeners.*;
import me.chaseoes.supercraftbrothers.utilities.mysql.Mysql;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperCraftBrothers extends JavaPlugin {

    private static SuperCraftBrothers instance;

    public static SuperCraftBrothers getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        //TODO: Uncomment after testing
        //Mysql.getInstance().setup(this);
        SCBGameManager.getInstance().setup(this);
        Schedulers.getInstance().setup(this);

        // Listener Registration
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDamageListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new SignChangeListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new PlayerRespawnListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new SCBDeathListener(), this);
        pm.registerEvents(new EntityBowFireListener(), this);
        pm.registerEvents(new PlayerDropItemListener(), this);
        pm.registerEvents(new PlayerPickupItemListener(), this);

        // HUB Signs
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                HUBSigns.update();
            }
        }, 0L, 20L);

        getCommand("scbb").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        instance = null;
        getServer().getScheduler().cancelTasks(this);

        // Probably make this the last method, maybe even sync / lock it to lock
        // this thread until all mysql stuff is through
        Mysql.getInstance().close();
    }

}
