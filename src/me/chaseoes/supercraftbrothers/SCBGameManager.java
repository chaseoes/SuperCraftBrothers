package me.chaseoes.supercraftbrothers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import me.chaseoes.supercraftbrothers.utilities.SerializableLocation;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SCBGameManager {

    private static final SCBGameManager instance = new SCBGameManager();
    private HashMap<String, SCBGame> games = new HashMap<String, SCBGame>();
    private HashMap<String, CraftBrother> bros = new HashMap<String, CraftBrother>();
    private SuperCraftBrothers plugin;
    private Location mainLobby;

    private SCBGameManager() {
    }

    public static SCBGameManager getInstance() {
        return instance;
    }

    public void setup(SuperCraftBrothers plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        games.clear();
        List<String> names = plugin.getConfig().getStringList("maps");
        if (names != null) {
            for (String name : names) {
                addGame(new SCBGame(plugin, name));
            }
        }
        if (plugin.getConfig().isString("main-lobby")) {
            mainLobby = SerializableLocation.stringToLocation(plugin.getConfig().getString("main-lobby"));
        } else {
            mainLobby = null;
        }
    }

    // Returns a game if one was created, else null if one already exists
    public SCBGame createGame(String name) {
        if (getGame(name) == null) {
            SCBGame game = games.put(name, new SCBGame(plugin, name));
            List<String> names = plugin.getConfig().getStringList("maps");
            names.add(name);
            plugin.getConfig().set("maps", names);
            plugin.saveConfig();
            return game;
        }
        return null;
    }

    // TODO: disable and kick everyone from said game before deleting
    public boolean deleteGame(String name) {
        if (getGame(name) != null) {
            games.remove(name);
            List<String> names = plugin.getConfig().getStringList("maps");
            names.remove(name);
            plugin.getConfig().set("maps", names);
            plugin.getConfig().set("maps." + name, null);
            plugin.saveConfig();
            return true;
        }
        return false;
    }

    public CraftBrother getCraftBrother(Player player) {
        if (player == null) {
            return null;
        }
        return getCraftBrother(player.getName());
    }

    public CraftBrother getCraftBrother(String player) {
        if (player == null) {
            return null;
        }
        if (bros.containsKey(player.toLowerCase())) {
            return bros.get(player.toLowerCase());
        }
        return null;
    }

    public CraftBrother addCraftBrother(String player) {
        return bros.put(player.toLowerCase(), new CraftBrother(player));
    }

    public void removeCraftBrother(String player) {
        if (player == null) {
            return;
        }
        if (bros.containsKey(player.toLowerCase())) {
            bros.remove(player.toLowerCase());
        }
    }

    // True if the player can be found in a SCBGame object
    public boolean isInGame(String player) {
        return bros.containsKey(player.toLowerCase());
    }

    public SCBGame getGame(String game) {
        if (games.containsKey(game.toLowerCase())) {
            return games.get(game.toLowerCase());
        }
        return null;
    }

    public void addGame(SCBGame game) {
        if (game == null) {
            return;
        }
        if (!games.containsKey(game.getName().toLowerCase())) {
            games.put(game.getName().toLowerCase(), game);
        }
    }

    public void removeGame(SCBGame game) {
        if (game == null) {
            return;
        }
        removeGame(game.getName());
    }

    public void removeGame(String game) {
        if (game == null) {
            return;
        }
        if (games.containsKey(game.toLowerCase())) {
            games.remove(game.toLowerCase());
        }
    }

    public Collection<SCBGame> getAllGames() {
        return games.values();
    }

    public Location getMainLobby() {
        return mainLobby;
    }

    public void setMainLobby(Location mainLobby) {
        plugin.getConfig().set("main-lobby", SerializableLocation.locationToString(mainLobby));
        plugin.saveConfig();
        this.mainLobby = mainLobby;
    }
}
