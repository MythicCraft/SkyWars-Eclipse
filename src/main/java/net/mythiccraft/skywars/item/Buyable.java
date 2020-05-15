package net.mythiccraft.skywars.item;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an object that can or cannot be bought.
 *
 * @author Taylor Hughes
 */
public interface Buyable {

    boolean isBuyable();

    double getCost();

    String requiredCurrency();

    Object getItem();

    ItemStack getDisplayItem();

    ItemType getItemType();
}

