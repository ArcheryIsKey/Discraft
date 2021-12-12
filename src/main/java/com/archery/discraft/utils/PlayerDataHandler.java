package com.archery.discraft.utils;

import com.archery.discraft.Discraft;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class PlayerDataHandler {

    private HashMap<Player, String> linkedAccounts = new HashMap<>();

    public boolean isLinked(Player player) {
        return linkedAccounts.containsKey(player);
    }

    public String getDiscord(Player player) {
        return linkedAccounts.get(player);
    }

    public void link(Player player, User user) {
        player.getPersistentDataContainer().set(new NamespacedKey(Discraft.getInstance(), "discord"), PersistentDataType.STRING, String.format("%s#%s", user.getName(), user.getDiscriminator()));
        linkedAccounts.put(player, String.format("%s#%s", user.getName(), user.getDiscriminator()));
    }

    public void unlink(Player player) {
        linkedAccounts.remove(player);
    }

}
