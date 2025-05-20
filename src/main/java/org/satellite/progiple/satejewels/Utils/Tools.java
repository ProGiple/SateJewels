package org.satellite.progiple.satejewels.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.novasparkle.lunaspring.API.util.service.managers.ColorManager;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.Map;

public class Tools {
    public static void sendMessage(CommandSender sender, String messageId, String soundId) {
        Tools.sendMessage(sender, messageId, 0, "", soundId);
    }

    public static void sendMessage(CommandSender sender, String messageId, String amount, String getterNick, String soundId) {
        String message = ConfigManager.getString(String.format("messages.%s", messageId));
        message = Utils.applyReplacements(message, "amount-%-" + amount, "player-%-" + getterNick);

        if (sender == null) return;
        if (message.contains("{name_") || message.contains("[name_")) {
            for (Map.Entry<String, String> entry : SateJewels.getINSTANCE().getSjapi().getJewelNames().entrySet()) {
                message = message
                        .replace(String.format("{name_%s}", entry.getKey()), entry.getValue())
                        .replace(String.format("[name_%s]", entry.getKey()), entry.getValue());
            }
        }

        if (sender instanceof Player player) {
            player.playSound(player.getLocation(), ConfigManager.getSound(soundId), 1, 1);
            message = Utils.setPlaceholders(player, message);
        }

        sender.sendMessage(ColorManager.color(message));
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
