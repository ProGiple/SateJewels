package org.satellite.progiple.satejewels.other.configs.managers;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.Plugin;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.other.configs.Config;

import java.util.Map;

@UtilityClass
public class ConfigManager {
    private final Config configuration;
    static {
        configuration = new Config(SateJewels.getPlugin());
        reload();
    }

    public String getString(String path) {
        return configuration.getString(path);
    }

    public Sound getSound(String type) {
        return configuration.getSound(type);
    }

    public void reload() {
        configuration.reload();
    }

    public Map<String, String> getJewelsNames() {
        return configuration.getJewelsNames();
    }
}
