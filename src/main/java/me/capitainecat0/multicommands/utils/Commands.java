package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.*;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.utils.tabcompleter.*;

import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Commands {
    public static void init(){
        if(getInstance().getConfig().getBoolean("console-setup")){
            sendConsoleMessage("&e - ACTIONBAR");
            Objects.requireNonNull(getInstance().getCommand("actionbar")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new ActionBar(), "actionbar");
            sendConsoleMessage("&e - AFK");
            getInstance().registerCommand(new AFK(), "afk");
            sendConsoleMessage("&e - ADMINCHAT / AC");
            getInstance().registerCommand(new AdminChat(), "adminchat");
            sendConsoleMessage("&e - ALERT");
            getInstance().registerCommand(new Alert(), "alert");
            sendConsoleMessage("&e - BAN");
            Objects.requireNonNull(getInstance().getCommand("ban")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Ban(), "ban");
            sendConsoleMessage("&e - BOSSBAR");
            Objects.requireNonNull(getInstance().getCommand("bossbar")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new BossBar(), "bossbar");
            sendConsoleMessage("&e - BROADCAST / BC");
            getInstance().registerCommand(new Broadcast(), "broadcast");
            sendConsoleMessage("&e - BUILDERCHAT / BCHAT");
            getInstance().registerCommand(new BuilderChat(), "builderchat");
            sendConsoleMessage("&e - CHAT");
            Objects.requireNonNull(getInstance().getCommand("chat")).setTabCompleter(new ChatTab());
            getInstance().registerCommand(new Chat(), "chat");
            sendConsoleMessage("&e - CLEARINVENTORY / CI");
            Objects.requireNonNull(getInstance().getCommand("clearinventory")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new ClearInventory(), "clearinventory");
            sendConsoleMessage("&e - CRAFT / WORKBENCH");
            getInstance().registerCommand(new Craft(), "craft");
            sendConsoleMessage("&e - DEVCHAT / DC");
            getInstance().registerCommand(new DevChat(), "devchat");
            sendConsoleMessage("&e - ECO / ECONOMY");
            /*if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                MultiCommands.getInstance().registerCommand(new LocalEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            sendConsoleMessage("&e - ENDERCHEST / EC");
            Objects.requireNonNull(getInstance().getCommand("enderchest")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new EnderChest(), "enderchest");
            sendConsoleMessage("&e - FEED");
            Objects.requireNonNull(getInstance().getCommand("feed")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Feed(), "feed");
            sendConsoleMessage("&e - FLY");
            Objects.requireNonNull(getInstance().getCommand("fly")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Fly(), "fly");
            sendConsoleMessage("&e - FREEZE");
            Objects.requireNonNull(getInstance().getCommand("freeze")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Freeze(), "freeze");
            sendConsoleMessage("&e - FURNACE");
            getInstance().registerCommand(new Furnace(), "furnace");
            sendConsoleMessage("&e - GAMEMODE / GM");
            Objects.requireNonNull(getInstance().getCommand("gamemode")).setTabCompleter(new GamemodeTab());
            getInstance().registerCommand(new Gamemode(), "gamemode");
            sendConsoleMessage("&e - GOD");
            Objects.requireNonNull(getInstance().getCommand("god")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new God(), "god");
            sendConsoleMessage("&e - HEAL");
            Objects.requireNonNull(getInstance().getCommand("heal")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Heal(), "heal");
            sendConsoleMessage("&e - HELPOP");
            getInstance().registerCommand(new Helpop(), "helpop");
            sendConsoleMessage("&e - INVSEE");
            Objects.requireNonNull(getInstance().getCommand("invsee")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Invsee(), "invsee");
            sendConsoleMessage("&e - KICK");
            Objects.requireNonNull(getInstance().getCommand("kick")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Kick(), "kick");
            sendConsoleMessage("&e - LIST");
            getInstance().registerCommand(new List(), "list");
            sendConsoleMessage("&e - MODOCHAT / MCHAT");
            getInstance().registerCommand(new ModoChat(), "modochat");
            sendConsoleMessage("&e - MSG");
            Objects.requireNonNull(getInstance().getCommand("msg")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new PrivateMessager(), "msg");
            sendConsoleMessage("&e - MULTICOMMANDS / MC");
            getInstance().registerCommand(new MultiHelp(), "multihelp");
            sendConsoleMessage("&e - MULTIINFOS / MI");
            getInstance().registerCommand(new MultiInfos(), "multiinfos");
            sendConsoleMessage("&e - NICK");
            getInstance().registerCommand(new Nick(), "nick");
            sendConsoleMessage("&e - PLAYERPINGER / PING");
            Objects.requireNonNull(getInstance().getCommand("playerping")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new PlayerPinger(), "playerping");
            sendConsoleMessage("&e - MULTIRELOAD / MRELOAD");
            getInstance().registerCommand(new MultiReload(), "multireload");
            sendConsoleMessage("&e - RANDOMTP / RTP");
            Objects.requireNonNull(getInstance().getCommand("randomtp")).setTabCompleter(new RandomTPTab());
            getInstance().registerCommand(new RandomTP(), "randomtp");
            sendConsoleMessage("&e - SERVERINFO");
            getInstance().registerCommand(new ServerInfo(), "serverinfo");
            sendConsoleMessage("&e - SETSPAWN");
            getInstance().registerCommand(new SetSpawn(), "setspawn");
            sendConsoleMessage("&e - SETWARP");
            getInstance().registerCommand(new SetWarp(), "setwarp");
            sendConsoleMessage("&e - SPAWN");
            getInstance().registerCommand(new Spawn(), "spawn");
            sendConsoleMessage("&e - STAFFCHAT / SC");
            getInstance().registerCommand(new StaffChat(), "staffchat");
            sendConsoleMessage("&e - TELEPORT / TP");
            Objects.requireNonNull(getInstance().getCommand("teleport")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new TP(), "teleport");
            sendConsoleMessage("&e - TITLE");
            Objects.requireNonNull(getInstance().getCommand("title")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new Title(), "title");
            sendConsoleMessage("&e - VANISH / V");
            Objects.requireNonNull(getInstance().getCommand("vanish")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Vanish(), "vanish");
            sendConsoleMessage("&e - WARP");
            getInstance().registerCommand(new Warp(), "warp");
            sendConsoleMessage("&e - WHOIS");
            Objects.requireNonNull(getInstance().getCommand("whois")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Whois(), "whois");
        }
        else{
            Objects.requireNonNull(getInstance().getCommand("actionbar")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new ActionBar(), "actionbar");
            getInstance().registerCommand(new AFK(), "afk");
            getInstance().registerCommand(new AdminChat(), "adminchat");
            getInstance().registerCommand(new Alert(), "alert");
            Objects.requireNonNull(getInstance().getCommand("ban")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Ban(), "ban");
            Objects.requireNonNull(getInstance().getCommand("bossbar")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new BossBar(), "bossbar");
            getInstance().registerCommand(new Broadcast(), "broadcast");
            getInstance().registerCommand(new BuilderChat(), "builderchat");
            Objects.requireNonNull(getInstance().getCommand("chat")).setTabCompleter(new ChatTab());
            getInstance().registerCommand(new Chat(), "chat");
            Objects.requireNonNull(getInstance().getCommand("clearinventory")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new ClearInventory(), "clearinventory");
            getInstance().registerCommand(new Craft(), "craft");
            getInstance().registerCommand(new DevChat(), "devchat");
            /*if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                MultiCommands.getInstance().registerCommand(new LocalEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            Objects.requireNonNull(getInstance().getCommand("enderchest")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new EnderChest(), "enderchest");
            Objects.requireNonNull(getInstance().getCommand("feed")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Feed(), "feed");
            Objects.requireNonNull(getInstance().getCommand("fly")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Fly(), "fly");
            Objects.requireNonNull(getInstance().getCommand("freeze")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Freeze(), "freeze");
            getInstance().registerCommand(new Furnace(), "furnace");
            Objects.requireNonNull(getInstance().getCommand("gamemode")).setTabCompleter(new GamemodeTab());
            getInstance().registerCommand(new Gamemode(), "gamemode");
            Objects.requireNonNull(getInstance().getCommand("god")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new God(), "god");
            Objects.requireNonNull(getInstance().getCommand("heal")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Heal(), "heal");
            getInstance().registerCommand(new Helpop(), "helpop");
            Objects.requireNonNull(getInstance().getCommand("invsee")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Invsee(), "invsee");
            Objects.requireNonNull(getInstance().getCommand("kick")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Kick(), "kick");
            getInstance().registerCommand(new List(), "list");
            getInstance().registerCommand(new ModoChat(), "modochat");
            Objects.requireNonNull(getInstance().getCommand("msg")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new PrivateMessager(), "msg");
            getInstance().registerCommand(new MultiHelp(), "multihelp");
            getInstance().registerCommand(new MultiInfos(), "multiinfos");
            getInstance().registerCommand(new Nick(), "nick");
            Objects.requireNonNull(getInstance().getCommand("playerping")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new PlayerPinger(), "playerping");
            getInstance().registerCommand(new MultiReload(), "multireload");
            Objects.requireNonNull(getInstance().getCommand("randomtp")).setTabCompleter(new RandomTPTab());
            getInstance().registerCommand(new RandomTP(), "randomtp");
            getInstance().registerCommand(new ServerInfo(), "serverinfo");
            getInstance().registerCommand(new SetSpawn(), "setspawn");
            getInstance().registerCommand(new SetWarp(), "setwarp");
            getInstance().registerCommand(new Spawn(), "spawn");
            getInstance().registerCommand(new StaffChat(), "staffchat");
            Objects.requireNonNull(getInstance().getCommand("teleport")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new TP(), "teleport");
            Objects.requireNonNull(getInstance().getCommand("title")).setTabCompleter(new BossActionTitleTab());
            getInstance().registerCommand(new Title(), "title");
            Objects.requireNonNull(getInstance().getCommand("vanish")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Vanish(), "vanish");
            Objects.requireNonNull(getInstance().getCommand("whois")).setTabCompleter(new BasicTab());
            getInstance().registerCommand(new Warp(), "warp");
            getInstance().registerCommand(new Whois(), "whois");
        }

        /*if(MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&aHooking to Vault is Done!");
        }*/

    }
}
