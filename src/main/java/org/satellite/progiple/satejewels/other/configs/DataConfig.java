package org.satellite.progiple.satejewels.other.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class DataConfig {
    @Getter
    private static DataConfig dataConfig;

    @Getter private FileConfiguration config;
    private final File file;
    public DataConfig(File dataFolder) {
        this.file = new File(dataFolder, "data.yml");
        this.reload();
        this.initialize();
    }

    public void initialize() {
        dataConfig = this;
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
        dataConfig = this;
        this.reload();
    }
}
