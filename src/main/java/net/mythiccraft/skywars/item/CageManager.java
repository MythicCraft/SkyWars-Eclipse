package net.mythiccraft.skywars.item;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.config.CagesConfig;
import net.mythiccraft.skywars.util.Loadable;
import net.mythiccraft.skywars.util.Manager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taylor Hughes
 */
public class CageManager extends Manager implements Loadable {

    private Map<String, Cage> cages;

    public CageManager(SkyWars plugin) {
        super(plugin);
        this.cages = new HashMap<>();
    }

    @Override
    public void shutdown() {
        if (!cages.isEmpty()) {
            cages.clear();
        }
        cages = null;
    }

    public Map<String, Cage> getCages() {
        return cages;
    }

    public boolean cageExists(String name) {
        return cages.containsKey(name.toLowerCase());
    }

    /**
     * Called when the object is being loaded.
     */
    @Override
    public void load() {
        for (String cageName : plugin.getCagesConfig().getConfigSection("Cages").getKeys(false)) {
            Cage cage = null;
            cages.put(cageName, cage);
        }
    }
}
