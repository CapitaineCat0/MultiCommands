package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.commands.*;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.utils.tabcompleter.*;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Commands {
    public static void init(){
        if(getInstance().getConfig().getBoolean("console-setup")){
            sendConsoleMessage("&e - ACTIONBAR");
            getInstance().registerCommand(new ActionBar(), "actionbar", new BossActionTitleTab());
            sendConsoleMessage("&e - AFK");
            getInstance().registerCommand(new AFK(), "afk");
            sendConsoleMessage("&e - ADMINCHAT / AC");
            getInstance().registerCommand(new AdminChat(), "adminchat");
            sendConsoleMessage("&e - ALERT");
            getInstance().registerCommand(new Alert(), "alert");
            sendConsoleMessage("&e -  ANVIL");
            getInstance().registerCommand(new Anvil(), "anvil");
            sendConsoleMessage("&e - BAN");
            getInstance().registerCommand(new Ban(), "ban", new BasicTab());
            sendConsoleMessage("&e - BAN-IP");
            getInstance().registerCommand(new BanIP(), "banip", new BasicTab());
            sendConsoleMessage("&e - BOSSBAR");
            getInstance().registerCommand(new BossBar(), "bossbar", new BossActionTitleTab());
            sendConsoleMessage("&e - BROADCAST / BC");
            getInstance().registerCommand(new Broadcast(), "broadcast");
            sendConsoleMessage("&e - BUILDERCHAT / BCHAT");
            getInstance().registerCommand(new BuilderChat(), "builderchat");
            sendConsoleMessage("&e - CHAT");
            getInstance().registerCommand(new Chat(), "chat", new ChatTab());
            sendConsoleMessage("&e - COMPASS");
            getInstance().registerCommand(new Compass(), "compass");
            sendConsoleMessage("&e - CLEARINVENTORY / CI");
            getInstance().registerCommand(new ClearInventory(), "clearinventory", new BasicTab());
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
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            sendConsoleMessage("&e - ENDERCHEST / EC");
            getInstance().registerCommand(new EnderChest(), "enderchest", new BasicTab());
            sendConsoleMessage("&e - FEED");
            getInstance().registerCommand(new Feed(), "feed", new BasicTab());
            sendConsoleMessage("&e - FLY");
            getInstance().registerCommand(new Fly(), "fly", new BasicTab());
            sendConsoleMessage("&e - FREEZE");
            getInstance().registerCommand(new Freeze(), "freeze", new BasicTab());
            sendConsoleMessage("&e - FURNACE");
            getInstance().registerCommand(new Furnace(), "furnace");
            sendConsoleMessage("&e - GAMEMODE / GM");
            getInstance().registerCommand(new Gamemode(), "gamemode", new GamemodeTab());
            sendConsoleMessage("&e - GOD");
            getInstance().registerCommand(new God(), "god", new BasicTab());
            sendConsoleMessage("&e - HEAL");
            getInstance().registerCommand(new Heal(), "heal", new BasicTab());
            sendConsoleMessage("&e - HELPOP");
            getInstance().registerCommand(new Helpop(), "helpop");
            sendConsoleMessage("&e - INVSEE");
            getInstance().registerCommand(new Invsee(), "invsee", new BasicTab());
            sendConsoleMessage("&e - KICK");
            getInstance().registerCommand(new Kick(), "kick", new BasicTab());
            sendConsoleMessage("&e - KICKALL");
            getInstance().registerCommand(new KickAll(), "kickall");
            sendConsoleMessage("&e - KILL");
            getInstance().registerCommand(new Kill(), "kill", new BasicTab());
            sendConsoleMessage("&e - LEVEL");
            getInstance().registerCommand(new Level(), "level", new LevelTab());
            sendConsoleMessage("&e - LIST");
            getInstance().registerCommand(new List(), "list");
            sendConsoleMessage("&e - MODOCHAT / MCHAT");
            getInstance().registerCommand(new ModoChat(), "modochat");
            sendConsoleMessage("&e - MSG");
            getInstance().registerCommand(new PrivateMessager(), "msg", new BasicTab());
            sendConsoleMessage("&e - MULTICOMMANDS / MC");
            getInstance().registerCommand(new MultiHelp(), "multihelp");
            sendConsoleMessage("&e - MULTIINFOS / MI");
            getInstance().registerCommand(new MultiInfos(), "multiinfos");
            sendConsoleMessage("&e - NICK");
            getInstance().registerCommand(new Nick(), "nick");
            sendConsoleMessage("&e - PLAYERPINGER / PING");
            getInstance().registerCommand(new PlayerPinger(), "playerping", new BasicTab());
            sendConsoleMessage("&e - MULTIRELOAD / MRELOAD");
            getInstance().registerCommand(new MultiReload(), "multireload");
            sendConsoleMessage("&e - RANDOMTP / RTP");
            getInstance().registerCommand(new RandomTP(), "randomtp", new RandomTPTab());
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
            getInstance().registerCommand(new TP(), "teleport", new BasicTab());
            sendConsoleMessage("&e - TITLE");
            getInstance().registerCommand(new Title(), "title", new BossActionTitleTab());
            sendConsoleMessage("&e - TOP");
            getInstance().registerCommand(new Top(), "top");
            sendConsoleMessage("&e - UNBAN");
            getInstance().registerCommand(new UnBan(), "unban");
            sendConsoleMessage("&e - VANISH / V");
            getInstance().registerCommand(new Vanish(), "vanish", new BasicTab());
            sendConsoleMessage("&e - WARP");
            getInstance().registerCommand(new Warp(), "warp");
            sendConsoleMessage("&e - WHOIS");
            getInstance().registerCommand(new Whois(), "whois", new BasicTab());
        }
        else{
            getInstance().registerCommand(new ActionBar(), "actionbar", new BossActionTitleTab());
            getInstance().registerCommand(new AFK(), "afk");
            getInstance().registerCommand(new AdminChat(), "adminchat");
            getInstance().registerCommand(new Alert(), "alert");
            getInstance().registerCommand(new Anvil(), "anvil");
            getInstance().registerCommand(new Ban(), "ban", new BasicTab());
            getInstance().registerCommand(new BanIP(), "banip", new BasicTab());
            getInstance().registerCommand(new BossBar(), "bossbar", new BossActionTitleTab());
            getInstance().registerCommand(new Broadcast(), "broadcast");
            getInstance().registerCommand(new BuilderChat(), "builderchat");
            getInstance().registerCommand(new Chat(), "chat", new ChatTab());
            getInstance().registerCommand(new Compass(), "compass");
            getInstance().registerCommand(new ClearInventory(), "clearinventory", new BasicTab());
            getInstance().registerCommand(new Craft(), "craft");
            getInstance().registerCommand(new DevChat(), "devchat");
            /*if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                getInstance().registerCommand(new LocalEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                getInstance().registerCommand(new NewVaultEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            getInstance().registerCommand(new EnderChest(), "enderchest", new BasicTab());
            getInstance().registerCommand(new Feed(), "feed", new BasicTab());
            getInstance().registerCommand(new Fly(), "fly", new BasicTab());
            getInstance().registerCommand(new Freeze(), "freeze", new BasicTab());
            getInstance().registerCommand(new Furnace(), "furnace");
            getInstance().registerCommand(new Gamemode(), "gamemode", new GamemodeTab());
            getInstance().registerCommand(new God(), "god", new BasicTab());
            getInstance().registerCommand(new Heal(), "heal", new BasicTab());
            getInstance().registerCommand(new Helpop(), "helpop");
            getInstance().registerCommand(new Invsee(), "invsee", new BasicTab());
            getInstance().registerCommand(new Kick(), "kick", new BasicTab());
            getInstance().registerCommand(new KickAll(), "kickall");
            getInstance().registerCommand(new Kill(), "kill", new BasicTab());
            getInstance().registerCommand(new Level(), "level", new LevelTab());
            getInstance().registerCommand(new List(), "list");
            getInstance().registerCommand(new ModoChat(), "modochat");
            getInstance().registerCommand(new PrivateMessager(), "msg", new BasicTab());
            getInstance().registerCommand(new MultiHelp(), "multihelp");
            getInstance().registerCommand(new MultiInfos(), "multiinfos");
            getInstance().registerCommand(new Nick(), "nick");
            getInstance().registerCommand(new PlayerPinger(), "playerping", new BasicTab());
            getInstance().registerCommand(new MultiReload(), "multireload");
            getInstance().registerCommand(new RandomTP(), "randomtp", new RandomTPTab());
            getInstance().registerCommand(new ServerInfo(), "serverinfo");
            getInstance().registerCommand(new SetSpawn(), "setspawn");
            getInstance().registerCommand(new SetWarp(), "setwarp");
            getInstance().registerCommand(new Spawn(), "spawn");
            getInstance().registerCommand(new StaffChat(), "staffchat");
            getInstance().registerCommand(new TP(), "teleport", new BasicTab());
            getInstance().registerCommand(new Title(), "title", new BossActionTitleTab());
            getInstance().registerCommand(new Top(), "top");
            getInstance().registerCommand(new UnBan(), "unban");
            getInstance().registerCommand(new Vanish(), "vanish", new BasicTab());
            getInstance().registerCommand(new Warp(), "warp");
            getInstance().registerCommand(new Whois(), "whois", new BasicTab());
        }

        /*if(MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&aHooking to Vault is Done!");
        }*/

    }
}
