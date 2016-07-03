package com.koens.parkour;

import com.koens.parkour.commands.parkourCMDExecutor;
import com.koens.parkour.util.subCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Parkour extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getLogger().info("Parkour v" + getDescription().getVersion() + " has been enabled!");
        getCommand("parkour").setExecutor(new parkourCMDExecutor(this));
        subCommandExecutor sce = new subCommandExecutor();
        setupSubCommands(sce);
    }
    @Override
    public void onDisable() {
        getServer().getLogger().info("Parkour v" + getDescription().getVersion() + " has been disabled");
    }
    private void setupSubCommands(subCommandExecutor sub) {

        sub.registerCommand("start");
    }
}
