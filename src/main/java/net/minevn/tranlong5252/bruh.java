package net.minevn.tranlong5252;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bruh implements CommandExecutor {
    private final Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("bruh")) {
            if (sender instanceof Player) {
                //todo: add permissions for player to remove cooldown
                Player player = (Player) sender;
                if(plugin.cooldowns.containsKey(sender.getName()) && !player.hasPermission("tl.cooldown")) {
                    long secondsLeft = ((plugin.cooldowns.get(sender.getName())/1000)+ plugin.cooldownTime) - (System.currentTimeMillis()/1000);
                    if(secondsLeft>0) {
                        // Still cooling down
                        player.sendMessage(ChatColor.RED + "Chờ thêm " + ChatColor.YELLOW + secondsLeft + " giây" + ChatColor.RED + " nữa đi iem!");
                        return true;
                    }
                }

                // No cooldown found or cooldown has expired, save new cooldown
                plugin.cooldowns.put(sender.getName(), System.currentTimeMillis());
                Bukkit.broadcastMessage(((Player) sender).getDisplayName() + ChatColor.YELLOW + " Đã BRUH đến tất cả người chơi");
                }
        else {
                Bukkit.broadcastMessage(sender.getName() + ChatColor.YELLOW + " Đã BRUH đến tất cả người chơi");
            return true;
            }
        }
        return false;
    }
}



