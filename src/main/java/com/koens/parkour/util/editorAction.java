package com.koens.parkour.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public interface editorAction {
    void run(Player player);

    void saveConfig(File path, YamlConfiguration file_to_save) throws IOException ;
}
