package me.chaseoes.supercraftbrothers.commands;

import me.chaseoes.supercraftbrothers.SCBGameManager;
import me.chaseoes.supercraftbrothers.SCBMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        // /scb
        if (strings.length == 0) {
            return true;
        }

        // /scb createmap <map name>
        if (strings[0].equalsIgnoreCase("createmap")) {
            if (strings.length > 1) {
                String mapName = strings[1];
                if (SCBGameManager.getInstance().getGame(mapName) != null) {
                    cs.sendMessage("Map already exists");
                } else {
                    //TODO: Call central Game / Map storage from SCB.getInst() to create it in config
                    cs.sendMessage("Created map " + mapName);
                }
            } else {
                cs.sendMessage("Usage: /scb createmap <map name>");
            }
        }

        // /scb deletemap <map name>
        if (strings[0].equalsIgnoreCase("deletemap")) {
            if (strings.length > 1) {
                String mapName = strings[1];
                if (SCBGameManager.getInstance().getGame(mapName) == null) {
                    cs.sendMessage("Map doesn't exist");
                } else {
                    //TODO: Call central Game / Map storage from SCB.getInst() to unload and delete
                    cs.sendMessage("Deleted map " + mapName);
                }
            } else {
                cs.sendMessage("Usage: /scb deletemap <map name>");
            }
        }

        if (strings[0].equalsIgnoreCase("set")) {
            if (!(cs instanceof Player)) {
                cs.sendMessage("Command only usable by player");
            } else {
                Player player = (Player) cs;
                if (strings.length > 1) {
                    // /scb set lobby <map name>
                    if (strings[1].equalsIgnoreCase("lobby")) {
                        if (strings.length > 2) {
                            String mapName = strings[2];
                            //SCBMap map = SCBGameManager.getInstance().getGame(mapName);
                            //if (map == null) {
                            //    cs.sendMessage("Map doesn't exist");
                            //} else {
                            //map.setClassLobby(player.getLocation());
                            cs.sendMessage("Set the lobby of " + mapName);
                            //}
                        } else {
                            cs.sendMessage("Usage: /scb set lobby <map name>");
                        }
                    }

                    // /scb set spawn <map name> <1|2|3|4>
                    if (strings[1].equalsIgnoreCase("spawn")) {
                        if (strings.length > 3) {
                            int i;
                            try {
                                i = Integer.parseInt(strings[3]);
                            } catch (NumberFormatException ex) {
                                cs.sendMessage("Invalid number '" + strings[3] + "'");
                                return true;
                            }
                            if (i > 0 && i < 5) {
                                String mapName = strings[2];
                                SCBMap map = null; //TODO: Getting map from w/e storage its called
                                //SCBMap map = SCBGameManager.getInstance().getGame(mapName);
                                //if (map == null) {
                                //    cs.sendMessage("Map doesn't exist");
                                //} else {
                                switch (i) {
                                    case 1:
                                        map.setSp1(player.getLocation());
                                        break;
                                    case 2:
                                        map.setSp2(player.getLocation());
                                        break;
                                    case 3:
                                        map.setSp3(player.getLocation());
                                        break;
                                    case 4:
                                        map.setSp4(player.getLocation());
                                        break;
                                    default:
                                        cs.sendMessage("YOU BORKED THE RULES");
                                }
                                //}
                            } else {
                                cs.sendMessage(i + " is not between 1 and 4");
                            }
                        } else {
                            cs.sendMessage("Usage: /scb set spawn <map name> <1|2|3|4>");
                        }
                    }
                } else {
                    cs.sendMessage("Usage: /scb set <lobby|spawn>");
                }
            }
        }
        return true;
    }

}
