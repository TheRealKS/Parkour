package com.koens.parkour.util;

import org.bukkit.command.CommandExecutor;

import java.util.HashMap;

public class subCommandExecutor {
    private HashMap<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public void registerCommand(String command, CommandExecutor exec) {
        commands.put(command.toLowerCase(), exec);
    }
    public CommandExecutor getCommandExecutor(String command) {
        return commands.get(command.toLowerCase());
    }
    public boolean doesCommandExist(String command) {
        return commands.containsKey(command.toLowerCase());
    }
}
