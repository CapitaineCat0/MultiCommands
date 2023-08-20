package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.commands.Plugins;
import me.capitainecat0.multicommands.events.*;
import org.bukkit.event.Listener;

import static me.capitainecat0.multicommands.MultiCommands.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Events implements Listener{
    public static void init(){
        if(getInstance().getConfig().getBoolean("console-setup")){
            sendConsoleMessage(" ");
            sendConsoleMessage("&e - AFK-EVENT");
            registerEvent(new AFKEvents());
            sendConsoleMessage("&e - ADMINCHAT-EVENT");
            registerEvent(new AdminChat());
            sendConsoleMessage("&e - BLOCK-REGEN");
            registerEvent(new BlockRegen());
            sendConsoleMessage("&e - BUILDERCHAT-EVENT");
            registerEvent(new BuilderChat());
            sendConsoleMessage("&e - CHAT-EVENT");
            registerEvent(new Chat());
            sendConsoleMessage("&e - DEATH-EVENT");
            registerEvent(new Death());
            sendConsoleMessage("&e - DEVCHAT-EVENT");
            registerEvent(new DevChat());
            sendConsoleMessage("&e - FREEZE-EVENT");
            registerEvent(new FreezeEvents());
            sendConsoleMessage("&e - FLY-EVENT");
            registerEvent(new Fly());
            sendConsoleMessage("&e - GUI-EVENTS");
            registerEvent(new GUIEvents());
            sendConsoleMessage("&e - JOIN-EVENT");
            sendConsoleMessage("&e - JOIN-EVENT &c(inactif)");
            registerEvent(new Join());
            sendConsoleMessage("&e - LEAVE-EVENT");
            registerEvent(new Leave());
            sendConsoleMessage("&e - MODOCHAT-EVENT");
            registerEvent(new ModoChat());
            sendConsoleMessage("&e - /PLUGINS-EVENT");
            registerEvent(new Plugins());
            sendConsoleMessage("&e - JOIN / RESPAWN-EVENT");
            registerEvent(new SpawnEvent());
            sendConsoleMessage("&e - STAFFCHAT-EVENT");
        }else{
            registerEvent(new AFKEvents());
            registerEvent(new AdminChat());
            registerEvent(new BlockRegen());
            registerEvent(new BuilderChat());
            registerEvent(new Chat());
            registerEvent(new Death());
            registerEvent(new DevChat());
            registerEvent(new FreezeEvents());
            registerEvent(new Fly());
            registerEvent(new GUIEvents());
            registerEvent(new Join());
            registerEvent(new Leave());
            registerEvent(new ModoChat());
            registerEvent(new Plugins());
            registerEvent(new SpawnEvent());
            registerEvent(new StaffChat());
        }


    }
}
