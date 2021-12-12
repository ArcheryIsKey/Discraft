package com.archery.discraft.commands.utils;

import org.bukkit.command.CommandSender;

public abstract class CommandExecutor {

    private String command;
    private String permission;
    private int maxLength;
    private String usage;

    public abstract void execute(CommandSender sender, String[] args);

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String getUsage() { return usage; }

    public void setUsage(String usage) { this.usage = usage; }
}
