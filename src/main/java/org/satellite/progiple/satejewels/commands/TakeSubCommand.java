package org.satellite.progiple.satejewels.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.LunaCompleter;
import org.novasparkle.lunaspring.API.commands.annotations.Args;
import org.novasparkle.lunaspring.API.commands.annotations.Permissions;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.commands.annotations.TabCompleteIgnore;
import org.novasparkle.lunaspring.API.util.utilities.LunaMath;
import org.novasparkle.lunaspring.API.util.utilities.Utils;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.Config;

import java.util.List;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = {"remove", "take", "rem", "delete"})
@TabCompleteIgnore({"remove", "rem", "delete"})
@Permissions("@.take")
@Args(min = 2, max = 3)
public class TakeSubCommand implements LunaCompleter {
    // sj take <nick> <value>

    @Override
    public List<String> tabComplete(CommandSender sender, List<String> list) {
        return list.size() == 1 ? Utils.getPlayerNicks(list.get(0)) :
                list.size() == 2 ? List.of("<количество>") : null;
    }

    @Override
    public void invoke(CommandSender sender, String[] strings) {
        int amount = strings.length >= 3 ? LunaMath.toInt(strings[2], 1) : 1;
        if (amount < 0) amount = -amount;

        SateJewels.getINSTANCE().getAPI().removeJewels(strings[1], amount);
        Config.sendMessage(sender, "takeJewels", "player-%-" + strings[1], "amount-%-" + amount);
    }
}
