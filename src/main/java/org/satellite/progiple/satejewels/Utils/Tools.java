package org.satellite.progiple.satejewels.Utils;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.Map;

public class Tools {
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender sender, String messageId, String soundId) {
        Tools.sendMessage(sender, messageId, 0, "", soundId);
    }

    public static void sendMessage(CommandSender sender, String messageId, int amount, String getterNick, String soundId) {
        String message = ConfigManager.getString(String.format("messages.%s", messageId));
        message = message.replace("{amount}", String.valueOf(amount));
        message = message.replace("{player}", getterNick);

        if (sender == null) return;
        if (message.contains("{name_")) {
            for (Map.Entry<String, String> entry : SateJewels.getPlugin().getSjapi().getJewelNames().entrySet()) {
                message = message.replace(String.format("{name_%s}", entry.getKey()), entry.getValue());
            }
        }

        sender.sendMessage(Tools.color(message));
        if (sender instanceof Player player) {
            player.playSound(player.getLocation(), ConfigManager.getSound(soundId), 1, 1);
        }
    }

    public static void syncSJtoPP(String nick) {
        if (!ConfigManager.getBool("jewelsSettings.playerPointsAutoTransfer") ||
                Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints") == null) return;
        PlayerPointsAPI ppapi = PlayerPoints.getInstance().getAPI();
        if (ppapi == null) return;

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayerIfCached(nick);
        if (offlinePlayer == null) return;
        ppapi.set(offlinePlayer.getUniqueId(), SateJewels.getPlugin().getSjapi().getJewels(nick));
    }
}
