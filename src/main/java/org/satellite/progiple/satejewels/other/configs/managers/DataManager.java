package org.satellite.progiple.satejewels.other.configs.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.satellite.progiple.satejewels.other.configs.DataConfig;

public class DataManager {
    private final static DataConfig dataConfig;
    static {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SateJewels");
        assert plugin != null;
        dataConfig = new DataConfig("data.yml", plugin);
    }

    public static void reload() {
        dataConfig.reload();
    }

    public static int getValue(String nick) {
        return dataConfig.getValue(nick);
    }

    public static void setValue(String nick, int value) {
        dataConfig.setValue(nick, value);
    }
}
