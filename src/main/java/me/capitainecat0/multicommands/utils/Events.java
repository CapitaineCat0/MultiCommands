package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.commands.Plugins;
import me.capitainecat0.multicommands.events.*;
import org.bukkit.event.Listener;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class Events implements Listener{
    public static void init(){
        sendConsoleMessage(" ");
        sendConsoleMessage("&e - AFK-EVENT");
        MultiCommands.instance().registerEvent(new AFKEvents());
        sendConsoleMessage("&e - ADMINCHAT-EVENT");
        MultiCommands.instance().registerEvent(new AdminChat());
        sendConsoleMessage("&e - BLOCK-REGEN");
        MultiCommands.instance().registerEvent(new BlockRegen());
        sendConsoleMessage("&e - BUILDERCHAT-EVENT");
        MultiCommands.instance().registerEvent(new BuilderChat());
        sendConsoleMessage("&e - CHAT-EVENT");
        MultiCommands.instance().registerEvent(new Chat());
        sendConsoleMessage("&e - DEATH-EVENT");
        MultiCommands.instance().registerEvent(new Death());
        sendConsoleMessage("&e - DEVCHAT-EVENT");
        MultiCommands.instance().registerEvent(new DevChat());
        sendConsoleMessage("&e - FREEZE-EVENT");
        MultiCommands.instance().registerEvent(new FreezeEvents());
        sendConsoleMessage("&e - FLY-EVENT");
        MultiCommands.instance().registerEvent(new Fly());
        sendConsoleMessage("&e - GUI-EVENTS");
        MultiCommands.instance().registerEvent(new GUIEvents());
        sendConsoleMessage("&e - JOIN-EVENT");
        MultiCommands.instance().registerEvent(new Join());
        sendConsoleMessage("&e - LEAVE-EVENT");
        MultiCommands.instance().registerEvent(new Leave());
        sendConsoleMessage("&e - MODOCHAT-EVENT");
        MultiCommands.instance().registerEvent(new ModoChat());
        sendConsoleMessage("&e - /PLUGINS-EVENT");
        MultiCommands.instance().registerEvent(new Plugins());
        sendConsoleMessage("&e - JOIN / RESPAWN-EVENT");
        MultiCommands.instance().registerEvent(new SpawnEvent());
        sendConsoleMessage("&e - STAFFCHAT-EVENT");
        MultiCommands.instance().registerEvent(new StaffChat());







    }
}
