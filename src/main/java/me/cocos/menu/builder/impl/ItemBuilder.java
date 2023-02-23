package me.cocos.menu.builder.impl;

import me.cocos.menu.builder.Builder;
import me.cocos.menu.helpers.ChatHelper;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ItemBuilder implements Builder<ItemStack> {

    private final ItemMeta meta;
    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this(new ItemStack(material, 1));
    }

    public ItemBuilder(ItemStack item) {
        this.itemStack = new ItemStack(item);
        this.meta = item.getItemMeta();
    }

    public static ItemBuilder from(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder from(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public ItemBuilder withItemName(String itemName) {
        this.meta.setDisplayName(ChatHelper.coloredText(itemName));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.meta.setLore(lore.stream().map(ChatHelper::coloredText).collect(Collectors.toList()));
        return this;
    }
    public ItemBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }


    public ItemBuilder withDurability(int durability) {
        this.itemStack.setDurability((short) (this.itemStack.getType().getMaxDurability() - durability));
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        lores.add(ChatHelper.coloredText(lore));
        this.meta.setLore(lores);
        return this;
    }

    public ItemBuilder withAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder withItemData(short data) {
        this.itemStack.setDurability(data);
        return this;
    }

    public ItemBuilder withEnchantment(Enchantment enchantment, int level) {
        this.meta.addEnchant(enchantment, level, true);
        return this;
    }
    public ItemBuilder flag(ItemFlag... flags) {
        this.meta.addItemFlags(flags);
        return this;
    }
    public ItemBuilder withSkullOwner(String ownerName) {
        ((SkullMeta) this.meta).setOwner(ownerName);
        return this;
    }

    public ItemBuilder addColor(Color color) {
        if (!(this.meta instanceof LeatherArmorMeta)) {
            throw new IllegalArgumentException("Item isn't armor!");
        }
        ((LeatherArmorMeta) this.meta).setColor(color);
        return this;
    }

    @Override
    public ItemStack build() {
        return itemStack;
    }
}