package org.satellite.progiple.satejewels.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.Map;
import java.util.UUID;

public class SJAPI {
    public static int getJewels(String nick) {
        return SateJewels.getPlugin().getStorage().getJewels(nick);
    }

    public static int getJewels(OfflinePlayer offlinePlayer) {
        return SJAPI.getJewels(offlinePlayer.getName());
    }

    public static int getJewels(UUID uuid) {
        return SJAPI.getJewels(Bukkit.getOfflinePlayer(uuid));
    }

    public static void giveJewels(String nick, int value) {
        SateJewels.getPlugin().getStorage().setJewels(nick, SJAPI.getJewels(nick) + value);
    }

    public static void giveJewels(OfflinePlayer offlinePlayer, int value) {
        SJAPI.giveJewels(offlinePlayer.getName(), value);
    }

    public static void giveJewels(UUID uuid, int value) {
        SJAPI.giveJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public static void removeJewels(String nick, int value) {
        SateJewels.getPlugin().getStorage().setJewels(nick, SJAPI.getJewels(nick) - value);
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
        SateJewels.getPlugin().getStorage().setJewels(nick, value);
    }

    public static void setJewels(OfflinePlayer offlinePlayer, int value) {
        SJAPI.setJewels(offlinePlayer.getName(), value);
    }

    public static void setJewels(UUID uuid, int value) {
        SJAPI.setJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public static Map<String, String> getJewelNames() {
        return ConfigManager.getJewelsNames();
    }

    public static String getJewelName(String id) {
        return SJAPI.getJewelNames().get(id);
    }
}
