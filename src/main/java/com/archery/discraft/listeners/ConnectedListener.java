package com.archery.discraft.listeners;

import com.archery.discraft.api.events.ConnectedEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ConnectedListener implements Listener {

    @EventHandler
    public void onConnect(ConnectedEvent e) {
        Bukkit.getLogger().severe("WOAHHHH it connected! event test.");
    }
}
