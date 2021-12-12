package com.archery.discraft.commands;

import com.archery.discraft.Discraft;
import com.archery.discraft.commands.utils.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Check extends CommandExecutor {

    public Check() {
        setCommand("check");
        setPermission("discraft.check");
        setMaxLength(2);
        setUsage("/discraft check [player]");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(Discraft.getInstance().getPlayerDataHandler().isLinked(Bukkit.getPlayer(args[1]))) {
            sender.sendMessage(String.format("%s has a linked discord account.", sender.getName()));
        } else {
            sender.sendMessage(String.format("%s does not have a linked discord account.", sender.getName()));
        }

        Bukkit.broadcastMessage(String.format("%s used /discraft check", sender.getName()));

    }
}