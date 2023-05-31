package me.cocos.menu;

import me.cocos.menu.annotations.Scheduled;
import me.cocos.menu.commands.Command;
import me.cocos.menu.commands.CommandRegister;
import me.cocos.menu.helpers.ChatHelper;
import me.cocos.menu.holders.MenuHolder;
import me.cocos.menu.listeners.InventoryClickListener;
import me.cocos.menu.listeners.InventoryCloseListener;
import me.cocos.menu.listeners.InventoryDragListener;
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class Menu {

    private final Inventory inventory;
    private final MenuHolder holder;
    private final Map<Integer, Consumer<InventoryClickEvent>> actions;

    private BiConsumer<InventoryDragEvent, Player> onInventoryDrag;
    private BiConsumer<InventoryCloseEvent, Player> onInventoryClose;
    private BiConsumer<InventoryClickEvent, Player> onInventoryClick;
    private boolean blockPlayerInventory;

    private static final Plugin plugin = JavaPlugin.getProvidingPlugin(Menu.class);

    static {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Stream.of(
                new InventoryClickListener(),
                new InventoryCloseListener(),
                new InventoryDragListener()
        ).forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

    public Menu(String title, int rows) {
        this.holder = new MenuHolder(this);
        this.inventory = Bukkit.createInventory(holder, rows*9, ChatHelper.coloredText(title));
        this.actions = new HashMap<>();
        this.blockPlayerInventory = true;
        try {
            Method method = this.getClass().getMethod("update");
            if (method.isAnnotationPresent(Scheduled.class)) {
                Scheduled scheduled = method.getAnnotation(Scheduled.class);
                if (scheduled.async()) {
                    plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, this::update, scheduled.delay(), scheduled.repeat());
                } else {
                    plugin.getServer().getScheduler().runTaskTimer(plugin, this::update, scheduled.delay(), scheduled.repeat());
                }
            }
            if (this.getClass().isAnnotationPresent(Command.class)) {
                CommandRegister.register(this);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void update();

    public void dispose() {
        this.inventory.clear();
        this.onInventoryDrag = null;
        this.onInventoryClose = null;
        this.onInventoryClick = null;
        this.actions.clear();
    }

    public void addItems(ItemStack... items) {
        this.inventory.addItem(items);
    }

    protected void clearInventory() {
        this.inventory.clear();
    }

    public void setItems(ItemStack item, int... slots) {
        for (int slot : slots) {
            this.setItem(slot, item);
        }
    }

    public void setItem(int slot, ItemStack item) {
        this.setItem(slot, item, null);
    }

    public void setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> action) {
        this.actions.put(slot, action);
        this.inventory.setItem(slot, item);
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

    public MenuHolder getHolder() {
        return holder;
    }

    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Consumer<InventoryClickEvent> getActionBySlot(int slot) {
        return actions.get(slot);
    }
}