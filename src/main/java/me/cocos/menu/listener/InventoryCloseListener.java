package me.cocos.menu.listener;

import me.cocos.menu.Menu;
import me.cocos.menu.holder.MenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class InventoryCloseListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (!(event.getView().getTopInventory().getHolder() instanceof MenuHolder)) return;
        MenuHolder menuHolder = (MenuHolder) event.getView().getTopInventory().getHolder();
        Menu menu = menuHolder.menu();
        if (menu.getOnInventoryClose() != null) menu.getOnInventoryClose().accept(event, player);
        if (menu.isDisposable()) {
            menu.dispose();
        }
    }
}
