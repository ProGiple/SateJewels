package org.satellite.progiple.satejewels.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.LunaExecutor;
import org.novasparkle.lunaspring.API.commands.annotations.Args;
import org.novasparkle.lunaspring.API.commands.annotations.Permissions;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.commands.annotations.TabCompleteIgnore;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.List;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = {"add", "give"})
@TabCompleteIgnore("add")
@Permissions("@.give")
@Args(min = 2, max = 3)
public class GiveSubCommand implements LunaExecutor {
    // sj add <nick> <value>

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        return list.size() == 1 ? Utils.getPlayerNicks(list.get(0), sender) :
                list.size() == 2 ? List.of("<количество>") : null;
    }

    @Override
    public void invoke(CommandSender sender, String[] strings) {
        int amount = strings.length >= 3 ? LunaMath.toInt(strings[2], 1) : 1;
        if (amount < 0) amount = -amount;

        SateJewels.getINSTANCE().getAPI().giveJewels(strings[1], amount);
        Config.sendMessage(sender, "giveJewels", "player-%-" + strings[1], "amount-%-" + amount);
    }
}
