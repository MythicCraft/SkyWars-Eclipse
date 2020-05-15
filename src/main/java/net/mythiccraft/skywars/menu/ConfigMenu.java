package net.mythiccraft.skywars.menu;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.config.FileConfig;

import java.io.File;

/**
 * @author Taylor Hughes
 */
public class ConfigMenu extends GUI {

    public ConfigMenu(SkyWars plugin, FileConfig config) {
        super(plugin, config.getFileName().replaceAll(".yml", ""));
    }
}
