package me.cocos.menu.helpers;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class GuiHelper {

    private GuiHelper() {

    }

    public static void border(Inventory inventory, ItemStack itemStack) {
        int inventorySize = inventory.getSize();

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, itemStack);
        }
        for (int i = inventorySize - 9; i < inventorySize; i++) {
            inventory.setItem(i, itemStack);
        }
        for (int i = 0; i < inventorySize / 9; i++) {
            int slot = i *9;
            inventory.setItem(slot, itemStack);
        }
        for (int i = 0; i < inventorySize / 9; i++) {
            int slot = i * 9 + 8;
            inventory.setItem(slot, itemStack);
        }
    }
    public void fill(Inventory inventory, ItemStack itemStack) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }
    public static void fillTop(Inventory inventory, ItemStack itemStack) {
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, itemStack);
        }
    }
    public static void fillBottom(Inventory inventory, ItemStack itemStack) {
        for (int i = inventory.getSize() - 9; i < inventory.getSize(); i++) {
            inventory.setItem(i, itemStack);
        }
    }
}
