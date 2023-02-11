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
        <version>1.0-SNAPSHOT</version>
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
    implementation("me.cocos:menu:1.0-SNAPSHOT")
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
}
```
