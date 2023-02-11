package me.cocos.menu.helpers;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatHelper {

    public static final LegacyComponentSerializer LEGACY =
            LegacyComponentSerializer.builder().hexColors().character('§').hexCharacter('#').useUnusualXRepeatedCharacterHexFormat().build();

    public static String fixText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> fixText(List<String> text) {
        return text.stream().map(ChatHelper::fixText).collect(Collectors.toList());
    }

}
