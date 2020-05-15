/*
 * Copyright (c) 2020 taylorhughes719.
 */

package net.mythiccraft.skywars.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class ItemStackBuilder {

    private Material material;
    private int amount;
    private short data;
    private MaterialData materialData;
    private ItemStackItemMetaBuilder metaBuilder;
    private Function<ItemStack, ItemMeta> itemMetaCreator = is -> {
        Preconditions.checkNotNull(is, "Null itemStack");
        if(metaBuilder != null) {
            return metaBuilder.build(is);
        }
        return is.getItemMeta();
    };
    private Map<Enchantment, Integer> enchantments = new HashMap<>();

    private ItemStackBuilder() {

    }

    private ItemStackBuilder(ItemStack itemStack) {
        Preconditions.checkNotNull(itemStack, "Null ItemStack");
        this.material = itemStack.getType();
        this.amount = itemStack.getAmount();
        this.data = itemStack.getDurability();
        if(itemStack.getData() != null) {
            this.materialData = itemStack.getData();
        }
        if(itemStack.getEnchantments() != null) {
            this.enchantments = itemStack.getEnchantments();
        }
    }

    public static ItemStackBuilder create() {
        return new ItemStackBuilder();
    }

    public static ItemStackBuilder create(ItemStack stack) {
        return new ItemStackBuilder(stack);
    }

    public ItemStackBuilder setMaterial(Material material) {
        Preconditions.checkNotNull(material, "Null material");
        this.material = material;
        return this;
    }

    public ItemStackBuilder changeAmount(int change) {
        this.amount = change;
        return this;
    }

    public ItemStackBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStackBuilder setData(short data) {
        this.data = data;
        return this;
    }

    public ItemStackBuilder setData(MaterialData data) {
        Preconditions.checkNotNull(data, "Null Material Data");
        this.materialData = data;
        return this;
    }

    public ItemStackBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        Preconditions.checkNotNull(enchantments, "Null enchantments");
        enchantments.forEach((ench, l) -> Preconditions.checkNotNull(ench, "Null enchantment in enchantment's collection"));
        this.enchantments = new HashMap<>(enchantments);
        return this;
    }

    public ItemStackBuilder addEnchantment(Enchantment enchantment, int level) {
        Preconditions.checkNotNull(enchantment, "Null enchantment");
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemStackItemMetaBuilder withItemMeta() {
        ItemStackItemMetaBuilder metaBuilder = new ItemStackItemMetaBuilder(this);
        this.metaBuilder = metaBuilder;
        return metaBuilder;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount, data);
        if(materialData != null) {
            itemStack.setData(materialData);
        }
        itemStack.addUnsafeEnchantments(enchantments);
        ItemMeta createdMeta = itemMetaCreator.apply(itemStack);
        return itemStack.setItemMeta(createdMeta) ? itemStack : itemStack;
    }

    public static class ItemStackItemMetaBuilder {

        private ItemStackBuilder innerBuilder;
        private String name;
        private ItemMeta userMeta;
        private List<String> lore = new ArrayList<>();
        private List<ItemFlag> itemFlags = new ArrayList<>();
        private boolean unbreakable = false;

        public ItemStackItemMetaBuilder(ItemStackBuilder innerBuilder) {
            this.innerBuilder = innerBuilder;
        }

        public ItemStackItemMetaBuilder setItemMeta(ItemMeta meta) {
            this.userMeta = meta;
            return this;
        }

        public ItemStackItemMetaBuilder setName(String name) {
            Preconditions.checkNotNull(name, "Null name");
            this.name = Text.colorize(name);
            return this;
        }

        public ItemStackItemMetaBuilder addBlankLore() {
            return this.addLore(" ");
        }

        public ItemStackItemMetaBuilder addLore(String... lore) {
            Preconditions.checkNotNull(lore, "Null lore");
            return this.addLore(Arrays.asList(lore));
        }

        public ItemStackItemMetaBuilder addLore(List<String> lore) {
            Preconditions.checkNotNull(lore, "Null lore");
            this.lore.addAll(Text.colorize(lore));
            return this;
        }

        public ItemStackItemMetaBuilder setLore(String... lore) {
            Preconditions.checkNotNull(lore, "Null lore");
            this.lore = Lists.newArrayList(Text.colorize(lore));
            return this;
        }

        public ItemStackItemMetaBuilder setLore(List<String> lore) {
            Preconditions.checkNotNull(lore, "Null lore");
            this.lore = Lists.newArrayList(Text.colorize(lore));
            return this;
        }

        public ItemStackItemMetaBuilder setItemFlags(List<ItemFlag> itemFlags) {
            Preconditions.checkNotNull(itemFlags, "itemFlags is null");
            this.itemFlags = itemFlags;
            return this;
        }

        public ItemStackBuilder and() {
            return innerBuilder;
        }

        public ItemStackItemMetaBuilder setUnbreakable(boolean flag) {
            this.unbreakable = flag;
            return this;
        }

        private ItemMeta build(ItemStack itemStack) {
            ItemMeta metaToUse;
            if(userMeta != null) {
                metaToUse = userMeta;
            } else {
                metaToUse = itemStack.getItemMeta();
            }
            metaToUse.setLore(lore);
            if(name != null) {
                metaToUse.setDisplayName(name);
            }
            if(unbreakable) {
                metaToUse.setUnbreakable(unbreakable);
            }
            if(itemFlags.size() > 0) {
                metaToUse.addItemFlags(itemFlags.toArray(new ItemFlag[0]));
            }
            return metaToUse;
        }
    }




}
