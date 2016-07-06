package com.koens.parkour.commands;

import com.koens.parkour.util.runSubCMD;
import org.bukkit.entity.Player;

public class selectedCMDExecutor implements runSubCMD {
    public void run(Player player) {
        if (player.hasMetadata("selectedParkour")) {
            player.sendMessage("You currently have parkour " + player.getMetadata("selectedParkour").get(0).asString() + " selected!");
        } else {
            player.sendMessage("You have not selected a parkour yet! Do /parkour select to select a parkour!");
        }
    }
}
