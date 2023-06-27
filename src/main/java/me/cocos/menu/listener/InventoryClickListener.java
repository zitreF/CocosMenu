package me.cocos.menu.listener;

import me.cocos.menu.Menu;
import me.cocos.menu.data.MenuItem;
import me.cocos.menu.holder.MenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public final class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!(event.getView().getTopInventory().getHolder() instanceof MenuHolder)) return;
        MenuHolder menuHolder = (MenuHolder) event.getView().getTopInventory().getHolder();
        Menu menu = menuHolder.menu();
        if (event.getClickedInventory() instanceof PlayerInventory && menu.isBlockPlayerInventory()) {
            event.setCancelled(true);
            return;
        }
        MenuItem action = menu.getMenuItemBySlot(event.getSlot());
        if (action != null && action.getOnInventoryClick() != null) action.getOnInventoryClick().accept(event, player);
        if (menu.getOnInventoryClick() != null) menu.getOnInventoryClick().accept(event, player);
    }
}