/*
 * Copyright (c) 2020 taylorhughes719.
 */

package net.mythiccraft.skywars.util;

import net.mythiccraft.skywars.SkyWars;

/**
 * A class used to manage data and unload it all at the end.
 *
 * @since 1.0.0
 */
public abstract class Manager {

    public SkyWars plugin;

    public Manager(SkyWars plugin) {
        this.plugin = plugin;
    }

    public SkyWars getPlugin() {
        return this.plugin;
    }

    public abstract void shutdown();
}
