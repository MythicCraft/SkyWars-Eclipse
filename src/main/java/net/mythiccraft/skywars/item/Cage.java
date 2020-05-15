package net.mythiccraft.skywars.item;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

/**
 * @author Taylor Hughes
 */
public class Cage {

    private String name;
    private double cost;
    private ItemStack item;
    private byte data;

    public Cage(String name, double cost, ItemStack item) {
        this.name = name;
        this.cost = cost;
        this.item = item;
        this.data = (byte) 0;
    }

    @Deprecated
    public Cage(String name, double cost, ItemStack item, byte data) {
        this.name = name;
        this.cost = cost;
        this.item = item;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void build(Location loc) {
        //loc.getBlock().getRelative(0, -1, 0).setBlockData().setTypeIdAndData(item.getType().getId(), data, false);
    }

    public boolean isFree() {
        return this.cost <= 0;
    }
}
