package net.mythiccraft.skywars;

import net.mythiccraft.skywars.hook.VaultUtil;
import net.mythiccraft.skywars.menu.GUIManager;
import net.mythiccraft.skywars.util.RandomFirework;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the SkyWars plugin.
 *
 * @author Taylor Hughes
 */
public final class SkyWars extends JavaPlugin {

    private static SkyWars instance;
    private Location spawn;
    private GUIManager guiManager;
    private VaultUtil vaultUtil;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!this.getDataFolder().exists() && !this.getDataFolder().mkdir()) {
            this.getLogger().severe("Failed to create plugin directory! The plugin will now be disabled!");
            this.setEnabled(false);
        }

        RandomFirework.loadFireworks();

        guiManager = new GUIManager(this);

        vaultUtil = new VaultUtil(this);
        vaultUtil.setupEconomy();

        // Set static instance of the plugin
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        guiManager.shutdown();

        // Just to be safe, set the instance to null
        instance = null;
    }

    /**
     * Get the instance of the plugin.
     *
     * @return The plugin's static instance.
     */
    public static SkyWars getInstance() {
        return instance;
    }

    /**
     * Get the GUI manager.
     *
     * @return The {@see GUIMana}
     */
    public GUIManager getGUIManager() {
        return guiManager;
    }

    public VaultUtil getVaultUtil() {
        return vaultUtil;
    }

    /**
     * Get the lobby spawn point.
     *
     * @return The lobby location
     */
    public Location getSpawn() {
        return spawn;
    }

    /**
     * Register the specified event listener.
     *
     * @param listener The listener to be registered.
     */
    public void register(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, instance);
    }


    /**
     * Unregister the specified event listener.
     *
     * @param listener The listener to be unregistered.
     */
    public void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }


    /**
     * Get the server's plugin manager.
     *
     * @return The plugin manager.
     */
    private PluginManager getPluginManager() {
        return this.getServer().getPluginManager();
    }

    public Location getLobbySpawn() {
        return spawn;
    }

    public static void sendConsoleMsg(String msg) {
        Bukkit.getConsoleSender().sendMessage(msg.replaceAll("&", "§"));
    }

    public static void log(String msg) {
        sendConsoleMsg("§d[§bDaily§aBonus§d] §f" + msg);
    }

    public static void sql(String msg) {
        log("§b[SQL] §f" + msg);
    }

    public static void warn(String msg) {
        log("§WARN: " + msg);
    }
}
