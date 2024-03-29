package me.cocos.menu.builder.impl;

import me.cocos.menu.Menu;
import me.cocos.menu.builder.Builder;
import me.cocos.menu.type.MenuType;
import me.cocos.menu.helper.GuiHelper;
import me.cocos.menu.menu.SimpleMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class MenuBuilder implements Builder<Menu> {

    private final Menu menu;

    public MenuBuilder(MenuType menuType, String title, int rows, boolean disposable) {
       if (menuType == MenuType.SIMPLE) {
           this.menu = new SimpleMenu(title, rows, disposable);
       } else {
           this.menu = null;
       }
    }

    public MenuBuilder onUpdate(Consumer<Menu> menuConsumer) {
        menuConsumer.accept(menu);
        return this;
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

    public MenuBuilder blockPlayerInventory(boolean value) {
        this.menu.setBlockPlayerInventory(value);
        return this;
    }

    public MenuBuilder addItems(ItemStack... items) {
        menu.addItems(items);
        return this;
    }

    public MenuBuilder setItem(ItemStack item, int slot, BiConsumer<InventoryClickEvent, Player> event) {
        menu.setItem(item, slot).onInventoryClick(event);
        return this;
    }

    public MenuBuilder setItem(ItemStack item, int slot) {
        menu.setItem(item, slot);
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

    public static MenuBuilder from(MenuType menuType, String title, int rows, boolean disposable) {
        return new MenuBuilder(menuType, title, rows, disposable);
    }

    @Override
    public Menu build() {
        return menu;
    }
}
