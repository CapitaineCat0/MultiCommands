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
            getInstance().registerCommand(new ActionBarCMD(), "actionbar", new BossActionTitleTab());
            sendConsoleMessage("&e - AFK");
            getInstance().registerCommand(new AFKCMD(), "afk");
            sendConsoleMessage("&e - ADMINCHAT / AC");
            getInstance().registerCommand(new AdminChatCMD(), "adminchat");
            sendConsoleMessage("&e - ALERT");
            getInstance().registerCommand(new AlertCMD(), "alert");
            sendConsoleMessage("&e - ANVIL");
            getInstance().registerCommand(new AnvilCMD(), "anvil");
            sendConsoleMessage("&e - BAN");
            getInstance().registerCommand(new BanCMD(), "ban", new BasicTab());
            sendConsoleMessage("&e - BAN-IP");
            getInstance().registerCommand(new BanIPCMD(), "banip", new BasicTab());
            sendConsoleMessage("&e - BOSSBAR");
            getInstance().registerCommand(new BossBarCMD(), "bossbar", new BossActionTitleTab());
            sendConsoleMessage("&e - BROADCAST / BC");
            getInstance().registerCommand(new BroadcastCMD(), "broadcast");
            sendConsoleMessage("&e - BUILDERCHAT / BCHAT");
            getInstance().registerCommand(new BuilderChatCMD(), "builderchat");
            sendConsoleMessage("&e - CHAT");
            getInstance().registerCommand(new ChatCMD(), "chat", new ChatTab());
            sendConsoleMessage("&e - COMPASS");
            getInstance().registerCommand(new CompassCMD(), "compass");
            sendConsoleMessage("&e - CLEARINVENTORY / CI");
            getInstance().registerCommand(new ClearInventoryCMD(), "clearinventory", new BasicTab());
            sendConsoleMessage("&e - CRAFT / WORKBENCH");
            getInstance().registerCommand(new CraftCMD(), "craft");
            sendConsoleMessage("&e - DEVCHAT / DC");
            getInstance().registerCommand(new DevChatCMD(), "devchat");
            /*sendConsoleMessage("&e - ECO / ECONOMY");
            if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                MultiCommands.getInstance().registerCommand(new LocalEconomy(), "economy");
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                MultiCommands.getInstance().registerCommand(new NewVaultEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            sendConsoleMessage("&e - ENDERCHEST / EC");
            getInstance().registerCommand(new EnderChestCMD(), "enderchest", new BasicTab());
            sendConsoleMessage("&e - FEED");
            getInstance().registerCommand(new FeedCMD(), "feed", new BasicTab());
            sendConsoleMessage("&e - FLY");
            getInstance().registerCommand(new FlyCMD(), "fly", new BasicTab());
            sendConsoleMessage("&e - FREEZE");
            getInstance().registerCommand(new FreezeCMD(), "freeze", new BasicTab());
            sendConsoleMessage("&e - FURNACE");
            getInstance().registerCommand(new FurnaceCMD(), "furnace");
            sendConsoleMessage("&e - GAMEMODE / GM");
            getInstance().registerCommand(new GamemodeCMD(), "gamemode", new GamemodeTab());
            sendConsoleMessage("&e - GOD");
            getInstance().registerCommand(new GodCMD(), "god", new BasicTab());
            sendConsoleMessage("&e - HEAL");
            getInstance().registerCommand(new HealCMD(), "heal", new BasicTab());
            sendConsoleMessage("&e - HELPOP");
            getInstance().registerCommand(new HelpopCMD(), "helpop");
            sendConsoleMessage("&e - INVSEE");
            getInstance().registerCommand(new InvseeCMD(), "invsee", new BasicTab());
            sendConsoleMessage("&e - KICK");
            getInstance().registerCommand(new KickCMD(), "kick", new BasicTab());
            sendConsoleMessage("&e - KICKALL");
            getInstance().registerCommand(new KickAllCMD(), "kickall");
            sendConsoleMessage("&e - KILL");
            getInstance().registerCommand(new KillCMD(), "kill", new BasicTab());
            sendConsoleMessage("&e - LEVEL");
            getInstance().registerCommand(new LevelCMD(), "level", new LevelTab());
            sendConsoleMessage("&e - LIST");
            getInstance().registerCommand(new ListCMD(), "list");
            sendConsoleMessage("&e - MODOCHAT / MCHAT");
            getInstance().registerCommand(new ModoChatCMD(), "modochat");
            sendConsoleMessage("&e - MUTE / M");
            getInstance().registerCommand(new MuteCMD(), "mute", new BasicTab());
            sendConsoleMessage("&e - MULTICOMMANDS / MC");
            getInstance().registerCommand(new MultiHelpCMD(), "multihelp");
            sendConsoleMessage("&e - MULTIINFOS / MI");
            getInstance().registerCommand(new MultiInfosCMD(), "multiinfos");
            sendConsoleMessage("&e - NICK");
            getInstance().registerCommand(new NickCMD(), "nick");
            sendConsoleMessage("&e - PLAYERPINGER / PING");
            getInstance().registerCommand(new PlayerPingerCMD(), "playerping", new BasicTab());
            sendConsoleMessage("&e - REPORT");
            getInstance().registerCommand(new ReportCMD(), "report", new BasicTab());
            sendConsoleMessage("&e - MULTIRELOAD / MRELOAD");
            getInstance().registerCommand(new MultiReloadCMD(), "multireload");
            sendConsoleMessage("&e - RANDOMTP / RTP");
            getInstance().registerCommand(new RandomTPCMD(), "randomtp", new RandomTPTab());
            sendConsoleMessage("&e - SERVERINFO");
            getInstance().registerCommand(new ServerInfoCMD(), "serverinfo");
            sendConsoleMessage("&e - SETSPAWN");
            getInstance().registerCommand(new SetSpawnCMD(), "setspawn");
            sendConsoleMessage("&e - SPAWN");
            getInstance().registerCommand(new SpawnCMD(), "spawn");
            sendConsoleMessage("&e - STAFFCHAT / SC");
            getInstance().registerCommand(new StaffChatCMD(), "staffchat");
            sendConsoleMessage("&e - TELEPORT / TP");
            getInstance().registerCommand(new TeleportCMD(), "teleport", new BasicTab());
            sendConsoleMessage("&e - TPA");
            getInstance().registerCommand(new TeleportACMD(), "tpa", new BasicTab());
            sendConsoleMessage("&e - TITLE");
            getInstance().registerCommand(new TitleCMD(), "title", new BossActionTitleTab());
            sendConsoleMessage("&e - TOP");
            getInstance().registerCommand(new TopCMD(), "top");
            sendConsoleMessage("&e - UNBAN");
            getInstance().registerCommand(new UnBanCMD(), "unban");
            sendConsoleMessage("&e - UNBANIP");
            getInstance().registerCommand(new UnBanIPCMD(), "unbanip");
            sendConsoleMessage("&e - VANISH / V");
            getInstance().registerCommand(new VanishCMD(), "vanish", new BasicTab());
            sendConsoleMessage("&e - WHOIS");
            getInstance().registerCommand(new WhoisCMD(), "whois", new BasicTab());
        }
        else{
            getInstance().registerCommand(new ActionBarCMD(), "actionbar", new BossActionTitleTab());
            getInstance().registerCommand(new AFKCMD(), "afk");
            getInstance().registerCommand(new AdminChatCMD(), "adminchat");
            getInstance().registerCommand(new AlertCMD(), "alert");
            getInstance().registerCommand(new AnvilCMD(), "anvil");
            getInstance().registerCommand(new BanCMD(), "ban", new BasicTab());
            getInstance().registerCommand(new BanIPCMD(), "banip", new BasicTab());
            getInstance().registerCommand(new BossBarCMD(), "bossbar", new BossActionTitleTab());
            getInstance().registerCommand(new BroadcastCMD(), "broadcast");
            getInstance().registerCommand(new BuilderChatCMD(), "builderchat");
            getInstance().registerCommand(new ChatCMD(), "chat", new ChatTab());
            getInstance().registerCommand(new CompassCMD(), "compass");
            getInstance().registerCommand(new ClearInventoryCMD(), "clearinventory", new BasicTab());
            getInstance().registerCommand(new CraftCMD(), "craft");
            getInstance().registerCommand(new DevChatCMD(), "devchat");
            /*if(!MultiCommands.getInstance().setupEconomy()){
                sendConsoleMessage("&e - &cEconomy system doesn't hook to VaultAPI! Hooking to local Economy.");
                getInstance().registerCommand(new LocalEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("economy")).setTabCompleter(new EconomyTab());
            }else{
                sendConsoleMessage("&e - &aVaultAPI found! Hooking commands ...");
                getInstance().registerCommand(new NewVaultEconomy(), "economy", new EconomyTab());
                Objects.requireNonNull(MultiCommands.getInstance().getCommand("bank")).setTabCompleter(new EconomyBankTab());
            }*/
            getInstance().registerCommand(new EnderChestCMD(), "enderchest", new BasicTab());
            getInstance().registerCommand(new FeedCMD(), "feed", new BasicTab());
            getInstance().registerCommand(new FlyCMD(), "fly", new BasicTab());
            getInstance().registerCommand(new FreezeCMD(), "freeze", new BasicTab());
            getInstance().registerCommand(new FurnaceCMD(), "furnace");
            getInstance().registerCommand(new GamemodeCMD(), "gamemode", new GamemodeTab());
            getInstance().registerCommand(new GodCMD(), "god", new BasicTab());
            getInstance().registerCommand(new HealCMD(), "heal", new BasicTab());
            getInstance().registerCommand(new HelpopCMD(), "helpop");
            getInstance().registerCommand(new InvseeCMD(), "invsee", new BasicTab());
            getInstance().registerCommand(new KickCMD(), "kick", new BasicTab());
            getInstance().registerCommand(new KickAllCMD(), "kickall");
            getInstance().registerCommand(new KillCMD(), "kill", new BasicTab());
            getInstance().registerCommand(new LevelCMD(), "level", new LevelTab());
            getInstance().registerCommand(new ListCMD(), "list");
            getInstance().registerCommand(new ModoChatCMD(), "modochat");
            getInstance().registerCommand(new MuteCMD(), "mute", new BasicTab());
            getInstance().registerCommand(new MultiHelpCMD(), "multihelp");
            getInstance().registerCommand(new MultiInfosCMD(), "multiinfos");
            getInstance().registerCommand(new NickCMD(), "nick");
            getInstance().registerCommand(new PlayerPingerCMD(), "playerping", new BasicTab());
            getInstance().registerCommand(new ReportCMD(), "report", new BasicTab());
            getInstance().registerCommand(new MultiReloadCMD(), "multireload");
            getInstance().registerCommand(new RandomTPCMD(), "randomtp", new RandomTPTab());
            getInstance().registerCommand(new ServerInfoCMD(), "serverinfo");
            getInstance().registerCommand(new SetSpawnCMD(), "setspawn");
            getInstance().registerCommand(new SpawnCMD(), "spawn");
            getInstance().registerCommand(new StaffChatCMD(), "staffchat");
            getInstance().registerCommand(new TeleportCMD(), "teleport", new BasicTab());
            getInstance().registerCommand(new TeleportACMD(), "tpa", new BasicTab());
            getInstance().registerCommand(new TitleCMD(), "title", new BossActionTitleTab());
            getInstance().registerCommand(new TopCMD(), "top");
            getInstance().registerCommand(new UnBanCMD(), "unban");
            getInstance().registerCommand(new UnBanIPCMD(), "unbanip");
            getInstance().registerCommand(new VanishCMD(), "vanish", new BasicTab());
            getInstance().registerCommand(new WhoisCMD(), "whois", new BasicTab());
        }

        /*if(MultiCommands.getInstance().setupEconomy()){
            sendConsoleMessage("&aHooking to Vault is Done!");
        }*/

    }
}
