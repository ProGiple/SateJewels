package org.satellite.progiple.satejewels.api;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.Map;
import java.util.UUID;

public class SJAPI {
    public int getJewels(String nick) {
        return SateJewels.getINSTANCE().getStorage().getJewels(nick);
    }

    public int getJewels(OfflinePlayer offlinePlayer) {
        return this.getJewels(offlinePlayer.getName());
    }

    public int getJewels(UUID uuid) {
        return this.getJewels(Bukkit.getOfflinePlayer(uuid));
    }

    public void giveJewels(String nick, int value) {
        SateJewels.getINSTANCE().getStorage().setJewels(nick, this.getJewels(nick) + value);
    }

    public void giveJewels(OfflinePlayer offlinePlayer, int value) {
        this.giveJewels(offlinePlayer.getName(), value);
    }

    public void giveJewels(UUID uuid, int value) {
        this.giveJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public void removeJewels(String nick, int value) {
        SateJewels.getINSTANCE().getStorage().setJewels(nick, this.getJewels(nick) - value);
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
        SateJewels.getINSTANCE().getStorage().setJewels(nick, value);
    }

    public void setJewels(OfflinePlayer offlinePlayer, int value) {
        this.setJewels(offlinePlayer.getName(), value);
    }

    public void setJewels(UUID uuid, int value) {
        this.setJewels(Bukkit.getOfflinePlayer(uuid), value);
    }

    public Map<String, String> getJewelNames() {
        return ConfigManager.getJewelsNames();
    }

    public String getJewelName(String id) {
        return this.getJewelNames().get(id);
    }
}
