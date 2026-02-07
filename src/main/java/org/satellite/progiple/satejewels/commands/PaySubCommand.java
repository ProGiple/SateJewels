package org.satellite.progiple.satejewels.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.novasparkle.lunaspring.API.commands.LunaExecutor;
import org.novasparkle.lunaspring.API.commands.annotations.Args;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.commands.processor.NoArgCommand;
import org.novasparkle.lunaspring.API.util.service.managers.VanishManager;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.List;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = "pay")
@Check(permissions = "@.pay", flags = NoArgCommand.AccessFlag.PLAYER_ONLY)
@Args(min = 3, max = 3)
public class PaySubCommand implements LunaExecutor {
    // sj pay <player> <amount>

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        return list.size() == 1 ? Utils.getPlayerNicks(list.get(0), sender) :
                list.size() == 2 ? List.of("<количество>") : null;
    }

    @Override
    public void invoke(CommandSender sender, String[] strings) {
        String nick = strings[1];
        int value = LunaMath.toInt(strings[2], 1);

        Player player = VanishManager.exact(nick);
        if (player == null || !player.isOnline()) {
            Config.sendMessage(sender, "playerIsOffline", "player-%-" + nick);
            return;
        }

        if (SateJewels.getINSTANCE().getAPI().payJewels(sender.getName(), nick, value)) {
            Config.sendMessage(sender, "payJewels",
                    "player-%-" + nick,
                    "amount-%-" + value);
            Config.sendMessage(player, "getPayedJewels",
                    "player-%-" + sender.getName(),
                    "amount-%-" + value);
        }
        else Config.sendMessage(sender, "noJewels");
    }
}
