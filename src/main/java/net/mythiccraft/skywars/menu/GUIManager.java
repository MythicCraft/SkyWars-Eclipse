package net.mythiccraft.skywars.menu;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Manager;

import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Taylor Hughes
 */
public class GUIManager extends Manager {

    private Map<String, GUI> guis;

    public GUIManager(SkyWars plugin) {
        super(plugin);
        this.guis = new HashMap<>();
    }

    public void loadGUIs() {
        File guisFolder = new File(plugin.getDataFolder() + "/guis/");
        if (!guisFolder.exists() && guisFolder.mkdir()) {
            SkyWars.warn("Failed to create the GUIs folder!");
        }
        if (guisFolder.exists()) {
            for (File file : guisFolder.listFiles()) {
                GUI gui = new GUI(plugin, file.getName().replaceAll(".yml", ""));
                guis.put(gui.getName(), gui);
            }
        }
    }

    public GUI getGUI(String name) {
        return this.guis.get(name);
    }

    public void openGUI(Player who, String name) {
        getGUI(name).open(who);
    }

    @Override
    public void shutdown() {
        if (!this.guis.isEmpty()) {
            this.guis.clear();
        }
        this.guis = null;
    }
}
