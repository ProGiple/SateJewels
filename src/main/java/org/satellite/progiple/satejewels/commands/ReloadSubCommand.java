package org.satellite.progiple.satejewels.commands;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.Invocation;
import org.novasparkle.lunaspring.API.commands.annotations.Permissions;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;
import org.satellite.progiple.satejewels.SateJewels;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.Config;
import org.satellite.progiple.satejewels.storages.configs.DataConfig;

@SubCommand(appliedCommand = "satejewels", commandIdentifiers = "reload")
@Permissions("@.reload")
public class ReloadSubCommand implements Invocation {
    @Override
    public void invoke(CommandSender sender, String[] strings) {
        Config.reload();

        Storage storage = SateJewels.getINSTANCE().getStorage();
        if (storage instanceof DataConfig dataConfig) dataConfig.reload();

        Config.sendMessage(sender, "reloadPlugin");
    }
}
