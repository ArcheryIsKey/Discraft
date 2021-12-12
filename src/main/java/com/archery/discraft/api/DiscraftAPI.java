package com.archery.discraft.api;

import com.archery.discraft.Discraft;
import com.archery.discraft.utils.DiscordHandler;
import com.archery.discraft.utils.PlayerDataHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.entity.Player;

public class DiscraftAPI {

    private static final Discraft discraft = Discraft.getInstance();
    private static final PlayerDataHandler playerDataHandler = discraft.getPlayerDataHandler();
    private static final DiscordHandler discordHandler = discraft.getDiscordHandler();

    public static void linkPlayer(Player player, User user) {
        playerDataHandler.link(player, user);
    }

    public static void linkPlayer(Player player, String user) {
        playerDataHandler.link(player, User.fromId(user));
    }

    public static void unlinkPlayer(Player player) {
        playerDataHandler.unlink(player);
    }

    public static String getPlayerDiscord(Player player) {
        return playerDataHandler.getDiscord(player);
    }

    public static DiscordHandler getDiscordHandler() {
        return discordHandler;
    }

    public static PlayerDataHandler getPlayerDataHandler() {
        return playerDataHandler;
    }

    public static JDA getBot() {
        return discraft.getJDA();
    }
}
