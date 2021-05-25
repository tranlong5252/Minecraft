package net.minevn.tranlong5252.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minevn.tranlong5252.tasks.Tasks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class RideListener implements Listener {

    @EventHandler
    public void Ride(PlayerInteractEntityEvent event, Plugin plugin) {
        Player player = event.getPlayer();
        if (!(event.getRightClicked() instanceof LivingEntity) || !player.hasPermission("tl.ride")) return;
        if (player.getPassengers().isEmpty() && player.isSneaking()) {
            LivingEntity target = (LivingEntity) event.getRightClicked();
            player.addPassenger(target);
            target.setMetadata("rode", new FixedMetadataValue(plugin, true));
            Tasks.sync(() -> target.removeMetadata("rode", plugin), 1, plugin);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§eBạn đã đem " + "§f" + target.getName() + " §elên người"));
            if (target instanceof Player) {
                Player pTarget = (Player) target;
                pTarget.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§eBạn đã ngồi lên " + "§f" + player.getName()));
            }
        }
    }

    @EventHandler
    public void applyInteractBlock(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player player = event.getPlayer();
        if (!player.getPassengers().isEmpty()) {
            for (Entity en : player.getPassengers()) {
                if (!en.hasMetadata("rode")) {
                    player.removePassenger(en);
                    en.setVelocity(player.getLocation().getDirection());
                }
            }
        }
    }
}
