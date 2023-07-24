package me.cocos.menu.helper;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatHelper {

    private ChatHelper() {
        throw new UnsupportedOperationException("You cannot do this!");
    }


    public static String colored(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> coloredList(List<String> text) {
        return text.stream().map(ChatHelper::colored).collect(Collectors.toList());
    }

}
