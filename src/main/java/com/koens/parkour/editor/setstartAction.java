package com.koens.parkour.editor;

import com.koens.parkour.Parkour;
import com.koens.parkour.util.editorAction;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class setstartAction implements editorAction {

    private String parkour;
    private Parkour par;
    private YamlConfiguration p_list;
    private String[] args;

    public setstartAction(Parkour p, YamlConfiguration yml, String[] a) {
        this.par = p;
        this.p_list = yml;
        this.args = a;
    }

    @Override
    public void run(Player player) {
        this.parkour = player.getMetadata("selectedParkour").get(0).asString();
        if (args.length == 2) {
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            List<Integer> listedcoords = new ArrayList<>(Arrays.asList(x, y, z));
            File path = new File(par.getDataFolder(), "parkours");
            File file = new File(path, p_list.getString(parkour + ".file") + ".yml");
            YamlConfiguration parkourfile = YamlConfiguration.loadConfiguration(file);
            parkourfile.set("start", listedcoords);
            try {
                saveConfig(file, parkourfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage("Start location for parkour " + parkour + " has been set to (" + x + "," + y + "," + z + ")!");
        }
        else if (args.length == 3) {
            String[] coords = args[2].split(",");
            if (coords.length < 3 || coords == null) {
                player.sendMessage("The formatting of the coordinates is incorrect!");
                return;
            }
            List<String> listedcoords = new ArrayList<>(Arrays.asList(coords));
            File path = new File(par.getDataFolder(), "parkours");
            File file = new File(path, p_list.getString(parkour + ".file") + ".yml");
            YamlConfiguration parkourfile = YamlConfiguration.loadConfiguration(file);
            parkourfile.set("start", listedcoords);
            try {
                saveConfig(file, parkourfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage("Start location for parkour " + parkour + " has been set to (" + coords[0] + "," + coords[1] + "," + coords[2] + ")!");
        }
        else {
            player.sendMessage("You have entered an incorrect amount of arguments!");
        }
    }

    @Override
    public void saveConfig(File path, YamlConfiguration file_to_save) throws IOException {
        file_to_save.save(path);
    }
}
