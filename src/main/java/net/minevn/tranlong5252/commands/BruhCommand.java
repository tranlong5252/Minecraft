package net.minevn.tranlong5252.commands;

import net.minevn.tranlong5252.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BruhCommand implements CommandExecutor {

    private final Main plugin;
    public BruhCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("bruh")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(plugin.cooldown.containsKey(sender.getName()) && !player.hasPermission("tl.cooldown")) {
                    long secondsLeft = ((plugin.cooldown.get(sender.getName())/1000)+ plugin.cooldownTime) - (System.currentTimeMillis()/1000);
                    if(secondsLeft>0) {
                        // Still cooling down
                        player.sendMessage("§cVui lòng chờ sau " + "§e" + secondsLeft + "§c giây");
                        return true;
                    }
                }

                // No cooldown found or cooldown has expired, save new cooldown
                plugin.cooldown.put(sender.getName(), System.currentTimeMillis());
                Bukkit.broadcastMessage("§f" + ((Player) sender).getDisplayName() + "§e" + " Đã §iBRUH đến tất cả người chơi");
                }
        else {
            //console
                Bukkit.broadcastMessage("§f" + sender.getName() + "§e Đã §iBRUH đến tất cả người chơi");
            return true;
            }
        }
        return false;
    }
}



