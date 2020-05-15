/*
 * Copyright (c) 2018 taylorhughes719.
 */

package net.mythiccraft.skywars.item;

import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Rarities for obtainables/items.
 *
 * @author Taylor Hughes
 */
public enum Rarity {
    COMMON(1, "§7Common"),
    UNCOMMON(2, "§8Uncommon"),
    RARE(3, "§bRare"),
    EPIC(4, "§5Epic"),
    LEGENDARY(5, "§6Legendary"),
    GODLIKE(6, "§4Godlike");

    private final int tier;
    private final String display;

    Rarity(int tier, String display) {
        this.tier = tier;
        this.display = display;
    }

    public final int getTier() {
        return this.tier;
    }

    @NotNull
    public final String getDisplay() {
        return this.display;
    }

    @NotNull
    public static Rarity fromTier(int tier) {
        return Arrays.stream(Rarity.values()).filter(rarity -> rarity.tier == tier).findFirst().orElse(Rarity.COMMON);
    }

    public static Rarity upgrade(Rarity rarity) {
        if (rarity == null) {
            return null;
        }
        int tier = rarity.getTier();
        return tier == 6 ? Rarity.GODLIKE : fromTier(tier + 1);
    }

    public static Rarity getRarity(ItemStack item) {
        if (hasRarity(item)) {

        }
        return null;
    }

    public static boolean hasRarity(ItemStack item) {
        //return Items.verify(item) && Items.loreContains(item, "rarity:");
        return false;
    }
}
