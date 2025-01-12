package org.satellite.progiple.satejewels.other.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class DataConfig {
    @Getter private FileConfiguration config;
    private final File file;
    public DataConfig(String fileName, Plugin plugin) {
        this.file = new File(plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public int getValue(String nick) {
        return this.config.getInt(String.format("players.%s", nick));
    }

    @SneakyThrows
    public void setValue(String nick, int value) {
        if (value < 0) value = Math.abs(value);
        this.config.set(String.format("players.%s", nick), value);
        this.config.save(this.file);
    }
}
