package me.cocos.menu.builder;

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
import java.util.List;

public final class ItemBuilder {

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
        this.meta.setDisplayName(ChatHelper.fixText(itemName));
        return this;
    }

    public ItemBuilder withLore(List<String> lore) {
        this.meta.setLore(ChatHelper.fixText(lore));
        return this;
    }


    public ItemBuilder withDurability(int durability) {
        this.itemStack.setDurability((short) (this.itemStack.getType().getMaxDurability() - durability));
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        (lores).add(ChatHelper.fixText(lore));
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

    public ItemBuilder withEnchantment() {
        this.meta.addEnchant(Enchantment.OXYGEN, 1, true);
        this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder withSkullOwner(String ownerName) {
        ((SkullMeta) this.meta).setOwner(ownerName);
        return this;
    }

    public ItemBuilder addColor(Color color) {
        ((LeatherArmorMeta) this.meta).setColor(color);
        this.meta.addItemFlags(ItemFlag.HIDE_DYE);
        return this;
    }

    public ItemStack buildItem() {
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }
}