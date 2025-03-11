package org.satellite.progiple.satejewels.storages.configs;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.Utils.Tools;
import org.satellite.progiple.satejewels.storages.Storage;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DataConfig implements Storage {
    @Getter private FileConfiguration config;
    private final File file;

    @SneakyThrows
    public DataConfig(String fileName) {
        SateJewels plugin = SateJewels.getPlugin();
        assert plugin != null;
        this.file = new File(plugin.getDataFolder(), fileName);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        ConfigurationSection dataSection = this.config.getConfigurationSection("players");
        if (dataSection == null) return;

        Set<String> keys = new HashSet<>();
        dataSection.getKeys(false).forEach(key -> {
            if (!key.equals(key.toLowerCase())) {
                this.config.set(String.format("players.%s", key.toLowerCase()), dataSection.getInt(key));
                keys.add(key);
            }
        });
        keys.forEach(key -> this.config.set(String.format("players.%s", key), null));
        this.config.save(this.file);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public int getJewels(String playerName) {
        return this.config.getInt(String.format("players.%s", playerName.toLowerCase()));
    }

    @SneakyThrows
    @Override
    public void setJewels(String playerName, int amount) {
        String nick = playerName.toLowerCase();
        if (amount < 0) amount = Math.abs(amount);

        this.config.set(String.format("players.%s", nick), amount);
        this.config.save(this.file);
    }

    @Override
    public void clear() {
        this.config.getKeys(false).forEach(k -> this.config.set(k, null));
    }
}
