package org.satellite.progiple.satejewels.Utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "satejewels";
    }

    @Override
    public @NotNull String getAuthor() {
        return "ProGiple";
    }

    @Override
    public @NotNull String getVersion() {
        return "latest";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        String mainPath = "placeholders.autoName.%s";
        SJAPI sjapi = SateJewels.getPlugin().getSjapi();
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
    }
}