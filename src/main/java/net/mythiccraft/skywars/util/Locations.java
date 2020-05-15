/*
 * Copyright (c) 2019 R. Taylor Hughes (taylorhughes719).
 */

package net.mythiccraft.skywars.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Taylor Hughes
 */
public class Locations {

    @SuppressWarnings("ConstantConditions")
    public static String toString(Location loc) {
        return loc != null ? "World:" + loc.getWorld().getName() + " " +
                "X:" + loc.getBlockX() + " " +
                "Y:" + loc.getBlockY() + " " +
                "Z:" + loc.getBlockZ() : null;
    }

    public Location fromString(String s) {
        if (s == null) {
            return null;
        }
        String[] parts = s.split(" ");

        String wS = parts[0].split(":")[1];
        String xS = parts[1].split(":")[1];
        String yS = parts[2].split(":")[1];
        String zS = parts[3].split(":")[1];

        World w = Bukkit.getWorld(wS);
        if (w != null) {
            return new Location(w, Integer.parseInt(xS), Integer.parseInt(yS), Integer.parseInt(zS));
        }
        return null;
    }

}