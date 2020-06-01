package com.andre.build.main.Commands;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandReroll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!(player.hasPermission("reroll"))) return false;
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            for(MythicItem mythicItem : MythicMobs.inst().getItemManager().getItems()) {
                if (MythicMobs.inst().getItemManager().getItemStack(mythicItem.getInternalName()).isSimilar(mainHand)) {
                    player.getInventory().setItemInMainHand(MythicMobs.inst().getItemManager().getItemStack(mythicItem.getInternalName()));
                    player.updateInventory();
                    return true;
                }
            }
        }
        return false;
    }
}
