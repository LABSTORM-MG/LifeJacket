package me.labstorm.lifejacket.listeners;

import me.labstorm.lifejacket.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;


public class PlayerMoveListener implements Listener {

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent e) {
        // true if feet in water
        if (!e.getFrom().getBlock().getBlockData().getMaterial().equals(Material.WATER)) {
            return;
        }

        ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
        if (chestplate == null) {
            return;
        }

        if (!chestplate.getType().equals(Material.LEATHER_CHESTPLATE)) {
            return;
        }

        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        if (leatherArmorMeta == null) {
            return;
        }

        Location loc = e.getFrom();
        loc.setY((loc.getY() + e.getPlayer().getEyeHeight()) - Main.SINK_DEPTH);
        // true if distance between eyes and water is smaller than set in config
        if (!loc.getBlock().getBlockData().getMaterial().equals(Material.WATER)) {
            return;
        }

        if (!String.valueOf(leatherArmorMeta.getColor()).equals("Color:[rgb0xF9801D]")) { //Color:[rgb0xF9801D]
            return;
        }

        Player p = e.getPlayer();
        Vector v = p.getVelocity();
        p.setVelocity(new Vector(v.getX(), Main.UPDRAFT, v.getZ()));

    }

}
