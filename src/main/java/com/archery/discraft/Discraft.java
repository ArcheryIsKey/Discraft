package com.archery.discraft;

import com.archery.discraft.commands.utils.CommandHandler;
import com.archery.discraft.discord.listeners.MessageReceived;
import com.archery.discraft.discord.listeners.ReadyListener;
import com.archery.discraft.hooks.PlaceholderAPIExpansion;
import com.archery.discraft.hooks.ProtocolLibHook;
import com.archery.discraft.listeners.ConnectedListener;
import com.archery.discraft.utils.DiscordHandler;
import com.archery.discraft.utils.PlayerDataHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Discraft extends JavaPlugin {

    private static Discraft instance;
    private DiscordHandler discordHandler;
    private PlayerDataHandler playerDataHandler;
    private ProtocolLibHook protocolLibHook;
    private JDA jda;

    public Discraft() {
        instance = this;
    }

    @Override
    public void onEnable() {

        instance = this;

        getServer().getPluginManager().registerEvents(new ConnectedListener(), this);
        Objects.requireNonNull(getCommand("discraft")).setExecutor(new CommandHandler());

        if(getConfig().get("TOKEN") != null) {

            try {

                jda = JDABuilder.createDefault(getConfig().get("TOKEN").toString()).setActivity(Activity.playing("Minecraft")).addEventListeners(new ReadyListener()).build();

            } catch (LoginException e) {

                e.printStackTrace();
            }
        }

        jda.addEventListener(new MessageReceived());

        discordHandler = new DiscordHandler();
        playerDataHandler = new PlayerDataHandler();

        enablePlaceholderAPI();
        enableProtocolLib();

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

        if (jda != null) {
            CompletableFuture<Void> shutdownTask = new CompletableFuture<>();

            jda.addEventListener(new ListenerAdapter() {

                @Override
                public void onShutdown(ShutdownEvent event) {
                    shutdownTask.complete(null);
                }
            });

            jda.shutdownNow();
            jda = null;

            try {

                shutdownTask.get(5, TimeUnit.SECONDS);

            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                getLogger().warning("JDA took too long to shut down, skipping");
            }
        }
        instance = null;
    }

    public void enablePlaceholderAPI() {

        if(getConfig().getStringList("DisabledDependencies").contains("PlaceholderAPI")) return;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIExpansion(this).register();
        }
    }

    public void enableProtocolLib() {

        if(getConfig().getStringList("DisabledDependencies").contains("ProtocolLib")) return;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            protocolLibHook = new ProtocolLibHook();
        }
    }

    public static Discraft getInstance() {
        return instance;
    }

    public DiscordHandler getDiscordHandler() {
        return discordHandler;
    }

    public PlayerDataHandler getPlayerDataHandler() {
        return playerDataHandler;
    }

    public JDA getJDA() {
        return jda;
    }

    public ProtocolLibHook getProtocolLibHook() {
        return protocolLibHook;
    }
}
