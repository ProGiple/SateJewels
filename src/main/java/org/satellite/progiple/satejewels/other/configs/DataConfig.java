package org.satellite.progiple.satejewels.other.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.satellite.progiple.satejewels.SateJewels;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

public class DataConfig {

    @Getter private FileConfiguration config;
    private final File file;
    public DataConfig(String fileName) {
        this.file = new File(SateJewels.getPlugin().getDataFolder(), fileName);
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
