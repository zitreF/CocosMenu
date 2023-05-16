<p align="center">
  <img src="https://user-images.githubusercontent.com/71133191/218276557-aee9fe5f-0a99-4a0f-bda5-8cc94bfe5586.png" width=450">
</p>

# Maven:
```xml

<repositories>
    <repository>
        <id>transtv-repository</id>
        <name>TransTV Repository</name>
        <url>https://repo.transtv.pl/releases</url>
    </repository>
</repositories>


<dependencies>
    <dependency>
        <groupId>me.cocos</groupId>
        <artifactId>menu</artifactId>
        <version>2.2-SNAPSHOT</version>
    </dependency>
</dependencies>
```
# Gradle:

```kotlin
repositories {
    maven {
        url = uri("https://repo.transtv.pl/releases")
    }
}
dependencies {
    implementation("me.cocos:menu:2.2-SNAPSHOT")
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
