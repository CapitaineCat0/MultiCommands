package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.TELEPORT_PERM;

public class TP implements CommandExecutor {

    /**
     * La commande &quot;/teleport&quot; requiert la permission &quot;multicommands.teleport&quot; pour fonctionner.
     * <br>Cette commande permet de se téléporter.
     * <br>*
     * <br>Si l'argument "0" correspond à un joueur, cela vous téléportera à lui.
     * <br>Si l'argument "0" et l'argument "1" correspondent à des joueurs, sela téléportera le joueur1 vers le joueur2.
     * <br>Si les arguments "0" à "2" correspondent à des coordonées valides, cela vous téléportera à ces coordonées.
     * <br>Si l'argument "0" correspond à un joueur et les arguments "1" à "3" correspondent à des coordonées valides,
     * <br>Le joueur sera téléporté vers ces coordonées.
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        try{
            if(!sender.hasPermission(TELEPORT_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            } else{
                if(sender instanceof Player){
                    if(args.length == 0){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<coordinates>"));
                        return true;
                    }else if(args.length <= 4){
                        if(args.length == 1){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                ((Player) sender).teleport(target.getLocation());
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), TELEPORT_SELF_TO_PLAYER.getMessage().replace("{0", target.getName()));
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                            }
                        }
                        if(args.length == 2){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            Player target2 = Bukkit.getPlayerExact(args[1]);
                            assert target != null;
                            if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(Objects.requireNonNull(target2).getName())){
                                target.teleport(target2.getLocation());
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), TELEPORT_OTHER_TO_OTHER.getMessage().replace("{0}", target2.getName()));
                                getMsgSendConfig(sender, command.getName(), TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("{0}", target.getName()).replace("{1}", target2.getName()));
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), TELEPORT_ERROR.getMessage());
                            }
                        }
                        if(args.length == 3){
                            try{
                                int x = Integer.parseInt(args[0].replace('~', (char) ((Player) sender).getLocation().getX()));
                                int y = Integer.parseInt(args[1].replace('~', (char) ((Player) sender).getLocation().getY()));
                                int z = Integer.parseInt(args[2].replace('~', (char) ((Player) sender).getLocation().getZ()));
                                ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), TELEPORT_SELF_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                            }catch(NumberFormatException e){
                                sendCommandExceptionMessage(e, command.getName()+" coordinates");
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), TELEPORT_INVALID_COORDINATES.getMessage());
                            }
                        }
                        if(args.length == 4){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                try{
                                    int x = Integer.parseInt(args[1].replace('~', (char) target.getLocation().getX()));
                                    int y = Integer.parseInt(args[2].replace('~', (char) target.getLocation().getY()));
                                    int z = Integer.parseInt(args[3].replace('~', (char) target.getLocation().getZ()));
                                    target.teleport(new Location(target.getWorld(), x, y, z));
                                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(target, command.getName(), TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                                    getMsgSendConfig(sender, command.getName(), TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("{0}", target.getName()).replace("{1}", "X "+x+" Y "+y+" Z "+z));
                                }catch(NumberFormatException e){
                                    sendCommandExceptionMessage(e, command.getName()+" coordinates");
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                                }
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                            }
                        }
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    if(args.length <= 1){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                    }
                    if(args.length == 2){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        assert target != null;
                        if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(Objects.requireNonNull(target2).getName())){
                            target.teleport(target2.getLocation());
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(target, command.getName(), TELEPORT_OTHER_TO_OTHER.getMessage().replace("{0}", target2.getName()));
                            sendConsoleMessage(TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("{0}", target.getName()).replace("{1}", target2.getName()));
                        }else{
                            sendConsoleMessage(TELEPORT_ERROR.getMessage());
                        }
                    }
                    if(args.length == 3){
                        sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage());
                    }
                    if(args.length == 4){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            try{
                                int x = Integer.parseInt(args[1].replace('~', (char) target.getLocation().getX()));
                                int y = Integer.parseInt(args[2].replace('~', (char) target.getLocation().getY()));
                                int z = Integer.parseInt(args[3].replace('~', (char) target.getLocation().getZ()));
                                target.teleport(new Location(target.getWorld(), x, y, z));
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                                sendConsoleMessage(TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("{0}", target.getName()).replace("{1}", "X "+x+" Y "+y+" Z "+z));
                            }catch(NumberFormatException e){
                                sendCommandExceptionMessage(e, command.getName()+" coordinates");
                                sendConsoleMessage(TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                            }
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
        return false;
    }
}