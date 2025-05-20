package org.satellite.progiple.satejewels;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.DataConfig;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;
import org.satellite.progiple.satejewels.storages.db.DataBase;

import java.util.Objects;

public final class SateJewels extends LunaPlugin {
    @Getter private static SateJewels INSTANCE;
    @Getter private Storage storage;
    @Getter private SJAPI sjapi;

    @Override
    public void onEnable() {
        INSTANCE = this;
        super.onEnable();

        sjapi = new SJAPI();
        this.saveDefaultConfig();
        this.registerTabExecutor(new Command(), "satejewels");

        ConfigurationSection storageSection = ConfigManager.getSection("storage");
        if (Objects.requireNonNull(storageSection.getString("storageType")).equalsIgnoreCase("mysql")) {
            ConfigurationSection sqlSection = storageSection.getConfigurationSection("mySql");
            assert sqlSection != null;
            this.storage = new DataBase(sqlSection);
        } else this.storage = new DataConfig(storageSection.getString("configuration.fileName"));

        this.createPlaceholder("satejewels", (player, params) -> {
            String mainPath = "placeholders.autoName.%s";
            SJAPI sjapi = SateJewels.getINSTANCE().getSjapi();
            int value = sjapi.getJewels(player);

            if (params.equalsIgnoreCase("autoname")) {
                byte lastNum = (byte) (value % 10);
                return switch (lastNum) {
                    case 0 -> sjapi.getJewelName(ConfigManager.getString(String.format(mainPath, "0")));
                    case 1 -> sjapi.getJewelName(ConfigManager.getString(String.format(mainPath, "1")));
                    case 2, 3, 4 -> sjapi.getJewelName(ConfigManager.getString(String.format(mainPath, "2-4")));
                    default -> sjapi.getJewelName(ConfigManager.getString(String.format(mainPath, "5+")));
                };
            }
            else if (params.contains("name_")) {
                String[] massive = params.split("_");
                if (massive.length >= 2) {
                    return sjapi.getJewelName(massive[1]);
                }
            }
            else if (params.equalsIgnoreCase("balance")) {
                return String.valueOf(value);
            }
            return null;
        });

//        if (Utils.isPluginEnabled("zAuctionHouseV3")) {
//            new ZAuctionHouse().register();
//        }
    }

    public <T> T getProvider(Class<T> classz) {
        RegisteredServiceProvider<T> provider = this.getServer().getServicesManager().getRegistration(classz);
        if (provider == null) return null;

        return provider.getProvider();
    }
}