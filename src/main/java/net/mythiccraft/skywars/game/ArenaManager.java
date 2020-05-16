package net.mythiccraft.skywars.game;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Manager;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taylor Hughes
 */
public class ArenaManager extends Manager {

    private Map<String, Arena> arenas;

    public ArenaManager(SkyWars plugin) {
        super(plugin);
        this.arenas = new HashMap<>();
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }

    public boolean exists(String arenaName) {
        return arenas.containsKey(arenaName.toLowerCase());
    }

    @Nullable
    public Arena getArena(String arena) {
        return arenas.getOrDefault(arena, null);
    }

    @Override
    public void shutdown() {
        if (this.arenas != null) {
            this.arenas.clear();
            this.arenas = null;
        }
    }
}