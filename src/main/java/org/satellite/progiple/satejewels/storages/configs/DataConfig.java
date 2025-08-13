package org.satellite.progiple.satejewels.storages.configs;

import lombok.SneakyThrows;
import org.novasparkle.lunaspring.API.configuration.Configuration;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.Storage;

import java.io.File;

public class DataConfig implements Storage {
    private final Configuration config;

    @SneakyThrows
    public DataConfig(String fileName) {
        SateJewels plugin = SateJewels.getINSTANCE();
        assert plugin != null;
        this.config = new Configuration(new File(plugin.getDataFolder(), fileName));
    }

    public void reload() {
        this.config.reload();
    }

    @Override
    public int getJewels(String playerName) {
        return this.config.getInt(String.format("players.%s", playerName.toLowerCase()));
    }

    @Override
    public void setJewels(String playerName, int amount) {
        String nick = playerName.toLowerCase();
        if (amount < 0) amount = -amount;

        this.config.setInt(String.format("players.%s", nick), amount);
        this.config.save();
    }

    @Override
    public void clear() {
        this.config.setSection("players", null);
        this.config.save();
    }
}
