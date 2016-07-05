package com.koens.parkour;

import com.koens.parkour.commands.parkourCMDExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Parkour extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        File file = new File(getDataFolder(), "parkours_list.yml");
        YamlConfiguration p_list = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                p_list.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        p_list = YamlConfiguration.loadConfiguration(file);
        getServer().getLogger().info("Parkour v" + getDescription().getVersion() + " has been enabled!");
        getCommand("parkour").setExecutor(new parkourCMDExecutor(this, p_list));
    }
    @Override
    public void onDisable() {
        getServer().getLogger().info("Parkour v" + getDescription().getVersion() + " has been disabled");
    }
}
