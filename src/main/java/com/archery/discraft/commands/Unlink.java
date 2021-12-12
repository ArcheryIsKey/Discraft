package com.archery.discraft.commands;

import com.archery.discraft.commands.utils.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Unlink extends CommandExecutor {

    public Unlink() {
        setCommand("unlink");
        setPermission("discraft.unlink");
        setMaxLength(1);
        setUsage("/discraft unlink");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        /*
        TODO: Unlink player to discord
            Steps:
              - find player's uuid file in playerData folder
              - delete :)
        */

        Bukkit.broadcastMessage(String.format("%s used /discraft unlink", sender.getName()));

    }
}