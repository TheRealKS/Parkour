package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class resetCMDExecutor implements runSubCMD {

    private Parkour par;

    public resetCMDExecutor(Parkour p) {
        this.par = p;
    }

    public void run(Player player) {
        if (player.hasMetadata("parkourtime") && player.hasMetadata("timerID")) {
            player.setMetadata("parkourtime", new FixedMetadataValue(par, 0));
            player.sendMessage("Your timer is reset to 00.00.00");
        }
        else {
            player.sendMessage("You have not started a parkour yet! Please do /parkour start to start the parkour");
        }
    }
}
