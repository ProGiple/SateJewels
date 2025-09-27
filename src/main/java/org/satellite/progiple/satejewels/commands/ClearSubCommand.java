package org.satellite.progiple.satejewels.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.Invocation;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.novasparkle.lunaspring.API.commands.annotations.TabCompleteIgnore;
import org.novasparkle.lunaspring.API.commands.processor.NoArgCommand;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.configs.Config;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = {"fullClear", "clear"})
@TabCompleteIgnore("clear")
@Check(permissions = "@.clear", flags = NoArgCommand.AccessFlag.CONSOLE_ONLY)
public class ClearSubCommand implements Invocation {
    @Override
    public void invoke(CommandSender sender, String[] strings) {
        SateJewels.getINSTANCE().getStorage().clear();
        Config.sendMessage(sender, "clearStorage");
    }
}
