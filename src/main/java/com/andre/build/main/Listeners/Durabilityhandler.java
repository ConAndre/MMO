package com.andre.build.main.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class Durabilityhandler implements Listener {
    @EventHandler
    public void PreventDurabilityLoss(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}
