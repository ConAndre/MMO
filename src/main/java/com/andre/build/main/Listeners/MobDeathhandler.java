package com.andre.build.main.Listeners;

import com.andre.build.main.Main;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

public class MobDeathhandler implements Listener {
    private double rewardHealthScale = .5;
    private double range = 16;

    @EventHandler
    public void SharedMoneyOnDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof Mob)) return;
        if (event.getEntity().getName().equalsIgnoreCase("fallop")) return; // Friendly Mob
        if (event.getEntity().getName().equalsIgnoreCase("mini-eye")) return; // Friendly Mob
        if (event.getEntity().getName().equalsIgnoreCase("ghost")) return; // Friendly Mob
        if (event.getEntity().getName().contains("Teleport")) return; // Teleport Mob

        Location loc = event.getEntity().getLocation();

        for (org.bukkit.entity.Entity e : loc.getWorld().getNearbyEntities(loc, range, range, range)) {
                if (!(e instanceof Player)) continue;
                Player player = (Player) e;
                double reward = Math.floor(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*rewardHealthScale + event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()*rewardHealthScale*Math.random());
                EconomyResponse r = Main.getEconomy().depositPlayer(player, reward);
                if (r.transactionSuccess()) {
                    player.sendMessage(ChatColor.GOLD + "You received $" + ChatColor.GREEN + reward + ChatColor.GOLD + " for slaying " + ChatColor.DARK_RED + event.getEntity().getName());
                }
        }
    }
}