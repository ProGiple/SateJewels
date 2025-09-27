package org.satellite.progiple.satejewels;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.commands.CommandInitializer;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.DataConfig;
import org.satellite.progiple.satejewels.storages.configs.Config;
import org.satellite.progiple.satejewels.storages.db.DataBase;

import java.util.Objects;

@Getter
public final class SateJewels extends LunaPlugin {
    @Getter private static SateJewels INSTANCE;
    private Storage storage;
    private SJAPI API;

    @Override
    public void onEnable() {
        INSTANCE = this;
        super.onEnable();

        API = new SJAPI();
        this.saveDefaultConfig();

        CommandInitializer.initialize(this, "#.commands");

        ConfigurationSection storageSection = Config.getSection("storage");
        if (Objects.requireNonNull(storageSection.getString("storageType")).equalsIgnoreCase("mysql")) {
            ConfigurationSection sqlSection = storageSection.getConfigurationSection("mySql");
            assert sqlSection != null;
            this.storage = new DataBase(sqlSection);
        }
        else this.storage = new DataConfig(storageSection.getString("configuration.fileName"));

        this.createPlaceholder("satejewels", (player, params) -> {
            if (params.contains("name_")) {
                String[] massive = params.split("_");
                return massive.length >= 2 ? API.getJewelName(massive[1]) : null;
            }

            if (params.startsWith("autoname-")) {
                String[] split = params.split("-");
                return API.getJewelName(LunaMath.toInt(Utils.setBracketPlaceholders(player, split[1])));
            }

            int value = API.getJewels(player);
            if (params.equalsIgnoreCase("autoname")) {
                return API.getJewelName(value);
            }

            if (params.equalsIgnoreCase("balance")) {
                return String.valueOf(value);
            }

            return null;
        });
    }
}