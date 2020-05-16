package net.mythiccraft.skywars.item;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

/**
 * @author Taylor Hughes
 */
public class Cage implements Buyable {

    private String name;
    private boolean buyable;
    private Rarity rarity;
    private int cost;
    private String currency;
    private ItemStack display;
    private ItemStack roof;
    private ItemStack floor;
    private ItemStack sides;

    public Cage(String name, boolean buyable, Rarity rarity) {
        this.name = name;
        this.buyable = buyable;
        this.rarity = rarity;
    }

    @Override
    public boolean isBuyable() {
        return buyable;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String requiredCurrency() {
        return currency;
    }

    @Override
    public ItemStack getDisplayItem() {
        return display;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public ItemType getType() {
        return ItemType.CAGE;
    }

    public ItemStack getFloor() {
        return floor;
    }

    public ItemStack getRoof() {
        return roof;
    }

    public ItemStack getSides() {
        return sides;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDisplay(ItemStack display) {
        this.display = display;
    }

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setFloor(ItemStack floor) {
        this.floor = floor;
    }

    public void setRoof(ItemStack roof) {
        this.roof = roof;
    }

    public void setSides(ItemStack sides) {
        this.sides = sides;
    }

    public void spawn(Location location) {
        location.subtract(0, 1, 0).getBlock().setType(floor.getType(), false);
        location.add(1, 0, 0).getBlock().setType(sides.getType(), false);
        location.add(0, 0, 1).getBlock().setType(sides.getType(), false);
        location.add(0, 0, -1).getBlock().setType(sides.getType(), false);
        location.add(-1, 0, 0).getBlock().setType(sides.getType(), false);
        location.add(1, 1, 0).getBlock().setType(sides.getType(), false);
        location.add(0, 1, 1).getBlock().setType(sides.getType(), false);
        location.add(0, 1, -1).getBlock().setType(sides.getType(), false);
        location.add(-1, 1, 0).getBlock().setType(sides.getType(), false);
        location.add(0, 2, 0).getBlock().setType(roof.getType(), false);
    }
}


