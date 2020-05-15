/*
 * Copyright (c) 2020 Taylor Hughes (taylorhughes719).
 */

package net.mythiccraft.skywars.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Items {

    public static boolean verify(ItemStack item) {
        return item != null && item.getType() != Material.AIR;
    }

    public static boolean loreContains(@NotNull ItemStack item, String text) {
        return item.getItemMeta().hasLore() && item.getItemMeta().getLore().stream().anyMatch(line -> Text.decolorize(line).equalsIgnoreCase(text) || Text.decolorize(line).toLowerCase().contains(text.toLowerCase()));
    }

    public static ItemStack replaceInLore(ItemStack item, String text, String replacement) {
        ItemStack result = null;
        if (item != null) {
            result = item;
            ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
            assert meta != null;
            if (meta.hasLore()) {
                List<String> newLore = new ArrayList<>();
                for (String line : Objects.requireNonNull(meta.getLore())) {
                    newLore.add(line.replaceAll(text, Text.colorize(replacement)));
                }
                meta.setLore(newLore);
                result.setItemMeta(meta);
            }
        }
        return result;
    }
}
