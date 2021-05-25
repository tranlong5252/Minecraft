package net.minevn.tranlong5252.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class TridentListener implements Listener {

    @EventHandler
    public void trident(PlayerInteractEvent e, Plugin plugin) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (i.getType() == Material.BOOK && p.hasPermission("tdt.throw") && p.getGameMode() == GameMode.CREATIVE) {

            Location loc = p.getLocation();
            Vector playerDirection = loc.getDirection();
            Trident trident = p.launchProjectile(Trident.class, playerDirection.multiply(3F));
            trident.setMetadata("bonk", new FixedMetadataValue(plugin, "cuccu"));
            trident.setGlowing(true);
            p.playSound(loc, Sound.ITEM_TRIDENT_THROW, 5.0F, 1F);
            trident.setPickupStatus(Trident.PickupStatus.DISALLOWED);
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e){
        if (!e.getEntity().hasMetadata("bonk")) {
            return;
        }

        if (e.getHitEntity() != null) {
            Entity env = e.getHitEntity();
            if(env instanceof LivingEntity) {
                ((LivingEntity) env).setHealth(0);
                env.sendMessage("vì bạn xứng đáng");

            }
            return;
        }
        if (e.getHitBlock() != null) {
            Block b = e.getHitBlock();
            if (b.getType() == Material.TNT){
                b.setType(Material.AIR);
                TNTPrimed tnt = b.getWorld().spawn(b.getLocation(), TNTPrimed.class);
                tnt.setFuseTicks(0);
            }
        }
    }
}
