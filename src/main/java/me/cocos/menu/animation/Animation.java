package me.cocos.menu.animation;

import me.cocos.menu.Menu;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class Animation<T> {

    private T type;
    private final int period;
    private final int maxIndex;
    private int currentIndex;
    private final Map<Integer, Function<T, T>> animations;

    public Animation(int maxIndex, int period) {
        this.maxIndex = maxIndex;
        this.period = period;
        this.currentIndex = 0;
        this.animations = new HashMap<>();
    }

    public T getType() {
        return type;
    }

    public void iterate() {
        if (currentIndex == maxIndex) {
            currentIndex = 0;
            return;
        }
        this.currentIndex++;
        this.getAnimation(currentIndex).apply(type);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setAnimation(int index, Function<T, T> function) {
        if (index > maxIndex) {
            throw new IndexOutOfBoundsException("Current index is bigger than maximum index!");
        }
        animations.put(index, function);
    }

    public void start(boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(Menu.PLUGIN, this::iterate, 0L, period);
        } else {
            Bukkit.getScheduler().runTaskTimer(Menu.PLUGIN, this::iterate, 0L, period);
        }
    }

    public Function<T, T> getAnimation(int index) {
        return this.animations.get(index);
    }
}
