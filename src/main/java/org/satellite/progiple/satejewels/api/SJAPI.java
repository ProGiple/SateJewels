package org.satellite.progiple.satejewels.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.satellite.progiple.satejewels.other.configs.Config;
import org.satellite.progiple.satejewels.other.configs.DataConfig;

import java.util.*;

public class SJAPI {
    public static boolean additiveLoad() {
        new DataConfig();
        return SJAPI.isNotNull();
    }

    public static boolean isNotNull() {
        return DataConfig.getDataConfig() != null;
    }

    public static int getJewels(String nick) {
        return DataConfig.getDataConfig().getValue(nick);
    }

    public static int getJewels(OfflinePlayer offlinePlayer) {
        return SJAPI.getJewels(offlinePlayer.getName());
    }

    public static int getJewels(UUID uuid) {
        return SJAPI.getJewels(Bukkit.getOfflinePlayer(uuid));
    }

    public static void giveJewels(String nick, int value) {
        DataConfig.getDataConfig().setValue(nick, SJAPI.getJewels(nick) + value);
    }

    public static void giveJewels(OfflinePlayer offlinePlayer, int value) {
        SJAPI.giveJewels(offlinePlayer.getName(), value);
    }

    public static void giveJewels(UUID uuid, int value) {
        SJAPI.giveJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public static void removeJewels(String nick, int value) {
        DataConfig.getDataConfig().setValue(nick, SJAPI.getJewels(nick) - value);
    }

    public static void removeJewels(OfflinePlayer offlinePlayer, int value) {
        SJAPI.removeJewels(offlinePlayer.getName(), value);
    }

    public static void removeJewels(UUID uuid, int value) {
        SJAPI.removeJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public static boolean payJewels(String fromPlayerNick, String toPlayerNick, int value) {
        if (SJAPI.getJewels(fromPlayerNick) >= value) {
            SJAPI.removeJewels(fromPlayerNick, value);
            SJAPI.giveJewels(toPlayerNick, value);
            return true;
        }
        return false;
    }

    public static void setJewels(String nick, int value) {
        DataConfig.getDataConfig().setValue(nick, value);
    }

    public static void setJewels(OfflinePlayer offlinePlayer, int value) {
        SJAPI.setJewels(offlinePlayer.getName(), value);
    }

    public static void setJewels(UUID uuid, int value) {
        SJAPI.setJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public static ConfigurationSection getPlayerSection() {
        return DataConfig.getDataConfig().getConfig().getConfigurationSection("players");
    }

    public static Map<String, String> getJewelNames() {
        return Config.getJewelsNames();
    }

    public static String getJewelName(String id) {
        return SJAPI.getJewelNames().get(id);
    }
}
