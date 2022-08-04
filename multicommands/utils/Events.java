package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.Plugins;
import me.capitainecat0.multicommands.events.*;
import org.bukkit.event.Listener;

public class Events implements Listener{
    public static void init(){
        MultiCommands.instance().registerEvent(new FreezeEvents());
        MultiCommands.instance().registerEvent(new Plugins());
        MultiCommands.instance().registerEvent(new Join());
        MultiCommands.instance().registerEvent(new Leave());
        MultiCommands.instance().registerEvent(new Death());
        MultiCommands.instance().registerEvent(new Chat());
        MultiCommands.instance().registerEvent(new AFKEvents());
        MultiCommands.instance().registerEvent(new Fly());
    }
}
