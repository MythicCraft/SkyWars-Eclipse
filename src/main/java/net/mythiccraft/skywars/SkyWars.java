package net.mythiccraft.skywars;

import net.mythiccraft.skywars.cmd.SGUICmd;
import net.mythiccraft.skywars.cmd.SkyWarsCmd;
import net.mythiccraft.skywars.config.CagesConfig;
import net.mythiccraft.skywars.config.FileConfig;
import net.mythiccraft.skywars.config.GUIConfig;
import net.mythiccraft.skywars.event.InventoryListener;
import net.mythiccraft.skywars.hook.VaultUtil;
import net.mythiccraft.skywars.menu.GUI;
import net.mythiccraft.skywars.menu.GUIManager;
import net.mythiccraft.skywars.util.RandomFirework;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * The main class of the SkyWars plugin.
 *
 * @author Taylor Hughes
 */
public final class SkyWars extends JavaPlugin {

    private static SkyWars instance;

    // File configs
    private FileConfig mainConfig;
    private FileConfig dataConfig;
    private FileConfig messagesConfig;
    private FileConfig trailsConfig;
    private FileConfig itemsConfig;
    private FileConfig lobbyConfig;
    private CagesConfig cagesConfig;

    private SkyWarsCmd skyWarsCmd;
    private SGUICmd sguiCmd;

    private InventoryListener inventoryListener;
    private GUIManager guiManager;
    private VaultUtil vaultUtil;
    private Location spawn;
    private GUIConfig testGUIConf;
    private GUI testGUI;

    @Override
    public void onEnable() {
        // Start timer
        long startTime = System.nanoTime();

        // Plugin startup logic
        if (!this.getDataFolder().exists() && !this.getDataFolder().mkdir()) {
            severe("Failed to create plugin directory! The plugin will now be disabled!");
            this.setEnabled(false);
        }

        // setup configs
        mainConfig = new FileConfig(this, "config.yml");
        messagesConfig = new FileConfig(this, "messages.yml");
        dataConfig = new FileConfig(this, "data.yml");
        trailsConfig = new FileConfig(this, "trails.yml");
        cagesConfig = new CagesConfig(this);
        lobbyConfig = new FileConfig(this, "lobby.yml");
        itemsConfig = new FileConfig(this, "items.yml");

        mainConfig.load();
        messagesConfig.load();
        dataConfig.load();
        trailsConfig.load();
        cagesConfig.load();
        lobbyConfig.load();
        itemsConfig.load();

        // setup gui configs
        File guisFolder = new File(this.getDataFolder() + "/guis/");
        if (!guisFolder.exists() && !guisFolder.mkdir()) {
            warn("Failed to create GUI folder! The plugin will now be disabled!");
            setEnabled(false);
        }

        //testGUIConf = new GUIConfig(this, "testgui");
       // testGUIConf.load();

        testGUI = new GUI(this, "testgui");
        testGUI.load();

        // Set spawn
        if (this.dataConfig.contains("Lobby")) {
            spawn = this.dataConfig.getLocation("Lobby");
        }

        RandomFirework.loadFireworks();
        inventoryListener = new InventoryListener(this);

        this.getServer().getPluginManager().registerEvents(inventoryListener, this);


        guiManager = new GUIManager(this);
        guiManager.loadGUIs();

        vaultUtil = new VaultUtil(this);
        vaultUtil.setupEconomy();

        skyWarsCmd = new SkyWarsCmd(this);
        sguiCmd = new SGUICmd(this);

        // Register commands
        if (this.getCommand("skywars") != null ) {
            Objects.requireNonNull(this.getCommand("skywars")).setExecutor(skyWarsCmd);
        }
        if (this.getCommand("sgui") != null ) {
            Objects.requireNonNull(this.getCommand("sgui")).setExecutor(sguiCmd);
        }

        // Set static instance of the plugin
        instance = this;

        // Stop timer
        long stopTime = System.nanoTime();
        //log("Startup complete! Took " + NumberFormat.getNumberInstance().format((stopTime - startTime / 1000000) + " ms."));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        guiManager.shutdown();

        HandlerList.unregisterAll(this);

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
     * Set the lobby spawnpoint to the specified location.
     *
     * @param location The location
     */
    public void setSpawn(Location location) {
        this.spawn = location;
        this.dataConfig.set("Lobby", location);
        this.dataConfig.save();
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

    /**
     * Send the console a message - colors supported!
     *
     * @param msg The message
     */
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
        log("§cWARN: " + msg);
    }

    public static void severe(String msg) {
        log("§4SEVERE: " + msg);
    }

    private void loadConfigurations() {
        mainConfig.load();
        messagesConfig.load();
        dataConfig.load();
        trailsConfig.load();
        cagesConfig.load();
    }

    /**
     * Get the main configuration file.
     *
     * @return The main config
     */
    public FileConfig getMainConfig() {
        return mainConfig;
    }

    /**
     * Get the messages configuration file.
     *
     * @return The messages config
     */
    public FileConfig getMessagesConfig() {
        return messagesConfig;
    }

    /**
     * Get the data file config.
     *
     * @return The data config
     */
    public FileConfig getDataConfig() {
        return dataConfig;
    }

    /**
     * Get the cages configuration file.
     *
     * @return The cages config
     */
    public CagesConfig getCagesConfig() {
        return cagesConfig;
    }

    /**
     * Get the trails configuration file.
     *
     * @return The trails config
     */
    public FileConfig getTrailsConfig() {
        return trailsConfig;
    }

    /**
     * Get the items configuration file.
     *
     * @return The items config
     */
    public FileConfig getItemsConfig() {
        return itemsConfig;
    }

    /**
     * Get the lobby configuration file.
     *
     * @return The lobby config
     */
    public FileConfig getLobbyConfig() {
        return lobbyConfig;
    }

    public GUI getTestGUI() {
        return testGUI;
    }
}
