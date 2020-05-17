package net.mythiccraft.skywars.menu;

import net.mythiccraft.skywars.SkyWars;

import net.mythiccraft.skywars.config.GUIConfig;
import net.mythiccraft.skywars.util.Items;
import net.mythiccraft.skywars.util.Loadable;
import net.mythiccraft.skywars.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

/**
 * @author Taylor Hughes
 */
@SuppressWarnings("FieldMayBeFinal")
public class GUI implements GUIHolder, Loadable {

    private SkyWars plugin;
    private GUIConfig config;
    private String name;
    private String title;
    private Inventory inventory;
    private int size;
    private Map<Integer, Item> items;
    private Set<String> viewers;

    public GUI(SkyWars plugin, String name) {
        this.plugin = plugin;
        this.items = new HashMap<>();
        this.viewers = new HashSet<>();
        this.name = name;
        this.config = new GUIConfig(plugin, new File(plugin.getDataFolder() + "/guis/" + name + ".yml"));
    }

    /**
     * Called when you want to load the GUI.
     */
    @Override
    public void load() {
        this.config.load();
        this.config.build();
        this.setItems(this.config.getItems());
    }

    public SkyWars getPlugin() {
        return plugin;
    }

    public GUIConfig getConfig() {
        return config;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    @Nullable
    public Item getItem(int slot) {
        return items.get(slot);
    }

    public void setItem(int slot, Item item) {
        if (items.get(slot) != null) {
            items.remove(slot);
        }
        items.put(slot, item);
    }

    public void setItems(Map<Integer, Item> items) {
        this.items = items;
    }

    public void removeItem(int slot) {
        items.remove(slot);
    }

    public void clearItems() {
        items.clear();
    }

    public void forceCloseAll() {
        if (!viewers.isEmpty()) {
            viewers.stream().map(Bukkit::getPlayer).filter(Objects::nonNull).forEach(HumanEntity::closeInventory);
        }
    }

    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && getItem(e.getSlot()) != null) {
            Objects.requireNonNull(getItem(e.getSlot())).onClick(e);
        }
    }

    @Override
    @NotNull
    public GUI getGUI() {
        return this.config.build();
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        if (this.inventory == null) {
            build(null);
        }
        return inventory;
    }

    public Set<String> getViewers() {
        return viewers;
    }

    public void removeViewer(String s) {
        viewers.remove(s);
    }

    public boolean isViewing(Player player) {
        return viewers.contains(player.getName().toLowerCase());
    }

    public void open(Player player) {
        if (isViewing(player)) {
            return;
        }
        this.viewers.add(player.getName().toLowerCase());
        this.build(player);
        player.openInventory(this.inventory);
    }

    public void close(Player player) {
        if (isViewing(player)) {
            viewers.remove(player.getName().toLowerCase());
        }
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = Text.colorize(title);
    }

    public int getRows() {
        return size / 9;
    }

    public void build(Player player) {
        if (player == null || !isViewing(player)) {
            String finalTitle = player == null ? Text.colorize(this.title) : Text.colorize(this.title.replaceAll("%displayname%", player.getDisplayName()).replaceAll("%playername%", player.getName()));
            this.inventory = Bukkit.createInventory(this, this.size, finalTitle);
            for (int i : items.keySet()) {
                ItemStack item = (player == null) ? (items.get(i).getItem()) : Items.replaceInLore(items.getOrDefault(i, null).getItem(), "%displayname%", player.getDisplayName());
                if (item != null) {
                    if (player != null) {
                        item = Items.replaceInLore(item, "%playername%", player.getName());
                    }
                    this.inventory.setItem(i - 1, item);
                }
            }
        }
    }
}
