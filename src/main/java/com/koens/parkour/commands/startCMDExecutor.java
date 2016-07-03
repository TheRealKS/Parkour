package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.TimeUtils;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;

public class startCMDExecutor implements runSubCMD {

    private final Parkour par;
    private int time;
    private Player player;
    private TimeUtils converter = new TimeUtils();

    public startCMDExecutor(Parkour parkour) {
        this.par = parkour;
    }

    public void run(Player pl) {
        time = 0;
        player = pl;
        player.sendMessage(ChatColor.YELLOW + "Parkour started!");
        player.setMetadata("parkourtime", new FixedMetadataValue(par, time));
        int id = par.getServer().getScheduler().scheduleSyncRepeatingTask(par, new Runnable() {
            public void run() {
                time++;
                player.setMetadata("parkourtime", new FixedMetadataValue(par, time + 1));
            }
        }, 20L, 20L);
        player.setMetadata("timerID", new FixedMetadataValue(par, id));
    }
}
