package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class selectCMDExecutor implements runSubCMD {

    private Parkour par;
    private String[] args;
    private YamlConfiguration p_list;

    public selectCMDExecutor(Parkour p, String[] a, YamlConfiguration yml) {
        this.par = p;
        this.args = a;
        this.p_list = yml;
    }

    public void run(Player player) {
        if (player.hasPermission("parkour.edit.select")) {
            int id = getParkourID(args[1]);
            if (id >= 0) {
                player.setMetadata("selectedParkour", new FixedMetadataValue(par, id));
                player.sendMessage("Selected parkour '" + args[1] + "', with id " + id + "!");
            }
            else
                player.sendMessage("That parkour doesn't exist!");
        }
        else
            player.sendMessage("You don't have permission to do that!");
    }

    private int getParkourID(String parkour) {
        if (p_list.isSet(parkour + ".id") && p_list.isInt(parkour + ".id"))
            return p_list.getInt(parkour + ".id");
        else
            return -1;
    }
}

