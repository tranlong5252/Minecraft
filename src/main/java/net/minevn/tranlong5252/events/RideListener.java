package net.minevn.tranlong5252.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class RideListener implements Listener {

    @EventHandler @Deprecated
    public void Ride(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();

        if (e.getPlayer().isSneaking() && e.getRightClicked() instanceof Player
                && p.hasPermission("tl.ride")) {

            Player other = (Player) e.getRightClicked();
            p.setPassenger(other);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§eBạn đã đem " + "§f" + other.getName() + " §elên người"));
            other.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§eBạn đã ngồi lên " + "§f" + p.getName()));

        }
    }
    @EventHandler @Deprecated
    public void Eject(PlayerInteractEvent event) {
        Player thrower = event.getPlayer();
        Player toThrow = (Player)thrower.getPassenger();
        if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getPlayer().getPassenger() != null && event.getPlayer().getPassenger() instanceof Player) {
            toThrow.getVehicle().eject();

            Vector dir = thrower.getLocation().getDirection();
            toThrow.setVelocity(dir.multiply(0.7));
            toThrow.setFallDistance(-10.0F);
            thrower.sendMessage("bump");

        }
    }
}
