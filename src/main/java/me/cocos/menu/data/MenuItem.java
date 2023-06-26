package me.cocos.menu.data;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public final class MenuItem {

    private final ItemStack item;

    private BiConsumer<InventoryClickEvent, Player> onInventoryClick;

    public MenuItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public BiConsumer<InventoryClickEvent, Player> getOnInventoryClick() {
        return onInventoryClick;
    }

    public void onInventoryClick(BiConsumer<InventoryClickEvent, Player> onInventoryClick) {
        this.onInventoryClick = onInventoryClick;
    }
}
