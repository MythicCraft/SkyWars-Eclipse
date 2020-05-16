package net.mythiccraft.skywars.game;

import com.google.common.collect.BiMap;
import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.config.FileConfig;
import net.mythiccraft.skywars.item.Cage;
import net.mythiccraft.skywars.item.Rarity;
import net.mythiccraft.skywars.util.Colors;
import net.mythiccraft.skywars.util.Loadable;
import net.mythiccraft.skywars.util.Text;
import net.mythiccraft.skywars.util.Unloadable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * @author Taylor Hughes
 */
public class Arena implements Loadable, Unloadable {

    private String name;
    private World world;
    private List<Location> spawnpoints;
    private Map<Integer, Boolean> spawnMap;
    private boolean inProgress;
    private Set<Player> players;
    private int maxPlayers;
    private int minPlayers;
    private FileConfig config;

    public Arena(String name, World world, List<Location> spawnpoints, boolean inProgress) {
        this.name = name;
        this.world = world;
        this.spawnpoints = spawnpoints;
        this.inProgress = inProgress;
        this.players = new HashSet<>();
        this.config = new FileConfig(SkyWars.getInstance(), name);
        this.spawnMap = new HashMap<>();
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public boolean isFull() {
        return this.players.size() == this.maxPlayers;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (!isFull()) {
            this.players.add(player);
            for (Player p : this.players) {
                p.sendMessage(Text.colorize(Colors.LIGHT_PURPLE + player.getDisplayName() + " has joined the game!"));
            }
            player.teleport(spawnpoints.get(this.players.size() + 1));
        } else {
            player.sendMessage(Text.colorize("&4That game is currently full."));
        }
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        player.teleport(SkyWars.getInstance().getLobbySpawn());
        player.sendMessage(Text.colorize("&6You quit the game and have been sent back to the lobby!"));

    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public List<Location> getSpawnpoints() {
        return spawnpoints;
    }

    public void setSpawnpoints(List<Location> spawnpoints) {
        this.spawnpoints = spawnpoints;
    }


    public void start() {
        this.setInProgress(true);
        players.forEach(p -> p.sendMessage(Text.colorize("The game is starting!")));
        Bukkit.getScheduler().runTaskTimerAsynchronously(SkyWars.getInstance(), () -> {
            players.forEach(p -> p.sendMessage(Text.colorize("&bThe game is starting in 10 seconds")));
        }, 0L, 20L);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setupSpawnpoints() {
        Cage cage = new Cage("default", false, Rarity.COMMON);
        for (Location loc : spawnpoints) {
            cage.spawn(loc);
        }
    }

    public FileConfig getConfig() {
        return config;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    /**
     * Called when the arena is being loaded.
     */
    @Override
    public void load() {

    }

    /**
     * Called when the arena is unloaded.
     */
    @Override
    public void unload() {

    }
}
