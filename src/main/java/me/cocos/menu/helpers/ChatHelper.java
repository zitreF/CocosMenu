package me.cocos.menu.helpers;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatHelper {

    public static String fixText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static List<String> fixText(List<String> text) {
        return text.stream().map(ChatHelper::fixText).collect(Collectors.toList());
    }

}
