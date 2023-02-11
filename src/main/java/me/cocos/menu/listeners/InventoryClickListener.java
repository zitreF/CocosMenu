package me.cocos.menu.listeners;

import me.cocos.menu.holders.MenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public final class InventoryClickListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTopInventory().getHolder() == null || !(event.getView().getTopInventory().getHolder() instanceof MenuHolder menuHolder)) return;
        menuHolder.menu().onInventoryClick(event, player);
    }
}