package net.mythiccraft.skywars.item;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * @author Taylor Hughes
 */
public class Kit implements Buyable {

    private String name;
    private boolean buyable;
    private Rarity rarity;
    private int cost;
    private String currency;
    private ItemStack display;
    private ItemStack helmet, chestplate, leggings, boots;
    private Map<Integer, ItemStack> items;

    public Kit(String name, boolean buyable, Rarity rarity, int cost, String currency) {
        this.name = name;
        this.buyable = buyable;
        this.rarity = rarity;
        this.cost = cost;
        this.currency = currency;
    }

    public Kit(String name, boolean buyable, Rarity rarity, int cost, String currency, ItemStack display, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, Map<Integer, ItemStack> items) {
        this.name = name;
        this.buyable = buyable;
        this.rarity = rarity;
        this.cost = cost;
        this.currency = currency;
        this.display = display;
        this.helmet = helmet;
        this.boots = boots;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.items = items;
    }

    @Override
    public boolean isBuyable() {
        return buyable;
    }

    @Override
    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public void setDisplay(ItemStack display) {
        this.display = display;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
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
        return ItemType.KIT;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getDisplay() {
        return display;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public Map<Integer, ItemStack> getItems() {
        return items;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setItems(Map<Integer, ItemStack> items) {
        this.items = items;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }
}
