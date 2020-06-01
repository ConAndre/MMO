package com.andre.build.main.Npcs;

import com.andre.build.main.Main;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class NPCEnchant implements Listener {
    private Main main;
    private NPCLib npcLib;

    private NPC dungeonNPC;

    public NPCEnchant(Main main) {
        this.main = main;
        this.npcLib = new NPCLib(main);
        load();
        Bukkit.getPluginManager().registerEvents(this, main);
    }
    public void display(Player player) {
        dungeonNPC.show(player);
    }
    private void load() {
        MineSkinFetcher.fetchSkinFromIdAsync(566317012, skin -> {
            dungeonNPC = npcLib.createNPC(Arrays.asList( ChatColor.BLUE + "" + ChatColor.BOLD + "Enchanter", ChatColor.GRAY + "" + ChatColor.BOLD + "Click to open enchant menu"));
            dungeonNPC.setLocation(new Location(Bukkit.getWorlds().get(0), -1234.5, 88, -771.5, -180, 0));
//            dungeonNPC.setItem(NPCSlot.MAINHAND, new ItemStack(Material.IRON_SWORD));
            dungeonNPC.setSkin(skin);
            dungeonNPC.create();
        });
    }

    @EventHandler
    public void onNPC(NPCInteractEvent e) {
        Player player = e.getWhoClicked();
        if (e.getNPC() == dungeonNPC) {
//            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "warp dungeon4 " + player.getName());
            player.chat("/enchants");
        }

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        display(player);
    }

}
