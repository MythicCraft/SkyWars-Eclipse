package net.mythiccraft.skywars.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taylor Hughes
 */
public abstract class MenuBuilder implements GUIHolder {

    private String title;
    private int size;
    private Map<Integer, ItemStack> items;
    private Inventory inventory;

    public MenuBuilder(String title) {
        this(title, 1);
    }

    public MenuBuilder(int rows) {
        this("", rows);
    }

    public MenuBuilder(String title, int rows) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.size = rows * 9;
        this.items = new HashMap<>();
    }

    public MenuBuilder setTitle(String title) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        return this;
    }

    public MenuBuilder setSize(int rows) {
        this.size = rows * 9;
        return this;
    }

    public MenuBuilder clearItems() {
        this.items.clear();
        return this;
    }

    public MenuBuilder addItem(int slot, ItemStack item) {
        if (this.items.containsKey(slot)) {
            this.items.replace(slot, item);
        } else {
            this.items.put(slot, item);
        }
        return this;
    }

    public MenuBuilder removeItem(int slot) {
        this.items.remove(slot);
        return this;
    }

    public Inventory build() {
        this.inventory = Bukkit.createInventory(this, this.size, this.title);
        for (int i = 0; i < this.size; i++) {
            if (this.items.containsKey(i)) {
                this.inventory.setItem(i, this.items.get(i));
            }
        }
        return this.inventory;
    }

    /**
     * Get the object's inventory.
     *
     * @return The inventory.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
