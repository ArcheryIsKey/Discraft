package com.archery.discraft.utils;

import com.archery.discraft.Discraft;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DiscordHandler {

    private ArrayList<Long> configChannels = new ArrayList<>();

    private HashMap<Player, String> linking = new HashMap<>();
    private ArrayList<Long> channels = new ArrayList<>();
    private JDA jda = Discraft.getInstance().getJDA();

    public DiscordHandler() {

        Discraft.getInstance().getConfig().getStringList("Channels").forEach(channels -> {
            configChannels.add(Long.parseLong(channels));
        });

        configChannels.forEach(channels -> {
            if (jda.getTextChannelById(channels) != null) {
                this.channels.add(channels);
            }
        });

    }

    public boolean isInChannel(Long l) {
        return channels.contains(l);
    }

    public boolean isInChannel(GuildChannel channel) {
        return channels.contains(channel.getIdLong());
    }

    public boolean isLinking(Player player) {
        return linking.containsKey(player);
    }

    public boolean isLinking(String code) {
        return linking.containsValue(code);
    }

    public String getCode(Object player) {
        return linking.get(player);
    }

    public HashMap<Player, String> getLinking() {
        return linking;
    }

    public void startLinking(Player p, String code) {
        linking.put(p, code);
    }

    public String generateCode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public Object getKeysFromValue(Object value) {
        for (Object o : linking.keySet()) {
            if (linking.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}