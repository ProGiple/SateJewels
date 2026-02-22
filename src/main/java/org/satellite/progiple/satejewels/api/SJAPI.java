package org.satellite.progiple.satejewels.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.Map;
import java.util.UUID;

public class SJAPI {
    public Storage getStorage() {
        return SateJewels.getINSTANCE().getStorage();
    }

    public int getJewels(String nick) {
        return getStorage().getJewels(nick);
    }

    public int getJewels(OfflinePlayer offlinePlayer) {
        return this.getJewels(offlinePlayer.getName());
    }

    public int getJewels(UUID uuid) {
        return this.getJewels(Bukkit.getOfflinePlayer(uuid));
    }

    public void giveJewels(String nick, int value) {
        getStorage().setJewels(nick, this.getJewels(nick) + value);
    }

    public void giveJewels(OfflinePlayer offlinePlayer, int value) {
        this.giveJewels(offlinePlayer.getName(), value);
    }

    public void giveJewels(UUID uuid, int value) {
        this.giveJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public void removeJewels(String nick, int value) {
        getStorage().setJewels(nick, this.getJewels(nick) - value);
    }

    public void removeJewels(OfflinePlayer offlinePlayer, int value) {
        this.removeJewels(offlinePlayer.getName(), value);
    }

    public void removeJewels(UUID uuid, int value) {
        this.removeJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public boolean payJewels(String fromPlayerNick, String toPlayerNick, int value) {
        if (this.getJewels(fromPlayerNick) >= value) {
            this.removeJewels(fromPlayerNick, value);
            this.giveJewels(toPlayerNick, value);
            return true;
        }
        return false;
    }

    public void setJewels(String nick, int value) {
        getStorage().setJewels(nick, value);
    }

    public void setJewels(OfflinePlayer offlinePlayer, int value) {
        this.setJewels(offlinePlayer.getName(), value);
    }

    public void setJewels(UUID uuid, int value) {
        this.setJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public Map<String, String> getJewelNames() {
        return Config.getJewelsNames();
    }

    public String getJewelName(String id) {
        return this.getJewelNames().get(id);
    }

    public String getJewelName(int value) {
        String mainPath = "placeholders.autoName.%s";

        String strValue = String.valueOf(value);
        if (strValue.length() >= 2) {
            int cachedValue = LunaMath.toInt(strValue.substring(strValue.length() - 2));

            if (cachedValue >= 11 && cachedValue <= 19)
                return this.getJewelName(Config.getString(String.format(mainPath, "5+")));
        }

        byte lastNum = (byte) (value % 10);
        return switch (lastNum) {
            case 0 -> this.getJewelName(Config.getString(String.format(mainPath, "0")));
            case 1 -> this.getJewelName(Config.getString(String.format(mainPath, "1")));
            case 2, 3, 4 -> this.getJewelName(Config.getString(String.format(mainPath, "2-4")));
            default -> this.getJewelName(Config.getString(String.format(mainPath, "5+")));
        };
    }

    public void clearStorage() {
        getStorage().clear();
    }
}
