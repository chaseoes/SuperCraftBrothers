package me.chaseoes.supercraftbrothers;

import me.chaseoes.supercraftbrothers.utilities.SerializableLocation;

import org.bukkit.Location;
import org.bukkit.configuration.Configuration;

public class SCBMap {

    private Location sp1, sp2, sp3, sp4;
    private Location lobbySign;
    private Location classLobby;
    private SCBGame game;
    private final SuperCraftBrothers plugin;

    public SCBMap(SCBGame game, SuperCraftBrothers plugin) {
        this.game = game;
        this.plugin = plugin;
    }

    public void load() {
        Configuration config = plugin.getConfig();
        if (config.isString("map." + game.getName() + ".sp1")) {
            sp1 = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".sp1"));
        } else {
            sp1 = null;
        }
        if (config.isString("map." + game.getName() + ".sp2")) {
            sp2 = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".sp2"));
        } else {
            sp2 = null;
        }
        if (config.isString("map." + game.getName() + ".sp3")) {
            sp3 = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".sp3"));
        } else {
            sp3 = null;
        }
        if (config.isString("map." + game.getName() + ".sp4")) {
            sp4 = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".sp4"));
        } else {
            sp4 = null;
        }
        if (config.isString("map." + game.getName() + ".lobby-sign")) {
            lobbySign = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".lobby-sign"));
        } else {
            lobbySign = null;
        }
        if (config.isString("map." + game.getName() + ".class-lobby")) {
            classLobby = SerializableLocation.stringToLocation(config.getString("map." + game.getName() + ".class-lobby"));
        } else {
            classLobby = null;
        }
    }

    public void save() {
        Configuration config = plugin.getConfig();
        if (sp1 != null) {
            config.set("map." + game.getName() + ".sp1", SerializableLocation.locationToString(sp1));
        } else {
            config.set("map." + game.getName() + ".sp1", null);
        }
        if (sp2 != null) {
            config.set("map." + game.getName() + ".sp2", SerializableLocation.locationToString(sp2));
        } else {
            config.set("map." + game.getName() + ".sp2", null);
        }
        if (sp3 != null) {
            config.set("map." + game.getName() + ".sp3", SerializableLocation.locationToString(sp3));
        } else {
            config.set("map." + game.getName() + ".sp3", null);
        }
        if (sp4 != null) {
            config.set("map." + game.getName() + ".sp4", SerializableLocation.locationToString(sp4));
        } else {
            config.set("map." + game.getName() + ".sp4", null);
        }
        if (lobbySign != null) {
            config.set("map." + game.getName() + ".lobby-sign", SerializableLocation.locationToString(lobbySign));
        } else {
            config.set("map." + game.getName() + ".lobby-sign", null);
        }
        if (classLobby != null) {
            config.set("map." + game.getName() + ".class-lobby", SerializableLocation.locationToString(classLobby));
        } else {
            config.set("map." + game.getName() + ".class-lobby", null);
        }
        plugin.saveConfig();
    }

    public SCBGame getGame() {
        return game;
    }

    public Location getSp1() {
        return sp1;
    }

    public void setSp1(Location sp1) {
        this.sp1 = sp1;
        save();
    }

    public Location getSp2() {
        return sp2;
    }

    public void setSp2(Location sp2) {
        this.sp2 = sp2;
        save();
    }

    public Location getSp3() {
        return sp3;
    }

    public void setSp3(Location sp3) {
        this.sp3 = sp3;
        save();
    }

    public Location getSp4() {
        return sp4;
    }

    public void setSp4(Location sp4) {
        this.sp4 = sp4;
        save();
    }

    public Location getLobbySign() {
        return lobbySign;
    }

    public void setLobbySign(Location lobbySign) {
        this.lobbySign = lobbySign;
        save();
    }

    public Location getClassLobby() {
        return classLobby;
    }

    public void setClassLobby(Location classLobby) {
        this.classLobby = classLobby;
        save();
    }
}
