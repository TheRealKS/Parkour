package com.koens.parkour.util;

import org.bukkit.entity.Player;

public interface editorAction {
    void run(Player player);

    void saveConfig();
}
