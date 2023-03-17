package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.VaultBank;
import me.capitainecat0.multicommands.commands.*;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.utils.tabcompleter.*;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class Commands {
    public static void init(){
        sendConsoleMessage("&e - ACTIONBAR");
        Objects.requireNonNull(MultiCommands.instance().getCommand("actionbar")).setTabCompleter(new BossActionTitleTab());
        MultiCommands.instance().registerCommand(new ActionBar(), "actionbar");
        sendConsoleMessage("&e - AFK");
        MultiCommands.instance().registerCommand(new AFK(), "afk");
        sendConsoleMessage("&e - ADMINCHAT / AC");
        MultiCommands.instance().registerCommand(new AdminChat(), "adminchat");
        sendConsoleMessage("&e - ALERT");
        MultiCommands.instance().registerCommand(new Alert(), "alert");
        sendConsoleMessage("&e - BAN");
        Objects.requireNonNull(MultiCommands.instance().getCommand("ban")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Ban(), "ban");
        sendConsoleMessage("&e - BOSSBAR");
        Objects.requireNonNull(MultiCommands.instance().getCommand("bossbar")).setTabCompleter(new BossActionTitleTab());
        MultiCommands.instance().registerCommand(new BossBar(), "bossbar");
        sendConsoleMessage("&e - BROADCAST / BC");
        MultiCommands.instance().registerCommand(new Broadcast(), "broadcast");
        sendConsoleMessage("&e - BUILDERCHAT / BCHAT");
        MultiCommands.instance().registerCommand(new BuilderChat(), "builderchat");
        sendConsoleMessage("&e - CHAT");
        Objects.requireNonNull(MultiCommands.instance().getCommand("chat")).setTabCompleter(new ChatTab());
        MultiCommands.instance().registerCommand(new Chat(), "chat");
        sendConsoleMessage("&e - CLEARINVENTORY / CI");
        Objects.requireNonNull(MultiCommands.instance().getCommand("clearinventory")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new ClearInventory(), "clearinventory");
        sendConsoleMessage("&e - CRAFT / WORKBENCH");
        MultiCommands.instance().registerCommand(new Craft(), "craft");
        sendConsoleMessage("&e - DEVCHAT / DC");
        MultiCommands.instance().registerCommand(new DevChat(), "devchat");
        /*sendConsoleMessage("&e - ECO / ECONOMY");
        if(!MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
            MultiCommands.instance().registerCommand(new LocalEconomy(), "economy");
            Objects.requireNonNull(MultiCommands.instance().getCommand("economy")).setTabCompleter(new EconomyTab());
        }else{
            sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
            MultiCommands.instance().registerCommand(new VaultEconomy(), "economy");
            Objects.requireNonNull(MultiCommands.instance().getCommand("economy")).setTabCompleter(new EconomyTab());
            //Objects.requireNonNull(MultiCommands.instance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
        }*/
        sendConsoleMessage("&e - ENDERCHEST / EC");
        Objects.requireNonNull(MultiCommands.instance().getCommand("enderchest")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new EnderChest(), "enderchest");
        sendConsoleMessage("&e - FEED");
        Objects.requireNonNull(MultiCommands.instance().getCommand("feed")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Feed(), "feed");
        sendConsoleMessage("&e - FLY");
        Objects.requireNonNull(MultiCommands.instance().getCommand("fly")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Fly(), "fly");
        sendConsoleMessage("&e - FREEZE");
        Objects.requireNonNull(MultiCommands.instance().getCommand("freeze")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Freeze(), "freeze");
        sendConsoleMessage("&e - FURNACE");
        MultiCommands.instance().registerCommand(new Furnace(), "furnace");
        sendConsoleMessage("&e - GAMEMODE / GM");
        Objects.requireNonNull(MultiCommands.instance().getCommand("gamemode")).setTabCompleter(new GamemodeTab());
        MultiCommands.instance().registerCommand(new Gamemode(), "gamemode");
        sendConsoleMessage("&e - GOD");
        Objects.requireNonNull(MultiCommands.instance().getCommand("god")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new God(), "god");
        sendConsoleMessage("&e - HEAL");
        Objects.requireNonNull(MultiCommands.instance().getCommand("heal")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Heal(), "heal");
        sendConsoleMessage("&e - HELPOP");
        MultiCommands.instance().registerCommand(new Helpop(), "helpop");
        sendConsoleMessage("&e - INVSEE");
        Objects.requireNonNull(MultiCommands.instance().getCommand("invsee")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Invsee(), "invsee");
        sendConsoleMessage("&e - KICK");
        Objects.requireNonNull(MultiCommands.instance().getCommand("kick")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Kick(), "kick");
        sendConsoleMessage("&e - LIST");
        MultiCommands.instance().registerCommand(new List(), "list");
        sendConsoleMessage("&e - MODOCHAT / MCHAT");
        MultiCommands.instance().registerCommand(new ModoChat(), "modochat");
        sendConsoleMessage("&e - MSG");
        Objects.requireNonNull(MultiCommands.instance().getCommand("msg")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new PrivateMessager(), "msg");
        sendConsoleMessage("&e - MULTICOMMANDS / MC");
        MultiCommands.instance().registerCommand(new MultiHelp(), "multihelp");
        sendConsoleMessage("&e - MULTIINFOS / MI");
        MultiCommands.instance().registerCommand(new MultiInfos(), "multiinfos");
        sendConsoleMessage("&e - NICK");
        MultiCommands.instance().registerCommand(new Nick(), "nick");
        sendConsoleMessage("&e - PLAYERPINGER / PING");
        Objects.requireNonNull(MultiCommands.instance().getCommand("playerping")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new PlayerPinger(), "playerping");
        sendConsoleMessage("&e - MULTIRELOAD / MRELOAD");
        MultiCommands.instance().registerCommand(new MultiReload(), "multireload");
        sendConsoleMessage("&e - RANDOMTP / RTP");
        Objects.requireNonNull(MultiCommands.instance().getCommand("randomtp")).setTabCompleter(new RandomTPTab());
        MultiCommands.instance().registerCommand(new RandomTP(), "randomtp");
        sendConsoleMessage("&e - SERVERINFO");
        MultiCommands.instance().registerCommand(new ServerInfo(), "serverinfo");
        sendConsoleMessage("&e - SETSPAWN");
        MultiCommands.instance().registerCommand(new SetSpawn(), "setspawn");
        sendConsoleMessage("&e - SPAWN");
        MultiCommands.instance().registerCommand(new Spawn(), "spawn");
        sendConsoleMessage("&e - STAFFCHAT / SC");
        MultiCommands.instance().registerCommand(new StaffChat(), "staffchat");
        sendConsoleMessage("&e - TELEPORT / TP");
        Objects.requireNonNull(MultiCommands.instance().getCommand("teleport")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new TP(), "teleport");
        sendConsoleMessage("&e - TITLE");
        Objects.requireNonNull(MultiCommands.instance().getCommand("title")).setTabCompleter(new BossActionTitleTab());
        MultiCommands.instance().registerCommand(new Title(), "title");
        sendConsoleMessage("&e - VANISH / V");
        Objects.requireNonNull(MultiCommands.instance().getCommand("vanish")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Vanish(), "vanish");
        sendConsoleMessage("&e - WHOIS");
        Objects.requireNonNull(MultiCommands.instance().getCommand("whois")).setTabCompleter(new BasicTab());
        MultiCommands.instance().registerCommand(new Whois(), "whois");
        /*if(MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&aHooking to Vault is Done!");
        }*/

    }
}
