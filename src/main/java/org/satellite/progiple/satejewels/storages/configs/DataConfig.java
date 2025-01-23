package org.satellite.progiple.satejewels.storages.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.Utils.Tools;
import org.satellite.progiple.satejewels.storages.Storage;

import java.io.File;

public class DataConfig implements Storage {

    @Getter private FileConfiguration config;
    private final File file;
    public DataConfig(String fileName) {
        SateJewels plugin = SateJewels.getPlugin();
        assert plugin != null;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public int getJewels(String playerName) {
        return this.config.getInt(String.format("players.%s", playerName));
    }

    @SneakyThrows
    @Override
    public void setJewels(String playerName, int amount) {
        if (amount < 0) amount = Math.abs(amount);
        this.config.set(String.format("players.%s", playerName), amount);
        this.config.save(this.file);
        Tools.syncSJtoPP(playerName);
    }

    @Override
    public void clear() {
        this.config.getKeys(false).forEach(k -> this.config.set(k, null));
    }
}
