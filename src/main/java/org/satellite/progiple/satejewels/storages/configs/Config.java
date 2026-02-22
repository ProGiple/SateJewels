package org.satellite.progiple.satejewels.storages.configs;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.configuration.IConfig;
import org.satellite.progiple.satejewels.SateJewels;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Config {
    @Getter
    private Map<String, String> jewelsNames = new HashMap<>();
    private final IConfig config;
    static {
        config = new IConfig(SateJewels.getINSTANCE());
        reload();
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public void reload() {
        config.reload(SateJewels.getINSTANCE());

        ConfigurationSection section = config.getSection("jewelsSettings.names");
        if (section == null) return;

        jewelsNames.clear();
        for (String id : section.getKeys(false)) {
            jewelsNames.put(id, section.getString(id));
        }
    }

    public ConfigurationSection getSection(String storage) {
        return config.getSection(storage);
    }

    public void sendMessage(CommandSender sender, String id, String... rpl) {
        config.sendMessage(sender, id, rpl);
    }
}
