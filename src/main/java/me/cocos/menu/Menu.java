package me.cocos.menu;

import me.cocos.menu.data.MenuItem;
import me.cocos.menu.helper.ChatHelper;
import me.cocos.menu.holder.MenuHolder;
import me.cocos.menu.listener.InventoryClickListener;
import me.cocos.menu.listener.InventoryCloseListener;
import me.cocos.menu.listener.InventoryDragListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public abstract class Menu {

    private final Inventory inventory;
    private final MenuHolder holder;
    private final Map<Integer, MenuItem> actions;

    private BiConsumer<InventoryDragEvent, Player> onInventoryDrag;
    private BiConsumer<InventoryCloseEvent, Player> onInventoryClose;
    private BiConsumer<InventoryClickEvent, Player> onInventoryClick;
    private boolean blockPlayerInventory;
    private final boolean disposable;

    public static final Plugin PLUGIN = JavaPlugin.getProvidingPlugin(Menu.class);

    static {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Stream.of(
                new InventoryClickListener(),
                new InventoryCloseListener(),
                new InventoryDragListener()
        ).forEach(listener -> pluginManager.registerEvents(listener, PLUGIN));
    }

    public Menu(String title, int rows, boolean disposable) {
        this.holder = new MenuHolder(this);
        this.inventory = Bukkit.createInventory(holder, rows*9, ChatHelper.colored(title));
        this.actions = new HashMap<>();
        this.blockPlayerInventory = true;
        this.disposable = disposable;
    }

    public void dispose() {
        this.inventory.clear();
        this.onInventoryDrag = null;
        this.onInventoryClose = null;
        this.onInventoryClick = null;
        this.actions.clear();
    }

    public void addItems(MenuItem... items) {
        for (MenuItem item : items) {
            int firstEmpty = inventory.firstEmpty();
            this.setItem(item, firstEmpty);
        }
    }

    public void addItems(ItemStack... items) {
        this.inventory.addItem(items);
    }

    protected void clearInventory() {
        this.inventory.clear();
    }

    public MenuItem setItems(ItemStack item, int... slots) {
        MenuItem menuItem = new MenuItem(item);
        for (int slot : slots) {
            this.setItem(menuItem, slot);
        }
        return menuItem;
    }

    public void setItems(MenuItem item, int... slots) {
        for (int slot : slots) {
            this.setItem(item, slot);
        }
    }

    public MenuItem setItem(ItemStack item, int slot) {
        MenuItem menuItem = new MenuItem(item);
        this.setItem(menuItem, slot);
        return menuItem;
    }

    public void setItem(MenuItem item, int slot) {
        this.actions.put(slot, item);
        this.inventory.setItem(slot, item.getItem());
    }

    public BiConsumer<InventoryDragEvent, Player> getOnInventoryDrag() {
        return onInventoryDrag;
    }

    public void setOnInventoryDrag(BiConsumer<InventoryDragEvent, Player> onInventoryDrag) {
        this.onInventoryDrag = onInventoryDrag;
    }

    public BiConsumer<InventoryCloseEvent, Player> getOnInventoryClose() {
        return onInventoryClose;
    }

    public void setOnInventoryClose(BiConsumer<InventoryCloseEvent, Player> onInventoryClose) {
        this.onInventoryClose = onInventoryClose;
    }

    public BiConsumer<InventoryClickEvent, Player> getOnInventoryClick() {
        return onInventoryClick;
    }

    public void setOnInventoryClick(BiConsumer<InventoryClickEvent, Player> onInventoryClick) {
        this.onInventoryClick = onInventoryClick;
    }

    public boolean isBlockPlayerInventory() {
        return blockPlayerInventory;
    }

    public void setBlockPlayerInventory(boolean blockPlayerInventory) {
        this.blockPlayerInventory = blockPlayerInventory;
    }

    public boolean isDisposable() {
        return disposable;
    }

    public MenuHolder getHolder() {
        return holder;
    }

    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public MenuItem getMenuItemBySlot(int slot) {
        return actions.get(slot);
    }
}