package me.cocos.menu.listener;

import me.cocos.menu.Menu;
import me.cocos.menu.holder.MenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public final class InventoryDragListener implements Listener {

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getView().getTopInventory().getHolder() instanceof MenuHolder)) return;
        MenuHolder menuHolder = (MenuHolder) event.getView().getTopInventory().getHolder();
        Menu menu = menuHolder.menu();
        if (menu.getOnInventoryDrag() != null) menu.getOnInventoryDrag().accept(event, player);
    }
}
