package com.andre.build.main.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHelp implements Listener {
    @EventHandler
    public void NotifyPlayerOnDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) return;
        if (!(event.getEntity().getWorld().getName().equalsIgnoreCase("world"))) return; //dungeon world
        event.getEntity().getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD  + "If you are having trouble " +
                ChatColor.RED + "" + ChatColor.BOLD + "surviving " +
                ChatColor.GREEN + "" + ChatColor.BOLD + "try gathering more loot from " +
                ChatColor.RED + "" + ChatColor.BOLD + "dungeons");

    }
}
