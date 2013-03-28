package me.chaseoes.supercraftbrothers;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class SCBGameManager {

    private static final SCBGameManager instance = new SCBGameManager();
    private HashMap<String, SCBGame> games = new HashMap<String, SCBGame>();
    private HashMap<String, CraftBrother> bros = new HashMap<String, CraftBrother>();

    private SCBGameManager() { }

    public static SCBGameManager getInstance() {
        return instance;
    }

    public CraftBrother getCraftBrother(Player player) {
        if (player == null) return null;
        return getCraftBrother(player.getName());
    }

    public CraftBrother getCraftBrother(String player) {
        if (player == null) return null;
        if (bros.containsKey(player.toLowerCase())) {
            return bros.get(player.toLowerCase());
        }
        return bros.put(player.toLowerCase(), new CraftBrother(player));
    }

    public void playerLogout(String player) {
        if (player == null) return;
        if (bros.containsKey(player.toLowerCase())) {
            bros.remove(player.toLowerCase());
        }
    }

    public SCBGame getGame(String game) {
        if (games.containsKey(game.toLowerCase())) {
            return games.get(game.toLowerCase());
        }
        return null;
    }

    public void addGame(SCBGame game) {
        if (game == null) return;
        if (!games.containsKey(game.getName().toLowerCase())) {
            games.put(game.getName().toLowerCase(), game);
        }
    }

    public void removeGame(SCBGame game) {
        if (game == null) return;
        removeGame(game.getName());
    }

    public void removeGame(String game) {
        if (game == null) return;
        if (games.containsKey(game.toLowerCase())) {
            games.remove(game.toLowerCase());
        }
    }

}
