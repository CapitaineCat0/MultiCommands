package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.chatchannels.*;
import me.capitainecat0.multicommands.commands.Plugins;
import me.capitainecat0.multicommands.events.*;
import org.bukkit.event.Listener;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class Events implements Listener{
    public static void init(){
        if(MultiCommands.getInstance().getConfig().getBoolean("console-setup")){
            sendConsoleMessage(" ");
            sendConsoleMessage("&e - AFK-EVENT");
            MultiCommands.getInstance().registerEvent(new AFKEvents());
            sendConsoleMessage("&e - ADMINCHAT-EVENT");
            MultiCommands.getInstance().registerEvent(new AdminChat());
            sendConsoleMessage("&e - BLOCK-REGEN");
            MultiCommands.getInstance().registerEvent(new BlockRegen());
            sendConsoleMessage("&e - BUILDERCHAT-EVENT");
            MultiCommands.getInstance().registerEvent(new BuilderChat());
        /*sendConsoleMessage("&e - CHAT-EVENT");
        MultiCommands.instance().registerEvent(new Chat());*/
            sendConsoleMessage("&e - DEATH-EVENT");
            MultiCommands.getInstance().registerEvent(new Death());
            sendConsoleMessage("&e - DEVCHAT-EVENT");
            MultiCommands.getInstance().registerEvent(new DevChat());
            sendConsoleMessage("&e - FREEZE-EVENT");
            MultiCommands.getInstance().registerEvent(new FreezeEvents());
            sendConsoleMessage("&e - FLY-EVENT");
            MultiCommands.getInstance().registerEvent(new Fly());
            sendConsoleMessage("&e - GUI-EVENTS");
            MultiCommands.getInstance().registerEvent(new GUIEvents());
            sendConsoleMessage("&e - JOIN-EVENT");
            //sendConsoleMessage("&e - JOIN-EVENT &c(inactif)");
            MultiCommands.getInstance().registerEvent(new Join());
            sendConsoleMessage("&e - LEAVE-EVENT");
            MultiCommands.getInstance().registerEvent(new Leave());
            sendConsoleMessage("&e - MODOCHAT-EVENT");
            MultiCommands.getInstance().registerEvent(new ModoChat());
            sendConsoleMessage("&e - /PLUGINS-EVENT");
            MultiCommands.getInstance().registerEvent(new Plugins());
            sendConsoleMessage("&e - JOIN / RESPAWN-EVENT");
            MultiCommands.getInstance().registerEvent(new SpawnEvent());
            sendConsoleMessage("&e - STAFFCHAT-EVENT");
            MultiCommands.getInstance().registerEvent(new StaffChat());
        }else{
            MultiCommands.getInstance().registerEvent(new AFKEvents());
            MultiCommands.getInstance().registerEvent(new AdminChat());
            MultiCommands.getInstance().registerEvent(new BlockRegen());
            MultiCommands.getInstance().registerEvent(new BuilderChat());
            //MultiCommands.getInstance().registerEvent(new Chat());
            MultiCommands.getInstance().registerEvent(new Death());
            MultiCommands.getInstance().registerEvent(new DevChat());
            MultiCommands.getInstance().registerEvent(new FreezeEvents());
            MultiCommands.getInstance().registerEvent(new Fly());
            MultiCommands.getInstance().registerEvent(new GUIEvents());
            MultiCommands.getInstance().registerEvent(new Join());
            MultiCommands.getInstance().registerEvent(new Leave());
            MultiCommands.getInstance().registerEvent(new ModoChat());
            MultiCommands.getInstance().registerEvent(new Plugins());
            MultiCommands.getInstance().registerEvent(new SpawnEvent());
            MultiCommands.getInstance().registerEvent(new StaffChat());
        }

    }
}
