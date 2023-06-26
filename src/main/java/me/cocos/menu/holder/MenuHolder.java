package me.cocos.menu.holder;

import me.cocos.menu.Menu;
import me.cocos.menu.command.Command;
import me.cocos.menu.helper.ChatHelper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MenuHolder implements InventoryHolder {
    private final Menu menu;

    public MenuHolder(Menu menu) {
        this.menu = menu;
    }

    public void run(Player player, Command command) {
        if (command.permission() != null && (command.permission().isEmpty() || player.hasPermission(command.permission()))) {
            player.openInventory(menu.getInventory());
            return;
        }
        player.sendMessage(ChatHelper.coloredText(command.permissionMessage()));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return menu.getInventory();
    }

    public Menu menu() {
        return menu;
    }
}