package me.cocos.menu.builder;

import me.cocos.menu.helpers.ChatHelper;
import net.kyori.adventure.text.Component;
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

import static me.cocos.menu.helpers.ChatHelper.LEGACY;

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

    @Deprecated
    public ItemBuilder withItemName(String itemName) {
        this.meta.setDisplayName(ChatHelper.fixText(itemName));
        return this;
    }
    @Deprecated
    public ItemBuilder name(Component itemName) {
        this.meta.setDisplayName(LEGACY.serialize(itemName));
        return this;
    }


    @Deprecated
    public ItemBuilder withLore(List<String> lore) {
        return lore(lore.stream().map(LEGACY::deserialize).collect(Collectors.toList()));
    }

    @Deprecated
    public ItemBuilder withLore(String... lore) {
        return withLore(Arrays.asList(lore));
    }

    public ItemBuilder lore(List<Component> lore) {
        this.meta.setLore(lore.stream().map(LEGACY::serialize).collect(Collectors.toList()));
        return this;
    }
    public ItemBuilder lore(Component... lore) {
        return lore(Arrays.asList(lore));
    }



    public ItemBuilder withDurability(int durability) {
        this.itemStack.setDurability((short) (this.itemStack.getType().getMaxDurability() - durability));
        return this;
    }

    @Deprecated
    public ItemBuilder addLore(String lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        (lores).add(ChatHelper.fixText(lore));
        this.meta.setLore(lores);
        return this;
    }

    public ItemBuilder addLore(Component lore) {
        List<String> lores = this.meta.getLore() == null ? new ArrayList<>() : this.meta.getLore();
        lores.add(LEGACY.serialize(lore));
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

    public ItemStack buildItem() {
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }
}