package com.archery.discraft.commands;

import com.archery.discraft.Discraft;
import com.archery.discraft.commands.utils.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Link extends CommandExecutor {

    public Link() {
        setCommand("link");
        setPermission("discraft.link");
        setMaxLength(1);
        setUsage("/discraft link");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        /*
        TODO: Link player to discord username
            Steps:
              - "/discraft link [discord]
              - check if discord user is in discord server
              - call DiscordLinkStartedEvent
              - send code to minecraft player, asking to pm it to discord bot
              - confirm, successful link
              - call DiscordLinkedEvent
        */

        Player p = (Player)sender;

        Bukkit.getLogger().info(Discraft.getInstance().getPlayerDataHandler().toString());

        String code = Discraft.getInstance().getDiscordHandler().generateCode();

        Discraft.getInstance().getDiscordHandler().startLinking(p, code);

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&aPlease send '&b%s&a' to the bot in discord.")));

        Bukkit.broadcastMessage(String.format("%s used /discraft link", sender.getName()));

    }
}