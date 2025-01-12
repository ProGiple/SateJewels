Connect API with maven:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.ProGiple</groupId>
    <artifactId>SateJewels</artifactId>
    <version>1.1.4</version>
    <scope>provided</scope>
</dependency>
```

Use SateJewels in your plugins:
```java
SJAPI sjapi = SateJewels.getPlugin().getSjapi();
sjapi.getJewels(OfflinePlayer player); // get player balance
sjapi.giveJewels(OfflinePlayer player); // give jewels
sjapi.setJewels(OfflinePlayer player); // set jewels
sjapi.removeJewels(OfflinePlayer player); // remove jewels
etc..
```
