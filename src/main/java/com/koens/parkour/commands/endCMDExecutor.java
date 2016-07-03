package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.entity.Player;

public class endCMDExecutor implements runSubCMD {

    private final Parkour par;

    public endCMDExecutor(Parkour parkour) {
        this.par = parkour;
    }

    public void run(Player p) {

    }
}
