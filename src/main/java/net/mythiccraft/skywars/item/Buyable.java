package net.mythiccraft.skywars.item;

import org.bukkit.inventory.ItemStack;

/**
 * Represents an object that can or cannot be bought.
 *
 * @author Taylor Hughes
 */
public interface Buyable {

    boolean isBuyable();

    int getCost();

    String requiredCurrency();

    ItemStack getDisplayItem();

    Rarity getRarity();

    ItemType getType();
}

