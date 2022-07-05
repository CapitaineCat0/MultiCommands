package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.*;

public class Commands {
    public static void init(){
        MultiCommands.instance().registerCommand(new AFK(), "afk");
        MultiCommands.instance().registerCommand(new Alert(), "alert");
        MultiCommands.instance().registerCommand(new Broadcast(), "broadcast");
        MultiCommands.instance().registerCommand(new ClearInventory(), "clear");
        MultiCommands.instance().registerCommand(new Craft(), "craft");
        MultiCommands.instance().registerCommand(new Debug(), "debug");
        //MultiCommands.instance().registerCommand(new DelWarp(), "delwarp");
        MultiCommands.instance().registerCommand(new Enchanting(), "enchanting");
        MultiCommands.instance().registerCommand(new EnderChest(), "enderchest");
        MultiCommands.instance().registerCommand(new Feed(), "feed");
        MultiCommands.instance().registerCommand(new Fly(), "fly");
        MultiCommands.instance().registerCommand(new Freeze(), "freeze");
        MultiCommands.instance().registerCommand(new Gamemode(), "gamemode");
        MultiCommands.instance().registerCommand(new God(), "god");
        MultiCommands.instance().registerCommand(new Heal(), "heal");
        MultiCommands.instance().registerCommand(new Helpop(), "helpop");
        //MultiCommands.instance().registerCommand(new Help(), "help");
        MultiCommands.instance().registerCommand(new Invsee(), "invsee");
        MultiCommands.instance().registerCommand(new List(), "list");
        MultiCommands.instance().registerCommand(new MultiHelp(), "multihelp");
        MultiCommands.instance().registerCommand(new MultiInfos(), "multiinfos");
        MultiCommands.instance().registerCommand(new Nick(), "nick");
        MultiCommands.instance().registerCommand(new PlayerPinger(), "ping");
        //MultiCommands.instance().registerCommand(new PrivateMessages(), "msg");
        //MultiCommands.instance().registerCommand(new SetWarp(), "setwarp");
        MultiCommands.instance().registerCommand(new ServerInfo(), "serverinfo");
        MultiCommands.instance().registerCommand(new TP(), "teleport");
        MultiCommands.instance().registerCommand(new ToggleHelpBook(), "togglehelp");
        MultiCommands.instance().registerCommand(new Vanish(), "vanish");
        MultiCommands.instance().registerCommand(new Warp(), "warp");
        MultiCommands.instance().registerCommand(new Whois(), "whois");

    }
}
