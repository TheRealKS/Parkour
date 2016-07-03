package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class parkourCMDExecutor implements CommandExecutor {
    private final Parkour par;

    public parkourCMDExecutor(Parkour parkour) {
        this.par = parkour;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            if (commandSender instanceof BlockCommandSender) {
                BlockCommandSender sender = (BlockCommandSender) commandSender;
                if (args[0].equalsIgnoreCase("start")) {
                    startCMDExecutor executor = new startCMDExecutor(par);
                    executor.run(getNearestPlayer(sender.getBlock().getLocation()));
                }
                else if (args[0].equalsIgnoreCase("end")) {

                }
            }
        }
        else {
            Player p = (Player) commandSender;
            if (args[0].equalsIgnoreCase("start")) {
                startCMDExecutor executor = new startCMDExecutor(par);
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("end")) {
                endCMDExecutor executor = new endCMDExecutor(par);
                executor.run(p);
            }
        }
        return true;
    }
    private Player getNearestPlayer(Location loc) {
        Player nearest = null;
        Location initial = new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ());
        double closestDist = initial.distanceSquared(loc);
        for (Player i : Bukkit.getOnlinePlayers()) {
            if (i.getLocation().distanceSquared(loc) < closestDist) {
                nearest = i;
            }
        }
        return nearest;
    }
}
