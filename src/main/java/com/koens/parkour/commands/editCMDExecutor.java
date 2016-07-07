package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import com.koens.parkour.editor.setstartAction;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class editCMDExecutor {

    private Parkour par;
    private YamlConfiguration p_list;

    public editCMDExecutor(Parkour p, YamlConfiguration yml) {
        this.par = p;
        this.p_list = yml;
    }

    private final static String NO_PERMISSION = "You don't have permission to do that!";

    public void selector(String[] args, Player player) {
        //First convert index 1 to string, to prevent error
        String action = args[1].toLowerCase();
        //Action is always index 1, if action doesn't exist, error
        switch (action) {
            case "setstart":
                if (checkPermission("setstart", player)) {
                    setstartAction executor = new setstartAction(par, p_list, args);
                    executor.run(player);
                }
                else
                    player.sendMessage(NO_PERMISSION);
                break;
            case "setend":
                if (checkPermission("setend", player)) {
                    //coming!!
                }
            case "setcheckpoint":
                if (checkPermission("setcheckpoint", player)) {

                }
            case "editcheckpoint":
                if (checkPermission("editcheckpoint", player)) {
                }
            case "removecheckpoint":
                if (checkPermission("removecheckpoint", player)) {
                }
            case "rename":
                if (checkPermission("rename", player)) {
                }
            //default here
        }
    }

    private boolean checkPermission(String action, Player p) {
        if (p.hasPermission("parkour.edit.*")) {
            return true;
        }
        else {
            return p.hasPermission("parkour.edit." + action);
        }
    }
}
