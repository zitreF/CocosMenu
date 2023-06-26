<p align="center">
  <img src="https://user-images.githubusercontent.com/71133191/218276557-aee9fe5f-0a99-4a0f-bda5-8cc94bfe5586.png" width=450">
</p>

# Maven:
```xml
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>

<dependency>
	<groupId>com.github.zitreF</groupId>
	<artifactId>CocosMenu</artifactId>
	<version>2.4</version>
</dependency>
```
															     
# Gradle:
```kotlin
repositories {
	maven { url 'https://jitpack.io' }												     
}

dependencies {
    implementation 'com.github.zitreF:CocosMenu:2.4'
}
```


# Example usage:

```java
public class TestPlugin extends JavaPlugin {
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
        @Scheduled(async = true, delay = 20, repeat = 20)
        public void update() {
            GuiHelper.fillBottom(getInventory(), new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            ItemBuilder builder = ItemBuilder.from(Material.PLAYER_HEAD).withSkullOwner("QLNUS");
            this.addItems(builder.buildItem());
            this.setItem(10, new ItemStack(Material.DIRT), (event) -> {
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("Hello world!");
            });
            this.onInventoryClick((event, player) -> {
                event.setCancelled(true);
            });
        }
    }
}
```

