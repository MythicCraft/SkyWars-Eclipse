package net.mythiccraft.skywars.config;

import com.google.common.io.ByteStreams;

import net.mythiccraft.skywars.SkyWars;
import net.mythiccraft.skywars.util.Text;

import net.mythiccraft.skywars.util.Worlds;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * A custom file configuration.
 *
 * @version 1.0.0
 */
@SuppressWarnings("FieldMayBeFinal")
public class FileConfig {

    private SkyWars plugin;
    private File file;
    private FileConfiguration config;
    private String name;

    public FileConfig(SkyWars plugin, String file) {
        this.plugin = plugin;
        this.file = new File(this.plugin.getDataFolder(), file);
        this.name = file;
        this.config = new YamlConfiguration();
    }
    public FileConfig(SkyWars plugin, File file) {
        this.plugin = plugin;
        this.file = file;
        this.name = file.getName().replaceAll(".yml", "");
        this.config = new YamlConfiguration();
    }

    public String getFileName() {
        return this.file.getName();
    }

    public String getFilePath() {
        return this.file.getPath();
    }

    public File getFile() {
        return this.file;
    }

    public SkyWars getPlugin() {
        return plugin;
    }

    @SuppressWarnings("UnstableApiUsage")
    public void load() {
        plugin.getLogger().info("Loading file configuration: " + file.getName());
        try {
            if (!file.exists()) {
                if (plugin.getResource(file.getName()) != null) {
                    ByteStreams.copy(Objects.requireNonNull(plugin.getResource(file.getName())), new FileOutputStream(file));
                } else {
                    if (file.createNewFile()) {
                        plugin.getLogger().info("Creating file " + file.getName());
                    }
                }
            } else if (file.createNewFile()) {
                plugin.getLogger().info("Creating file " + file.getName());
            }
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // load
    // File file = new File(getDataFolder(), "file.yml");
    //
    //
    //        if (!file.exists()) {
    //            try (InputStream in = getResourceAsStream("file.yml")) {
    //                Files.copy(in, file.toPath());
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }

    public void save() {
        try {
            plugin.getLogger().info("Saving config.yml file " + name + "");
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.load();
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public ConfigurationSection getConfigSection(String path) {
        return this.config.getConfigurationSection(path);
    }

    public String getString(String key) {
        return Text.colorize(this.config.getString(key, null));
    }

    public String getString(String key, String def) {
        return Text.colorize(this.config.getString(key, def));
    }

    public List<String> getStringList(String key) {
        if (this.config.contains(key)) {
            return Text.colorize(this.config.getStringList(key));
        }
        return null;
    }

    public long getLong(String key) {
        return this.config.getLong(key);
    }

    public int getInt(String key) {
        return this.config.getInt(key);
    }

    public int getInt(String key, int def) {
        return config.getInt(key, def);
    }

    public double getDouble(String key) {
        return this.config.getDouble(key);
    }

    public double getDouble(String key, double def) {
        return this.config.getDouble(key, def);
    }

    public boolean getBoolean(String key) {
        return this.config.getBoolean(key);
    }

    public float getFloat(String key) {
        return (float) this.config.getDouble(key);
    }

    public void set(String key, Object value) {
        this.config.set(key, value);
    }

    public void set(String key, Location value) {
        this.config.set(key + ".World", Objects.requireNonNull(value.getWorld()).getName());
        this.config.set(key + ".X", value.getX());
        this.config.set(key + ".Y", value.getY());
        this.config.set(key + ".Z", value.getZ());
        this.config.set(key + ".Pitch", value.getPitch());
        this.config.set(key + ".Yaw", value.getYaw());
    }

    public Location getLocation(String path) {
        World world = Worlds.getWorld(getString(path + ".World"));
        double x = getDouble(path + ".X");
        double y = getDouble(path + ".Y");
        double z = getDouble(path + ".Z");
        float pitch = getFloat(path + ".Pitch");
        float yaw = getFloat(path + ".Yaw");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public boolean getBoolean(String key, boolean def) {
        return this.config.getBoolean(key, def);
    }
}
