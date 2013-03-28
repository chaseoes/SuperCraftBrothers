package me.chaseoes.supercraftbrothers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        // /scb
        if (strings.length == 0) {
            return true;
        }
        
        // /scb createmap <map name>
        if (strings[0].equalsIgnoreCase("createmap")) {
            String mapName = strings[1];
        }
        
        // /scb deletemap <map name>
        if (strings[0].equalsIgnoreCase("createmap")) {
            String mapName = strings[1];
        }
        
        if (strings[0].equalsIgnoreCase("set")) {
            // /scb set lobby <map name>
            if (strings[1].equalsIgnoreCase("lobby")) {
                String mapName = strings[2];
            }
            
            // /scb set spawn <map name> <1|2|3|4>
            if (strings[1].equalsIgnoreCase("spawn")) {
                String mapName = strings[2];
                int i = Integer.parseInt(strings[3]);
                // TODO: Check if i is a number, check string lengths.
            }
        }
        return true;
    }

}
