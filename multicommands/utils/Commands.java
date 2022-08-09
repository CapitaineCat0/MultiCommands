package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.*;

public class Commands {
    public static void init(){

        MultiCommands.instance().injectCommand("afk", new AFK());
        MultiCommands.instance().injectCommand("alert", new Alert());
        MultiCommands.instance().injectCommand("bc", new Broadcast());
        MultiCommands.instance().injectCommand("broadcast", new Broadcast());
        MultiCommands.instance().injectCommand("ci", new ClearInventory());
        MultiCommands.instance().injectCommand("clearinventory", new ClearInventory());
        MultiCommands.instance().injectCommand("craft", new Craft());
        MultiCommands.instance().injectCommand("workbench", new Craft());
        //MultiCommands.instance().registerCommand(new DelWarp(), "delwarp");
        //MultiCommands.instance().registerCommand(new Enchanting(), "enchanting");
        MultiCommands.instance().injectCommand("ec", new EnderChest());
        MultiCommands.instance().injectCommand("enderchest", new EnderChest());
        MultiCommands.instance().injectCommand("feed", new Feed());
        MultiCommands.instance().injectCommand("fly", new Fly());
        MultiCommands.instance().injectCommand("freeze", new Freeze());
        MultiCommands.instance().injectCommand("gm", new Gamemode());
        MultiCommands.instance().injectCommand("gamemode", new Gamemode());
        MultiCommands.instance().injectCommand("god", new God());
        MultiCommands.instance().injectCommand("heal", new Heal());
        MultiCommands.instance().injectCommand("helpop", new Helpop());
        //MultiCommands.instance().registerCommand(new Help(), "help");
        MultiCommands.instance().injectCommand("invsee", new Invsee());
        MultiCommands.instance().injectCommand("list", new List());
        MultiCommands.instance().injectCommand("mc", new MultiHelp());
        MultiCommands.instance().injectCommand("mhelp", new MultiHelp());
        MultiCommands.instance().injectCommand("multih", new MultiHelp());
        MultiCommands.instance().injectCommand("multihelp", new MultiHelp());
        MultiCommands.instance().injectCommand("mi", new MultiInfos());
        MultiCommands.instance().injectCommand("multii", new MultiInfos());
        MultiCommands.instance().injectCommand("multiinfos", new MultiInfos());
        MultiCommands.instance().injectCommand("nick", new Nick());
        MultiCommands.instance().injectCommand("ping", new PlayerPinger());
        MultiCommands.instance().injectCommand("playerpinger", new PlayerPinger());
        //MultiCommands.instance().registerCommand(new PrivateMessages(), "msg");
        //MultiCommands.instance().registerCommand(new SetWarp(), "setwarp");
        MultiCommands.instance().injectCommand("mreload", new Reload());
        MultiCommands.instance().injectCommand("multireload", new Reload());
        MultiCommands.instance().injectCommand("serverinfo", new ServerInfo());
        MultiCommands.instance().injectCommand("tp", new TP());
        MultiCommands.instance().injectCommand("teleport", new TP());
        MultiCommands.instance().injectCommand("togglehelp", new ToggleHelpBook());
        MultiCommands.instance().injectCommand("togglehelpbook", new ToggleHelpBook());
        MultiCommands.instance().injectCommand("v", new Vanish());
        MultiCommands.instance().injectCommand("vanish", new Vanish());
        //MultiCommands.instance().registerCommand(new Warp(), "warp");
        MultiCommands.instance().injectCommand("whois", new Whois());

    }
}
