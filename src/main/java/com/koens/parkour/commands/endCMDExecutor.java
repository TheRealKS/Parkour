package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.TimeUtils;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class endCMDExecutor implements runSubCMD {

    private final Parkour par;

    private TimeUtils converter = new TimeUtils();

    public endCMDExecutor(Parkour parkour) {
        this.par = parkour;
    }

    public void run(Player p) {
        int timerid = p.getMetadata("timerID").get(0).asInt();
        Bukkit.getScheduler().cancelTask(timerid);
        p.sendMessage(converter.secondsToHHMMSS(p.getMetadata("parkourtime").get(0).asInt()));
        p.removeMetadata("parkourtime", par);
    }
}
