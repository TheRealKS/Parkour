package com.koens.parkour.editor;

import com.koens.parkour.util.editorAction;
import org.bukkit.entity.Player;

public class setstartAction implements editorAction {

    @Override
    public void run(Player player) {
        player.sendMessage("COMMING!!!");
    }

    @Override
    public void saveConfig() {

    }
}
