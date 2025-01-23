package org.satellite.progiple.satejewels.storages.configs;

import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.satellite.progiple.satejewels.SateJewels;

import java.util.HashMap;
import java.util.Map;

public class Config {

    @Getter private Map<String, String> jewelsNames = new HashMap<>();
    private FileConfiguration config;
    public Config() {
        this.config = SateJewels.getPlugin().getConfig();
    }

    public void reload() {
        SateJewels plugin = SateJewels.getPlugin();
        plugin.reloadConfig();
        this.config = plugin.getConfig();

        ConfigurationSection section = this.config.getConfigurationSection("jewelsSettings.names");
        if (section == null) return;
        jewelsNames.clear();
        for (String id : section.getKeys(false)) {
            jewelsNames.put(id, section.getString(id));
        }
    }

    public String getString(String path) {
        return this.config.getString(path);
    }
    public ConfigurationSection getSection(String path) {
        return this.config.getConfigurationSection(path);
    }
    public Sound getSound(String type) {
        return Sound.valueOf(this.getString(String.format("sounds.%s", type)));
    }
}
