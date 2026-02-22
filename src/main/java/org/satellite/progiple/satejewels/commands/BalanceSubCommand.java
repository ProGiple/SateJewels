package org.satellite.progiple.satejewels.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.LunaExecutor;
import org.novasparkle.lunaspring.API.commands.annotations.Args;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.commands.annotations.TabCompleteIgnore;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.List;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = {"balance", "bal"})
@TabCompleteIgnore("bal")
@Args(min = 1, max = 2)
public class BalanceSubCommand implements LunaExecutor {
    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        return list.size() == 1 ? Utils.getPlayerNicks(list.get(0), sender) : null;
    }

    @Override
    public void invoke(CommandSender sender, String[] strings) {
        if (strings.length < 2) {
            if (!sender.hasPermission("satejewels.balance")) {
                Config.sendMessage(sender, "noPermission");
                return;
            }

            int amount = SateJewels.getINSTANCE().getAPI().getJewels(sender.getName());
            Config.sendMessage(sender, "balance", "amount-%-" + amount);
        }
        else {
            if (!sender.hasPermission("satejewels.balance.another")) {
                Config.sendMessage(sender, "noPermission");
                return;
            }

            int amount = SateJewels.getINSTANCE().getAPI().getJewels(strings[1]);
            Config.sendMessage(sender, "playerBalance",
                    "player-%-" + strings[1],
                    "amount-%-" + amount);
        }
    }
}
