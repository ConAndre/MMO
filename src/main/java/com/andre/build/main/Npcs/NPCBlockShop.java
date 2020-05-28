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

public class NPCBlockShop implements Listener {
    private Main main;
    private NPCLib npcLib;

    private NPC blockNPC;

    public NPCBlockShop(Main main) {
        this.main = main;
        this.npcLib = new NPCLib(main);
        load();
        Bukkit.getPluginManager().registerEvents(this, main);
    }
    public void display(Player player) {
        blockNPC.show(player);
    }
    private void load() {
        MineSkinFetcher.fetchSkinFromIdAsync(1236205815, skin -> {
            blockNPC = npcLib.createNPC(Arrays.asList(ChatColor.GREEN + "" + ChatColor.BOLD + "Block Shop", ChatColor.GRAY + "" + ChatColor.BOLD + "Click to purchase blocks!"));
            blockNPC.setLocation(new Location(Bukkit.getWorlds().get(0), 733.5,63,-194.5 , -90, 0));
            blockNPC.setItem(NPCSlot.MAINHAND, new ItemStack(Material.IRON_SWORD));
            blockNPC.setSkin(skin);
            blockNPC.create();
        });
    }

    @EventHandler
    public void onNPC(NPCInteractEvent e) {
        Player player = e.getWhoClicked();
        if (e.getNPC() == blockNPC) {
            player.chat("/blockshop");;
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        display(player);
    }

}
