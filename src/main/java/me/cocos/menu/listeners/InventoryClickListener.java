package me.cocos.menu.listeners;

import me.cocos.menu.Menu;
import me.cocos.menu.holders.MenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public final class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getView().getTopInventory().getHolder() instanceof MenuHolder menuHolder)) return;
        Menu menu = menuHolder.menu();
        Consumer<InventoryClickEvent> action = menu.getActionBySlot(event.getSlot());
        if (action != null) action.accept(event);
        if (menu.getOnInventoryClick() != null) menu.getOnInventoryClick().accept(event, player);
    }
}