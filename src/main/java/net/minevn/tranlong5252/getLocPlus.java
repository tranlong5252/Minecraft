package net.minevn.tranlong5252;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getLocPlus implements CommandExecutor{
    @Override @Deprecated
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getloc+")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                int x1 = p.getLocation().getBlockX();
                int y1 = p.getLocation().getBlockY();
                int z1 = p.getLocation().getBlockZ();

                String locSerialized = x1 + " " + y1 + " " + z1;

                if (args.length == 0) {
                    TextComponent message = new TextComponent("§a§lTọa độ: " + ChatColor.WHITE + locSerialized);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, locSerialized));
                    message.setHoverEvent(new HoverEvent((HoverEvent.Action.SHOW_TEXT),
                            new ComponentBuilder("Click để copy tọa độ").color(ChatColor.GRAY).italic(true).create()));
                    p.spigot().sendMessage(message);
                    return true;
                }
                if (args.length == 1) {
                    String arg = args[0];
                    Player p2 = Bukkit.getPlayer(arg);

                    if (!(sender.hasPermission("tl52.getloc"))) {
                        sender.sendMessage("§cLỗi: §eBạn không có quyền sử dụng câu lệnh này");
                        return true;
                    }
                    if ((p2 != null) && p2.isOnline()) {

                        int x2 = p2.getLocation().getBlockX();
                        int y2 = p2.getLocation().getBlockY();
                        int z2 = p2.getLocation().getBlockZ();

                        String locSerialized2 = x2 + " " + y2 + " " + z2;

                        TextComponent message = new TextComponent("§a§lTọa độ: " + ChatColor.WHITE + locSerialized2);
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, locSerialized2));
                        message.setHoverEvent(new HoverEvent((HoverEvent.Action.SHOW_TEXT),
                                new ComponentBuilder("Click để copy tọa độ").color(ChatColor.GRAY).italic(true).create()));
                        p.spigot().sendMessage(message);
                    } else {
                        p.sendMessage("§cLỗi: §eKhông thể tìm thấy " + "§f" + arg + "§e!");
                        return true;
                    }
                }
            } else {
                if (args.length == 0) {
                    sender.sendMessage("tìm tọa độ của người chơi \n /getloc+ [player]");
                    return true;
                }
                String arg = args[0];
                Player p2 = Bukkit.getPlayer(arg);

                if (args.length == 1) {
                    if ((p2 != null) && p2.isOnline()) {
                        int x2 = p2.getLocation().getBlockX();
                        int y2 = p2.getLocation().getBlockY();
                        int z2 = p2.getLocation().getBlockZ();

                        String locSerialized2 = x2 + " " + y2 + " " + z2;

                        sender.sendMessage(ChatColor.GREEN + "Tọa độ: " + ChatColor.WHITE + locSerialized2);
                    } else {
                        sender.sendMessage("§cLỗi: §eKhông thể tìm thấy " + "§f" + arg + "§e!");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
/* } else {
        sender.sendMessage("tìm tọa độ của người chơi \n /getloc+ [player]");
        ["",{"text":"T\u1ecda \u0111\u1ed9: ","bold":true,"color":"#0EE293"},{"text":"locSerialized","clickEvent":{"action":"suggest_command","value":"locSerialized"},"hoverEvent":{"action":"show_text","contents":"Click \u0111\u1ec3 copy t\u1ecda \u0111\u1ed9"}}]
*/