package me.chaseoes.supercraftbrothers;

import me.chaseoes.supercraftbrothers.utilities.mysql.Mysql;
import org.bukkit.plugin.java.JavaPlugin;

public class SuperCraftBrothers extends JavaPlugin {
    
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Mysql.getInstance().setup(this);
    }
    
    public void onDisable() {

        //Probably make this the last method, maybe even sync / lock it to lock this thread until all mysql stuff is through
        Mysql.getInstance().close();
    }

}
