package net.mythiccraft.skywars.data;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Manager;

/**
 * @author Taylor Hughes
 */
public class DataManager extends Manager {

    private Storage storage;

    public DataManager(SkyWars plugin) {
        super(plugin);
    }

    public void setup() {
        if (plugin.getMainConfig().getBoolean("MySQL.Enabled")) {
            storage = new MySQL(plugin);
        } else {
            storage = new SQLite(plugin);
        }
        storage.connect();
    }

    @Override
    public void shutdown() {
        storage.shutdown();
    }
}
