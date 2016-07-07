package com.koens.parkour.commands;

import com.koens.parkour.Parkour;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class parkourCMDExecutor implements CommandExecutor {
    private final Parkour par;
    private final YamlConfiguration p_file;

    public parkourCMDExecutor(Parkour parkour, YamlConfiguration yml) {
        this.par = parkour;
        this.p_file = yml;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            if (commandSender instanceof BlockCommandSender) {
                BlockCommandSender sender = (BlockCommandSender) commandSender;
                if (args[0].equalsIgnoreCase("start")) {
                    startCMDExecutor executor = new startCMDExecutor(par);
                    if (getNearestPlayer(sender.getBlock().getLocation()) != null)
                        executor.run(getNearestPlayer(sender.getBlock().getLocation()));
                    else
                        sender.sendMessage("Couldn't find player!");
                }
                else if (args[0].equalsIgnoreCase("end")) {
                    endCMDExecutor executor = new endCMDExecutor(par);
                    if (getNearestPlayer(sender.getBlock().getLocation()) != null)
                        executor.run(getNearestPlayer(sender.getBlock().getLocation()));
                    else
                        sender.sendMessage("Couldn't find player!");
                }
                else if (args[0].equalsIgnoreCase("reset")) {
                    resetCMDExecutor executor = new resetCMDExecutor(par);
                    if (getNearestPlayer(sender.getBlock().getLocation()) != null)
                        executor.run(getNearestPlayer(sender.getBlock().getLocation()));
                    else
                        sender.sendMessage("Couldn't find player!");
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
            else if (args[0].equalsIgnoreCase("reset")) {
                resetCMDExecutor executor = new resetCMDExecutor(par);
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("select")) {
                selectCMDExecutor executor = new selectCMDExecutor(par, args, p_file);
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("selected")) {
                selectedCMDExecutor executor = new selectedCMDExecutor();
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("add")) {
                addCMDExecutor executor = new addCMDExecutor(par, args, p_file);
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("delete")) {
                deleteCMDExecutor executor = new deleteCMDExecutor(par);
                executor.run(p);
            }
            else if (args[0].equalsIgnoreCase("edit")) {
                if (p.hasMetadata("selectedParkour")) {
                    editCMDExecutor executor = new editCMDExecutor(par, p_file);
                    executor.selector(args, p);
                } else
                    p.sendMessage("You have not selected a parkour yet! Do /parkour select to select a parkour");
            }
            else if (args[0].equalsIgnoreCase("confirm")) {
                confirmCMDExecutor executor = new confirmCMDExecutor(par);
                if (p.hasMetadata("confirmtype"))
                    executor.confirm(p, p.getMetadata("confirmtype").get(0).asInt(), p_file);
                else
                    executor.confirm(p, 0, p_file);
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
