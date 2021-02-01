package net.minevn.tranlong5252;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getLocPlus implements CommandExecutor{

    @Override @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getloc+")) {
            if ((sender instanceof Player)) {
                Player p = (Player) sender;
                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();

                String locSerialized = x + ", " + y + ", " + z;

                if (args.length == 0) {
                    TextComponent message = new TextComponent("§a§lTọa độ: " + ChatColor.WHITE + locSerialized);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, locSerialized));
                    message.setHoverEvent(new HoverEvent((HoverEvent.Action.SHOW_TEXT),
                            new ComponentBuilder("Click để copy tọa độ").color(ChatColor.GRAY).italic(true).create()));
                    p.spigot().sendMessage(message);
                    return true;
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "Bạn phải là Player mới có thể sử dụng được");
                return true;
            }
        }
        return false;
    }
}