package me.cocos.menu;

import me.cocos.menu.annotations.Scheduled;
import me.cocos.menu.commands.Command;
import me.cocos.menu.commands.CommandRegister;
import me.cocos.menu.holders.MenuHolder;
import me.cocos.menu.listeners.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class Menu {

    private final Inventory inventory;
    private final MenuHolder holder;
    private final Map<Integer, Consumer<InventoryClickEvent>> actions;

    private static final Plugin plugin = JavaPlugin.getProvidingPlugin(Menu.class);

    static {
        PluginManager pluginManager = Bukkit.getPluginManager();
        Stream.of(
                new InventoryClickListener()
        ).forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

    public Menu(String title, int rows) {
        this.holder = new MenuHolder(this);
        this.inventory = Bukkit.createInventory(holder, rows*9, title);
        this.actions = new HashMap<>();
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

    public abstract void onInventoryClick(InventoryClickEvent event, Player player);

    protected void addItems(ItemStack... items) {
        this.inventory.addItem(items);
    }

    protected void clearInventory() {
        this.inventory.clear();
    }


    protected void setItem(int slot, ItemStack item) {
        this.setItem(slot, item, null);
    }




    protected void setItem(int slot, ItemStack item, Consumer<InventoryClickEvent> action) {
        this.actions.put(slot, action);
        this.inventory.setItem(slot, item);
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
}