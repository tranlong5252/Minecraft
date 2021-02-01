package net.minevn.tranlong5252.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        event.setJoinMessage(ChatColor.GREEN + "[+] " + ChatColor.YELLOW + event.getPlayer().getName());
        p.playSound(loc, Sound.ENTITY_ILLUSIONER_CAST_SPELL, 3,1);
        p.sendTitle("", "§6§o§lWelcome",0, 20, 5);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(ChatColor.RED + "[-] " + ChatColor.YELLOW + event.getPlayer().getName());
    }
}
