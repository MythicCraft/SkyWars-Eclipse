package net.mythiccraft.skywars.menu;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a GUI item
 */
public abstract class Item implements Clickable {

    private ItemStack item;
    private int slot;

    public Item(ItemStack item, int slot) {
        this.item = item;
        this.slot = slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
