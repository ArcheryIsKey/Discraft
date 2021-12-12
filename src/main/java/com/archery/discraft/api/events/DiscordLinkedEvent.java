package com.archery.discraft.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DiscordLinkedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private String discord;

    private boolean isCancelled;

    public DiscordLinkedEvent(Player player, String discord) {
        this.player = player;
        this.discord = discord;
    }

    public Player getPlayer() {
        return player;
    }

    public String getDiscord() {
        return discord;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
