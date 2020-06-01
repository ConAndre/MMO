package com.andre.build.main.Listeners;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CommandListener implements Listener {


    @EventHandler
    public void RandomizerArg(PlayerCommandPreprocessEvent event) {
        Random random = new Random();
        String msg = event.getMessage();

        if (msg.contains("(") && msg.contains(")")) {
            int pos1 = msg.indexOf("(");
            int pos2 = msg.indexOf(")");
            int check = pos2 - pos1 - 1;
            String randomInt = "";

            for (int i = 0; check > i; i++) {
                randomInt = randomInt.concat(String.valueOf(msg.charAt(pos1 + i + 1)));
            }
            try {
                msg = msg.replace("(" + randomInt + ")", String.valueOf(random.nextInt(Integer.parseInt(randomInt)) + 1));
                event.setMessage(msg);
            } catch (IllegalArgumentException e) {
                Bukkit.getConsoleSender().sendMessage(event.getPlayer() + " tried to used (#) incorrectly: " + event.getMessage());
                return;
            }
        }
    }
}
