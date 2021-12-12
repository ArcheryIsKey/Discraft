package com.archery.discraft.discord.listeners;

import com.archery.discraft.Discraft;
import com.archery.discraft.api.events.ConnectedEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.bukkit.Bukkit;

public class ReadyListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            Bukkit.getScheduler().callSyncMethod(Discraft.getInstance(), () -> {
                Bukkit.getPluginManager().callEvent(new ConnectedEvent());
                return null;
            });

        }
    }
}
