package com.archery.discraft.discord.listeners;

import com.archery.discraft.Discraft;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageReceived extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getChannelType() == ChannelType.PRIVATE) {
            Bukkit.broadcastMessage(String.format("%s#%s: %s", event.getAuthor().getName(), event.getAuthor().getDiscriminator(), event.getMessage()));

            if(Discraft.getInstance().getDiscordHandler().isLinking(event.getMessage().toString())) {
                String s = Discraft.getInstance().getDiscordHandler().getCode(event.getAuthor());
                if(event.getMessage().toString().equalsIgnoreCase(s)) {
                    event.getChannel().sendMessage("Discord linked.");
                    Player p = (Player)Discraft.getInstance().getDiscordHandler().getKeysFromValue(event.getMessage());
                    Discraft.getInstance().getPlayerDataHandler().link(p, event.getAuthor());
                }
            }
        }
    }
}
