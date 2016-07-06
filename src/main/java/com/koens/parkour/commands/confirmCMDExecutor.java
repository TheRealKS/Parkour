package com.koens.parkour.commands;

import org.bukkit.entity.Player;

public class confirmCMDExecutor {
    /* List of possible confirm modes:
    0 = delete
     */

    public void confirm(Player player, int mode) {
        switch (mode) {
            case 0:
                if (player.hasMetadata("remainingconfirm")) {

                }
        }
    }

    private void delete(Player player, int mode) {

    }
}
