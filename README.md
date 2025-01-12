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

Placeholders for PAPI:
```java
%satejewels_name_<name>% // returns a icon/name with id <name> from config.yml
%satejewels_autoname% // returns a icon/name that depends on the current balance value
%satejewels_balance% // returns a player balance
```

Placeholders for SateJewels config:
```java
{player} // returns a target player nickname
{amount} // returns a target amount of jewels
{name_<id>} // copy of %satejewels_name_<name>%
```
