package org.satellite.progiple.satejewels;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.satellite.progiple.satejewels.other.Placeholders;
import org.satellite.progiple.satejewels.other.configs.Config;
import org.satellite.progiple.satejewels.other.configs.DataConfig;

import java.util.Objects;

public final class SateJewels extends JavaPlugin {
    private static DataConfig dataConfig = new DataConfig();
    @Getter
    private static SateJewels plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        new DataConfig(plugin.getDataFolder());
        new Config();

        Command command = new Command();
        Objects.requireNonNull(getCommand("satejewels")).setTabCompleter(command);
        Objects.requireNonNull(getCommand("satejewels")).setExecutor(command);

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Placeholders placeholders = new Placeholders();
            placeholders.register();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
