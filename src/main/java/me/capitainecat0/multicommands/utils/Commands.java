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
        if(MultiCommands.getInstance().getConfig().getBoolean("console-setup")){
            sendConsoleMessage("&e - ACTIONBAR");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("actionbar")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new ActionBar(), "actionbar");
            sendConsoleMessage("&e - AFK");
            MultiCommands.getInstance().registerCommand(new AFK(), "afk");
            sendConsoleMessage("&e - ADMINCHAT / AC");
            MultiCommands.getInstance().registerCommand(new AdminChat(), "adminchat");
            sendConsoleMessage("&e - ALERT");
            MultiCommands.getInstance().registerCommand(new Alert(), "alert");
            sendConsoleMessage("&e - BAN");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("ban")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Ban(), "ban");
            sendConsoleMessage("&e - BOSSBAR");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("bossbar")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new BossBar(), "bossbar");
            sendConsoleMessage("&e - BROADCAST / BC");
            MultiCommands.getInstance().registerCommand(new Broadcast(), "broadcast");
            sendConsoleMessage("&e - BUILDERCHAT / BCHAT");
            MultiCommands.getInstance().registerCommand(new BuilderChat(), "builderchat");
            sendConsoleMessage("&e - CHAT");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("chat")).setTabCompleter(new ChatTab());
            MultiCommands.getInstance().registerCommand(new Chat(), "chat");
            sendConsoleMessage("&e - CLEARINVENTORY / CI");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("clearinventory")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new ClearInventory(), "clearinventory");
            sendConsoleMessage("&e - CRAFT / WORKBENCH");
            MultiCommands.getInstance().registerCommand(new Craft(), "craft");
            sendConsoleMessage("&e - DEVCHAT / DC");
            MultiCommands.getInstance().registerCommand(new DevChat(), "devchat");
            sendConsoleMessage("&e - ECO / ECONOMY");
            if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                MultiCommands.getInstance().registerCommand(new LocalEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }
            sendConsoleMessage("&e - ENDERCHEST / EC");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("enderchest")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new EnderChest(), "enderchest");
            sendConsoleMessage("&e - FEED");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("feed")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Feed(), "feed");
            sendConsoleMessage("&e - FLY");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("fly")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Fly(), "fly");
            sendConsoleMessage("&e - FREEZE");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("freeze")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Freeze(), "freeze");
            sendConsoleMessage("&e - FURNACE");
            MultiCommands.getInstance().registerCommand(new Furnace(), "furnace");
            sendConsoleMessage("&e - GAMEMODE / GM");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("gamemode")).setTabCompleter(new GamemodeTab());
            MultiCommands.getInstance().registerCommand(new Gamemode(), "gamemode");
            sendConsoleMessage("&e - GOD");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("god")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new God(), "god");
            sendConsoleMessage("&e - HEAL");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("heal")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Heal(), "heal");
            sendConsoleMessage("&e - HELPOP");
            MultiCommands.getInstance().registerCommand(new Helpop(), "helpop");
            sendConsoleMessage("&e - INVSEE");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("invsee")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Invsee(), "invsee");
            sendConsoleMessage("&e - KICK");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("kick")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Kick(), "kick");
            sendConsoleMessage("&e - LIST");
            MultiCommands.getInstance().registerCommand(new List(), "list");
            sendConsoleMessage("&e - MODOCHAT / MCHAT");
            MultiCommands.getInstance().registerCommand(new ModoChat(), "modochat");
            sendConsoleMessage("&e - MSG");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("msg")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new PrivateMessager(), "msg");
            sendConsoleMessage("&e - MULTICOMMANDS / MC");
            MultiCommands.getInstance().registerCommand(new MultiHelp(), "multihelp");
            sendConsoleMessage("&e - MULTIINFOS / MI");
            MultiCommands.getInstance().registerCommand(new MultiInfos(), "multiinfos");
            sendConsoleMessage("&e - NICK");
            MultiCommands.getInstance().registerCommand(new Nick(), "nick");
            sendConsoleMessage("&e - PLAYERPINGER / PING");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("playerping")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new PlayerPinger(), "playerping");
            sendConsoleMessage("&e - MULTIRELOAD / MRELOAD");
            MultiCommands.getInstance().registerCommand(new MultiReload(), "multireload");
            sendConsoleMessage("&e - RANDOMTP / RTP");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("randomtp")).setTabCompleter(new RandomTPTab());
            MultiCommands.getInstance().registerCommand(new RandomTP(), "randomtp");
            sendConsoleMessage("&e - SERVERINFO");
            MultiCommands.getInstance().registerCommand(new ServerInfo(), "serverinfo");
            sendConsoleMessage("&e - SETSPAWN");
            MultiCommands.getInstance().registerCommand(new SetSpawn(), "setspawn");
            sendConsoleMessage("&e - SETWARP");
            MultiCommands.getInstance().registerCommand(new SetWarp(), "setwarp");
            sendConsoleMessage("&e - SPAWN");
            MultiCommands.getInstance().registerCommand(new Spawn(), "spawn");
            sendConsoleMessage("&e - STAFFCHAT / SC");
            MultiCommands.getInstance().registerCommand(new StaffChat(), "staffchat");
            sendConsoleMessage("&e - TELEPORT / TP");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("teleport")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new TP(), "teleport");
            sendConsoleMessage("&e - TITLE");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("title")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new Title(), "title");
            sendConsoleMessage("&e - VANISH / V");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("vanish")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Vanish(), "vanish");
            sendConsoleMessage("&e - WARP");
            MultiCommands.getInstance().registerCommand(new Warp(), "warp");
            sendConsoleMessage("&e - WHOIS");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("whois")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Whois(), "whois");
        }
        else{
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("actionbar")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new ActionBar(), "actionbar");
            MultiCommands.getInstance().registerCommand(new AFK(), "afk");
            MultiCommands.getInstance().registerCommand(new AdminChat(), "adminchat");
            MultiCommands.getInstance().registerCommand(new Alert(), "alert");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("ban")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Ban(), "ban");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("bossbar")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new BossBar(), "bossbar");
            MultiCommands.getInstance().registerCommand(new Broadcast(), "broadcast");
            MultiCommands.getInstance().registerCommand(new BuilderChat(), "builderchat");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("chat")).setTabCompleter(new ChatTab());
            MultiCommands.getInstance().registerCommand(new Chat(), "chat");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("clearinventory")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new ClearInventory(), "clearinventory");
            MultiCommands.getInstance().registerCommand(new Craft(), "craft");
            MultiCommands.getInstance().registerCommand(new DevChat(), "devchat");
            if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                MultiCommands.getInstance().registerCommand(new LocalEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("enderchest")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new EnderChest(), "enderchest");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("feed")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Feed(), "feed");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("fly")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Fly(), "fly");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("freeze")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Freeze(), "freeze");
            MultiCommands.getInstance().registerCommand(new Furnace(), "furnace");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("gamemode")).setTabCompleter(new GamemodeTab());
            MultiCommands.getInstance().registerCommand(new Gamemode(), "gamemode");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("god")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new God(), "god");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("heal")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Heal(), "heal");
            MultiCommands.getInstance().registerCommand(new Helpop(), "helpop");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("invsee")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Invsee(), "invsee");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("kick")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Kick(), "kick");
            MultiCommands.getInstance().registerCommand(new List(), "list");
            MultiCommands.getInstance().registerCommand(new ModoChat(), "modochat");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("msg")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new PrivateMessager(), "msg");
            MultiCommands.getInstance().registerCommand(new MultiHelp(), "multihelp");
            MultiCommands.getInstance().registerCommand(new MultiInfos(), "multiinfos");
            MultiCommands.getInstance().registerCommand(new Nick(), "nick");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("playerping")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new PlayerPinger(), "playerping");
            MultiCommands.getInstance().registerCommand(new MultiReload(), "multireload");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("randomtp")).setTabCompleter(new RandomTPTab());
            MultiCommands.getInstance().registerCommand(new RandomTP(), "randomtp");
            MultiCommands.getInstance().registerCommand(new ServerInfo(), "serverinfo");
            MultiCommands.getInstance().registerCommand(new SetSpawn(), "setspawn");
            MultiCommands.getInstance().registerCommand(new SetWarp(), "setwarp");
            MultiCommands.getInstance().registerCommand(new Spawn(), "spawn");
            MultiCommands.getInstance().registerCommand(new StaffChat(), "staffchat");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("teleport")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new TP(), "teleport");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("title")).setTabCompleter(new BossActionTitleTab());
            MultiCommands.getInstance().registerCommand(new Title(), "title");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("vanish")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Vanish(), "vanish");
            Objects.requireNonNull(MultiCommands.getInstance().getCommand("whois")).setTabCompleter(new BasicTab());
            MultiCommands.getInstance().registerCommand(new Warp(), "warp");
            MultiCommands.getInstance().registerCommand(new Whois(), "whois");
        }

        /*if(MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&aHooking to Vault is Done!");
        }*/

    }
}
