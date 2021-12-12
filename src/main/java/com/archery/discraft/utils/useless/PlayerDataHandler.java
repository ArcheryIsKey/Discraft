package com.archery.discraft.utils.useless;

import com.archery.discraft.Discraft;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerDataHandler { // TODO: Custom LinkedPlayer object containing all data (Player object, Discord)

    private File playerData;
    private HashMap<UUID, String> linkedAccounts = new HashMap<>();

    public PlayerDataHandler() {
        playerData = new File(Discraft.getInstance().getDataFolder(), "playerData");
        playerData.mkdirs();

        for(File files : playerData.listFiles()) {
            FileConfiguration playerFile = YamlConfiguration.loadConfiguration(files);
            if(playerFile.getString("Discord.ID") != null) {
                linkedAccounts.put(UUID.fromString(files.getName()), playerFile.getString("Discord.ID"));
            }
        }
    }

    public boolean isLinked(Player player) {
        return linkedAccounts.containsKey(player.getUniqueId());
    }

    public User getPlayer(UUID uuid) throws NullPointerException {
        File file = new File(playerData, uuid + ".yml");
        if(!file.exists()) return null;

        FileConfiguration playerFile = YamlConfiguration.loadConfiguration(file);
        return User.fromId(playerFile.getString("Discord.ID"));
    }

    public void unlink(UUID uuid) {
        linkedAccounts.remove(uuid);
        File file = new File(playerData, uuid + ".yml");
        if(!file.exists()) return;
        boolean delete = file.delete();

        if (!delete) {
            Bukkit.getLogger().log(Level.WARNING, String.format("Player file for player: %s could not be deleted, but player is now unlinked until next reload.", Bukkit.getOfflinePlayer(uuid).getName()));
        }
    }

    public FileConfiguration getPlayerFile(UUID uuid) {
        File file = new File(playerData, uuid + ".yml");
        if(!file.exists()) return null;
        FileConfiguration playerFile = YamlConfiguration.loadConfiguration(file);
        return playerFile;
    }

    public File getPlayerDataFolder() {
        return playerData;
    }

    public HashMap<UUID, String> getLinkedAccounts() {
        return linkedAccounts;
    }
}