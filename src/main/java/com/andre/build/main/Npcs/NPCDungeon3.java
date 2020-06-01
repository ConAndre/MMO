package com.andre.build.main.Npcs;

import com.andre.build.main.Main;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.state.NPCSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class NPCDungeon3 implements Listener {
    private Main main;
    private NPCLib npcLib;

    private NPC dungeonNPC;

    public NPCDungeon3(Main main) {
        this.main = main;
        this.npcLib = new NPCLib(main);
        load();
        Bukkit.getPluginManager().registerEvents(this, main);
    }
    public void display(Player player) {
        dungeonNPC.show(player);
    }
    private void load() {
        MineSkinFetcher.fetchSkinFromIdAsync(1943555863, skin -> {
            dungeonNPC = npcLib.createNPC(Arrays.asList( ChatColor.RED + "" + ChatColor.BOLD + "Dungeon 3", ChatColor.GRAY + "" + ChatColor.BOLD + "Click to enter dungeon 3"));
            dungeonNPC.setLocation(new Location(Bukkit.getWorlds().get(0), -1263.5, 65, -838.5, 45, 0));
//            dungeonNPC.setItem(NPCSlot.MAINHAND, new ItemStack(Material.IRON_SWORD));
            dungeonNPC.setSkin(skin);
            dungeonNPC.create();
        });
    }

    @EventHandler
    public void onNPC(NPCInteractEvent e) {
        Player player = e.getWhoClicked();
        if (e.getNPC() == dungeonNPC) {
//            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp dungeon3 " + player.getName());
            player.chat("/warp dungeon3");

        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        display(player);
    }

}
