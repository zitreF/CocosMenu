package me.cocos.menu.helpers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.stream.Collectors;

public final class ChatHelper {

    private ChatHelper() {

    }

    public static final LegacyComponentSerializer LEGACY =
            LegacyComponentSerializer.builder().hexColors().character('&').hexCharacter('#').useUnusualXRepeatedCharacterHexFormat().build();

    public static Component colored(String text) {
        return LEGACY.deserialize(text);
    }

    public static List<Component> colored(List<String> text) {
        return text.stream().map(ChatHelper::colored).collect(Collectors.toList());
    }

}
