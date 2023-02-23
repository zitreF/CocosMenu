package me.cocos.menu.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String name();

    String[] aliases() default {""};

    String permission() default "";

    String permissionMessage() default "";
}