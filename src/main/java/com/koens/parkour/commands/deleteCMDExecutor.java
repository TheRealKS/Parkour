package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class deleteCMDExecutor implements runSubCMD {

    private Parkour par;
    private Player player;

    public deleteCMDExecutor(Parkour p) {
        this.par = p;
    }

    @Override
    public void run(Player pl) {
        this.player = pl;
        if (player.hasMetadata("parkour.edit.delete") || player.hasPermission("parkour.edit.*")) {
            player.sendMessage("Are you sure you want to delete the selected parkour? If you are sure about this, do /parkour confirm. /parkour confirm will be available for 15 seconds from now!");
            player.setMetadata("confirmtype", new FixedMetadataValue(par, 1));
            player.setMetadata("remainingconfirm", new FixedMetadataValue(par, 15));
            int confirmer = par.getServer().getScheduler().scheduleSyncRepeatingTask(par, new Runnable() {
                @Override
                public void run() {
                    int remaining = player.getMetadata("remainingconfirm").get(0).asInt();
                    if (remaining > 0) {
                        remaining--;
                        player.setMetadata("remainingconfirm", new FixedMetadataValue(par, remaining));
                        player.sendMessage(Integer.toString(remaining));
                    } else {
                        player.removeMetadata("remainingconfirm", par);
                        disableTimer(player.getMetadata("TEMP").get(0).asInt());
                    }
                }
            }, 20L, 20L);
            player.setMetadata("TEMP", new FixedMetadataValue(par, confirmer));
        }
        else {
            player.sendMessage("You don't have permission to do that!");
        }
    }
    private void disableTimer(int id) {
        par.getServer().getScheduler().cancelTask(id);
    }
}
