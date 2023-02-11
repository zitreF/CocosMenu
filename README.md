<p align="center">
  <img src="https://cdn.discordapp.com/attachments/795294605850574848/1074026139690995834/kokos.jpg" width=450>
</p>
# Example usage:

```java

    @Override
    public void onEnable() {
        new TestMenu();
    }

@Command(name = "aha", aliases = "aha2", permission = "xd.xd", permissionMessage = "&cNie posiadasz permisji!")
public class TestMenu extends Menu {

    public TestMenu() {
        super("test", 3);
        update();
    }

    @Override
    public void update() {
        GuiHelper.fillBottom(getInventory(), new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemBuilder builder = ItemBuilder.from(Material.PLAYER_HEAD).withSkullOwner("QLNUS");
        this.addItems(builder.buildItem());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent inventoryClickEvent, Player player) {
        inventoryClickEvent.setCancelled(true);
    }
}
```
