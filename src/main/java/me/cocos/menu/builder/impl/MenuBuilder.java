package me.cocos.menu.builder.impl;

import me.cocos.menu.Menu;
import me.cocos.menu.builder.Builder;
import me.cocos.menu.enums.MenuType;
import me.cocos.menu.helpers.GuiHelper;
import me.cocos.menu.menus.SimpleMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class MenuBuilder implements Builder<Menu> {

    private final Menu menu;

    public MenuBuilder(MenuType menuType, String title, int rows) {
       if (menuType == MenuType.SIMPLE) {
           menu = new SimpleMenu(title, rows);
       } else {
           menu = null;
       }
    }

    public MenuBuilder onInventoryClick(BiConsumer<InventoryClickEvent, Player> action) {
        this.menu.setOnInventoryClick(action);
        return this;
    }

    public MenuBuilder onInventoryDrag(BiConsumer<InventoryDragEvent, Player> action) {
        this.menu.setOnInventoryDrag(action);
        return this;
    }

    public MenuBuilder onInventoryClose(BiConsumer<InventoryCloseEvent, Player> action) {
        this.menu.setOnInventoryClose(action);
        return this;
    }

    public MenuBuilder addItems(ItemStack... items) {
        menu.addItems(items);
        return this;
    }

    public MenuBuilder setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> event) {
        menu.setItem(slot, item, event);
        return this;
    }

    public MenuBuilder setItem(int slot, ItemStack item) {
        menu.setItem(slot, item);
        return this;
    }

    public MenuBuilder border(ItemStack item) {
        GuiHelper.border(menu.getInventory(), item);
        return this;
    }

    public MenuBuilder fill(ItemStack item) {
        GuiHelper.fill(menu.getInventory(), item);
        return this;
    }

    public static MenuBuilder from(MenuType menuType, String title, int rows) {
        return new MenuBuilder(menuType, title, rows);
    }

    @Override
    public Menu build() {
        return menu;
    }
}
