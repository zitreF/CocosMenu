package me.cocos.menu.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();

    String[] aliases();

    String permission();

    String permissionMessage();
}