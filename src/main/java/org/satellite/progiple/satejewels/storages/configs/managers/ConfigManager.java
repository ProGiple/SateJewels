package org.satellite.progiple.satejewels.storages.configs.managers;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.configuration.IConfig;
import org.satellite.progiple.satejewels.SateJewels;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ConfigManager {
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

    public Sound getSound(String type) {
        return Sound.valueOf(config.getString(String.format("sounds.%s", type)));
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

    public static ConfigurationSection getSection(String storage) {
        return config.getSection(storage);
    }

    public static boolean getBool(String path) {
        return config.getBoolean(path);
    }
}
