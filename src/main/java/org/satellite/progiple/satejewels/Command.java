package org.satellite.progiple.satejewels;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.satellite.progiple.satejewels.Utils.Tools;
import org.satellite.progiple.satejewels.api.SJAPI;
import org.satellite.progiple.satejewels.storages.Storage;
import org.satellite.progiple.satejewels.storages.configs.DataConfig;
import org.satellite.progiple.satejewels.storages.configs.managers.ConfigManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length >= 1) {
            switch (args[0]) {
                case "give", "add":
                    if (hasAdminPerm(sender)) {
                        if (args.length >= 3) {
                            String nick = args[1];
                            int value = Integer.parseInt(args[2]);

                            SateJewels.getPlugin().getSjapi().giveJewels(nick, value);
                            Tools.sendMessage(sender, "giveJewels", value, nick, "successful");
                        }
                        else this.noArgsMess(sender);
                    }
                    break;
                case "reload":
                    if (hasAdminPerm(sender)) {
                        ConfigManager.reload();
                        if (SateJewels.getPlugin().getStorage() instanceof DataConfig) {
                            ((DataConfig) SateJewels.getPlugin().getStorage()).reload();
                        }

                        Tools.sendMessage(sender, "reloadPlugin", "successful");
                    }
                    break;
                case "balance", "bal":
                    Storage storage = SateJewels.getPlugin().getStorage();
                    if (args.length >= 2) {
                        String nick = args[1];
                        if (sender.hasPermission("satejewels.balance.another")) {
                            Tools.sendMessage(sender, "playerBalance", storage.getJewels(nick), nick, "successful");
                        }
                        else Tools.sendMessage(sender, "noPerm", "error");
                    }
                    else {
                        String nick = sender.getName();
                        if (sender.hasPermission("satejewels.balance")) {
                            Tools.sendMessage(sender, "balance", storage.getJewels(nick), "", "successful");
                        }
                        else Tools.sendMessage(sender, "noPerm", "error");
                    }
                    break;
                case "removeJewels", "take":
                    if (hasAdminPerm(sender)) {
                        if (args.length >= 3) {
                            String nick = args[1];
                            int value = Integer.parseInt(args[2]);

                            SateJewels.getPlugin().getSjapi().removeJewels(nick, value);
                            Tools.sendMessage(sender, "removeJewels", value, nick, "successful");
                        }
                        else this.noArgsMess(sender);
                    }
                    break;
                case "payJewels", "pay":
                    if (sender.hasPermission("satejewels.pay")) {
                        if (args.length >= 3) {
                            String nick = args[1];
                            int value = Integer.parseInt(args[2]);

                            if (SateJewels.getPlugin().getSjapi().payJewels(sender.getName(), nick, value)) {
                                Tools.sendMessage(sender, "payJewels", value, nick, "successful");
                                Tools.sendMessage(Bukkit.getPlayer(nick), "payedJewels", value, sender.getName(), "successful");
                            }
                            else Tools.sendMessage(sender, "noJewels", "error");
                        }
                        else this.noArgsMess(sender);
                    }
                    else Tools.sendMessage(sender, "noPerm", "error");
                    break;
                case "setJewels", "set":
                    if (hasAdminPerm(sender)) {
                        if (args.length >= 3) {
                            String nick = args[1];
                            int value = Integer.parseInt(args[2]);

                            SateJewels.getPlugin().getSjapi().setJewels(nick, value);
                            Tools.sendMessage(sender, "payJewels", value, nick, "successful");
                        }
                        else this.noArgsMess(sender);
                    }
                    break;
            }
        }
        else this.noArgsMess(sender);
        return true;
    }

    private void noArgsMess(CommandSender sender) {
        Tools.sendMessage(sender, "noArgs", "error");
    }

    private boolean hasAdminPerm(CommandSender sender) {
        boolean value = sender.hasPermission("satejewels.admin");;
        if (!value) Tools.sendMessage(sender, "noPerm", "error");
        return value;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> list = new ArrayList<>();
        if (strings.length == 1) {
            list.addAll(Arrays.asList("add", "reload", "take", "set", "pay", "balance"));
        }
        else if (strings.length == 2 && (strings[0].equals("add") || strings[0].equals("set")
                || strings[0].equals("take") || strings[0].equals("pay") || strings[0].equals("balance"))) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        else if (strings.length == 3 && (strings[0].equals("add") || strings[0].equals("set")
                || strings[0].equals("take") || strings[0].equals("pay"))) {
            list.add("<amount>");
        }
        return list;
    }
}