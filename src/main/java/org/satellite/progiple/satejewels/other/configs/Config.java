package org.satellite.progiple.satejewels.other.configs;

import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Config {

    @Getter private Map<String, String> jewelsNames = new HashMap<>();
    private FileConfiguration config;
    private final Plugin plugin;
    public Config(Plugin plugin) {
        this.config = plugin.getConfig();
        this.plugin = plugin;
    }

    public void reload() {
        this.plugin.reloadConfig();
        this.config = this.plugin.getConfig();

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

    public Sound getSound(String type) {
        return Sound.valueOf(this.getString(String.format("sounds.%s", type)));
    }
}
