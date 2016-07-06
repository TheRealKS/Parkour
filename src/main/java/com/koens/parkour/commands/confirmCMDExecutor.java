package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class confirmCMDExecutor {
    /* List of possible confirm modes:
    0 = delete
     */

    private Parkour par;

    public confirmCMDExecutor(Parkour p) {
        this.par = p;
    }

    public void confirm(Player player, int mode, YamlConfiguration p_list) {
        switch (mode) {
            case 1:
                if (player.hasMetadata("remainingconfirm")) {
                    try {
                        delete(player, p_list);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        par.getLogger().warning(e.getMessage());
                    }
                    player.sendMessage("Parkour " + player.getMetadata("selectedParkour").get(0).asString() + " has been deleted!");
                    player.removeMetadata("selectedParkour", par);
                    player.removeMetadata("confirmtype", par);
                }
                break;
            default:
                player.sendMessage("Nothing to confirm right now!");
        }
    }

    private void delete(Player player, YamlConfiguration p_list) throws IOException {
        String parkour = player.getMetadata("selectedParkour").get(0).asString();
        p_list.set(parkour, null);
        File yml = new File(par.getDataFolder(), "parkour_list.yml");
        p_list.save(yml);
        String filename = p_list.getString(parkour + ".file");
        player.sendMessage(filename);
        File path = new File(par.getDataFolder(), "parkours");
        File file = new File(path, filename + ".yml");
        file.delete();
    }
}
