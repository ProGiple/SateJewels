package org.satellite.progiple.satejewels;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.satellite.progiple.satejewels.Utils.Placeholders;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.DataConfig;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;
import org.satellite.progiple.satejewels.storages.db.DataBase;

import java.util.Objects;

public final class SateJewels extends JavaPlugin {
    @Getter
    private static SateJewels plugin;
    @Getter
    private Storage storage;
    @Getter
    private SJAPI sjapi;

    @Override
    public void onEnable() {
        plugin = this;
        sjapi = new SJAPI();
        this.saveDefaultConfig();
        Command command = new Command();
        Objects.requireNonNull(getCommand("satejewels")).setTabCompleter(command);
        Objects.requireNonNull(getCommand("satejewels")).setExecutor(command);

        ConfigurationSection storageSection = ConfigManager.getSection("storage");
        if (Objects.requireNonNull(storageSection.getString("storageType")).equalsIgnoreCase("mysql")) {
            ConfigurationSection sqlSection = storageSection.getConfigurationSection("mySql");
            assert sqlSection != null;
            this.storage = new DataBase(sqlSection);

        } else this.storage = new DataConfig(storageSection.getString("configuration.fileName"));

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Placeholders placeholders = new Placeholders();
            placeholders.register();
        }
    }
}