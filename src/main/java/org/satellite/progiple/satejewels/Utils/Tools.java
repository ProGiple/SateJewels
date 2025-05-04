package org.satellite.progiple.satejewels.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.Map;

public class Tools {
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void sendMessage(CommandSender sender, String messageId, String soundId) {
        Tools.sendMessage(sender, messageId, 0, "", soundId);
    }

    public static void sendMessage(CommandSender sender, String messageId, String amount, String getterNick, String soundId) {
        String message = ConfigManager.getString(String.format("messages.%s", messageId));
        message = message.replace("{amount}", amount);
        message = message.replace("{player}", getterNick);

        if (sender == null) return;
        if (message.contains("{name_")) {
            for (Map.Entry<String, String> entry : SateJewels.getINSTANCE().getSjapi().getJewelNames().entrySet()) {
                message = message.replace(String.format("{name_%s}", entry.getKey()), entry.getValue());
            }
        }

        sender.sendMessage(Tools.color(message));
        if (sender instanceof Player player) {
            player.playSound(player.getLocation(), ConfigManager.getSound(soundId), 1, 1);
        }
    }

    public static void sendMessage(CommandSender sender, String messageId, int amount, String getterNick, String soundId) {
        Tools.sendMessage(sender, messageId, String.valueOf(amount), getterNick, soundId);
    }

    public static int toInt(String value) {
        return value.contains(".") ? Math.round(Float.parseFloat(value)) : Integer.parseInt(value);
    }

    public static int toInt(String nick, String value) {
        if (value.contains("%")) {
            double p = Double.parseDouble(value.replace("%", ""));

            int balance = SateJewels.getINSTANCE().getSjapi().getJewels(nick);
            return Tools.toInt(String.valueOf(balance * (p / 100)));
        }

        return Tools.toInt(value);
    }
}
