package com.andre.build.main.Listeners;

import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AttackSpeedhandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerJoinEvent e){
        adjustAttackSpeed(e.getPlayer());
    }

    private void adjustAttackSpeed(Player player){
        World world = player.getWorld();

        double attackSpeed = 20;

        setAttackSpeed(player, attackSpeed);
    }

    private void setAttackSpeed(Player player, double attackSpeed){
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        double baseValue = attribute.getBaseValue();

        if(baseValue != attackSpeed){
            attribute.setBaseValue(attackSpeed);
            player.saveData();
        }
    }


}
