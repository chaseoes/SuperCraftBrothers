package me.chaseoes.supercraftbrothers.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SerializableLocation {

    public static String locationToString(Location l) {
        String w = l.getWorld().getName();
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        return w + "." + x + "." + y + "." + z;
    }

    public static Location stringToLocation(String s) {
        String[] str = s.split("\\.");
        World w = Bukkit.getServer().getWorld(str[0]);
        int x = Integer.parseInt(str[1]);
        int y = Integer.parseInt(str[2]);
        int z = Integer.parseInt(str[3]);
        return new Location(w, x, y, z);
    }

    public static boolean compareLocations(Location one, Location two) {
        String w = one.getWorld().getName();
        int x = one.getBlockX();
        int y = one.getBlockZ();
        int z = one.getBlockZ();

        String checkw = two.getWorld().getName();
        int checkx = two.getBlockX();
        int checky = two.getBlockZ();
        int checkz = two.getBlockZ();

        return w.equalsIgnoreCase(checkw) && x == checkx && y == checky && z == checkz;
    }
}
