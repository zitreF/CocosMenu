package me.cocos.menu.builder;

public interface Builder<T> {
    T build();

    default T buildFrom(T instance) {
        return instance != null ? buildFrom(instance, this) : build();
    }

    default T buildFrom(T instance, Builder<T> builder) {
        if (builder != null && instance != null) {
            return builder.buildFrom(instance);
        } else {
            return instance;
        }
    }
}
