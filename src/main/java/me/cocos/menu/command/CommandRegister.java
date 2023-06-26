package me.cocos.menu.command;

import me.cocos.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public final class CommandRegister {

    private static CommandMap commandMap;

    static {
        try {
            Method m = Arrays.stream(Bukkit.class.getDeclaredMethods())
                    .filter(method -> method.getName().equalsIgnoreCase("getCommandMap"))
                    .findFirst()
                    .orElse(null);
            if (m != null) {
                final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                bukkitCommandMap.setAccessible(true);
                commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            } else {
                SimplePluginManager simplePluginManager = (SimplePluginManager) Bukkit.getPluginManager();
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(simplePluginManager);
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void register(Menu menu) {
        Command command = menu.getClass().getAnnotation(Command.class);
        commandMap.register(command.name(), new CommandReceiver(command, menu.getHolder()));
    }
    public CommandMap getCommandMap() {
        return commandMap;
    }
}
