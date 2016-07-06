package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.runSubCMD;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class addCMDExecutor implements runSubCMD {

    private Parkour par;
    private String[] args;
    private YamlConfiguration p_list;

    public addCMDExecutor(Parkour p, String[] a, YamlConfiguration yml) {
        this.par = p;
        this.args = a;
        this.p_list = yml;
    }

    public void run(Player player) {
        if (!(p_list.isSet(args[1]))) {
            p_list.createSection(args[1]);
            p_list.set(args[1] + ".file", "parkour_" + args[1].toLowerCase());
            int idcount = p_list.getInt("idcounter") + 1;
            p_list.set(args[1] + ".id", idcount);
            p_list.set("idcounter", idcount);
            try {
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            player.sendMessage("That parkour already exists! Please choose another name!");
        }
    }
    private void saveConfig() throws IOException {
        File file = new File(par.getDataFolder(), "parkour_list.yml");
        p_list.save(file);
    }
}
