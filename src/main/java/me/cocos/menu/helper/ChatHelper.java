package me.cocos.menu.helper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatHelper {

    private ChatHelper() {
        throw new UnsupportedOperationException("You cannot do this!");
    }

    public static final LegacyComponentSerializer LEGACY =
            LegacyComponentSerializer.builder()
                    .hexColors()
                    .character('&')
                    .hexCharacter('#')
                    .extractUrls()
                    .useUnusualXRepeatedCharacterHexFormat().build();

    public static Component colored(String text) {
        return LEGACY.deserialize(text);
    }

    public static String coloredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<Component> colored(List<String> text) {
        return text.stream().map(ChatHelper::colored).collect(Collectors.toList());
    }

}
