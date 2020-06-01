package com.andre.build.main;

import java.util.logging.Logger;

import com.andre.build.main.Commands.CommandReroll;
import com.andre.build.main.Listeners.*;

import com.andre.build.main.Npcs.*;
import net.jitse.npclib.NPCLib;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private NPCLib library;
    private static NPCDungeon npcDungeon;
    private static NPCDungeon1 npcDungeon1;
    private static NPCDungeon2 npcDungeon2;
    private static NPCDungeon3 npcDungeon3;
    private static NPCDungeon4 npcDungeon4;
    private static NPCRaid npcRaid;
    private static NPCDropTable npcDropTable;
    private static NPCBlockShop npcBlockShop;
    private static NPCEnchant npcEnchant;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @Override
    public void onEnable() {
        EnableListeners();
        EnableNPC();

//        this.getCommand("reroll").setExecutor(new CommandReroll()); // not sure how to handle this

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }

    private void EnableNPC() {
        this.library = new NPCLib(this);
        this.npcDungeon = new NPCDungeon(this);
        this.npcDungeon1 = new NPCDungeon1(this);
        this.npcDungeon2 = new NPCDungeon2(this);
        this.npcDungeon3 = new NPCDungeon3(this);
        this.npcDungeon4 = new NPCDungeon4(this);
        this.npcRaid = new NPCRaid(this);
        this.npcDropTable = new NPCDropTable(this);
        this.npcBlockShop = new NPCBlockShop(this);
        this.npcEnchant = new NPCEnchant(this);
    }

    private void EnableListeners() {
        Bukkit.getPluginManager().registerEvents(new Durabilityhandler(), this);
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathHelp(), this);
        Bukkit.getPluginManager().registerEvents(new AttackSpeedhandler(), this);
        Bukkit.getPluginManager().registerEvents(new Hungerhandler(), this);
        Bukkit.getPluginManager().registerEvents(new MobDeathhandler(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public NPCLib getNPCLib() {
        return library;
    }

    public static Chat getChat() {
        return chat;
    }

}
