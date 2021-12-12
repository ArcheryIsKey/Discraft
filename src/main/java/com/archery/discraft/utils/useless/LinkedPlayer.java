package com.archery.discraft.utils.useless;

import com.archery.discraft.Discraft;
import com.archery.discraft.api.events.DiscordLinkedEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class LinkedPlayer {

    private final Player player;
    private User discord;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LinkedPlayer(Player player, User discord) {
        this.player = player;
        this.discord = discord;
        Bukkit.getPluginManager().callEvent(new DiscordLinkedEvent(player, String.format("%s#%s", discord.getName(), discord.getDiscriminator())));
    }

    public void save() {
        try {
            gson.toJson(this, new FileWriter(new File(Discraft.getInstance().getDataFolder() + File.separator + "playerData", player.getUniqueId().toString())));
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.WARNING, String.format("Player file for player: %s could not be created", player.getName()));
        }
    }

    public User getDiscord() {
        return discord;
    }

    public void setDiscord(User discord) {
        this.discord = discord;
    }

    public Player getPlayer() {
        return player;
    }
}
