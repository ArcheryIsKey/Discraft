package com.archery.discraft.commands.utils;

import com.archery.discraft.commands.Check;
import com.archery.discraft.commands.Link;
import com.archery.discraft.commands.Unlink;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandHandler implements org.bukkit.command.CommandExecutor {

    private final HashMap<String, CommandExecutor> commands = new HashMap<>();

    public CommandHandler() {
        commands.put("link", new Link());
        commands.put("unlink", new Unlink());
        commands.put("check", new Check());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("discraft")) {

            if (args.length == 0) {
                // TODO: Discraft command

                Bukkit.broadcastMessage(String.format("%s used /discraft", sender.getName()));
                return true;

            } else {

                String cmdname = args[0].toLowerCase();

                if (commands.containsKey(cmdname)) {

                    final CommandExecutor cmd = commands.get(cmdname);

                    if (!(sender instanceof Player)) return false;
                    if (!sender.hasPermission(cmd.getPermission()) && !sender.isOp()) return false;

                    if (cmd.getMaxLength() > args.length) {

                        sender.sendMessage("Usage: " + cmd.getUsage());
                        return false;
                    }

                    cmd.execute(sender, args);
                    return true;
                }
            }
        }
        return false;
    }
}