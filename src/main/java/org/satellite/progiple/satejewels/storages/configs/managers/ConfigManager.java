package org.satellite.progiple.satejewels.storages.configs.managers;

import lombok.experimental.UtilityClass;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.Map;

@UtilityClass
public class ConfigManager {
    private final Config configuration;
    static {
        configuration = new Config();
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

    public static ConfigurationSection getSection(String storage) {
        return configuration.getSection(storage);
    }
}
