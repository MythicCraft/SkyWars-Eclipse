/*
 * Copyright (c) 2018 taylorhughes719.
 */

package net.mythiccraft.skywars.util;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of world-related utilities.
 *
 * @version 1.0.0-SNAPSHOT
 * @author Taylor Hughes
 */
public class Worlds {

    /**
     * Get a list of all worlds on the server.
     *
     * @return The list of worlds.
     */
    public static List<World> getWorlds() {
        return new ArrayList<>(Bukkit.getWorlds());
    }

    /**
     * Does a world by the given name exist?
     *
     * @param world The name of the world.
     * @return True if it exists, otherwise false.
     */
    public static boolean worldExists(String world) {
        return getWorld(world) != null;
    }

    /**
     * Get the world with the specified name.
     *
     * @param world The world name.
     * @return The world or null if it does not exist.
     */
    public static World getWorld(String world) {
        return Bukkit.getWorld(world);
    }

    /**
     * Unload the specified world and optionally save it.
     *
     * @param world The world name.
     * @param save Save the world before unloading it?
     */
    public static void unloadWorld(String world, boolean save) {
        Bukkit.unloadWorld(world, save);
    }


}
