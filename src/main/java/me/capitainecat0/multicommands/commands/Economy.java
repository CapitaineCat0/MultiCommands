package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Economy implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(args.length < 1){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, "", "&aVous avez &e"+ BalanceData.getBalance((Player) sender)+" &a$ en poche!");
                return false;
            }else{
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, "", "&aLa poche de &e"+target.getName()+"&a contient &d"+ BalanceData.getBalance(target)+" &a$ ");
                    return false;
                }
            }
            if(sender.hasPermission(ECONOMY_PERM_ADD.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("add")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" add", Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "add <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())+Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), "&aVous avez ajouté &d"+args[2]+"&a$ dans la poche de&e "+target.getName());
                        }else{
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }

            if(sender.hasPermission(ECONOMY_PERM_REMOVE.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("remove")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" remove", Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "remove <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())-Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), "&aVous avez retiré &d"+args[2]+"&a$ de la poche de&e "+target.getName());
                        }else{
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }

            if(sender.hasPermission(ECONOMY_PERM_RESET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" reset", Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <joueur>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.resetBalance(target.getPlayer());
                            getMsgSendConfig(sender, command.getName(), "&aVous avez réinitialisé la poche de&e "+target.getName());
                        }else{
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }

            if(sender.hasPermission(ECONOMY_PERM_SET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("set")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" set", Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "set <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), "&aVous avez mis &d"+args[2]+"&a$ dans la poche de&e "+target.getName());
                        }else{
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(args.length < 1){
                sendConsoleMessage("&cMerci de taper &e/economy <add | remove | reset | set> <joueur> <value> &c!");
                return true;
            }
                if(args[0].equalsIgnoreCase("add")){
                    if(args.length <= 2){
                        sendConsoleMessage(Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "add <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())+Integer.parseInt(args[2]));
                            sendConsoleMessage("&aVous avez ajouté &d"+args[2]+"&a$ dans la poche de&e "+target.getName());
                        }else{
                            sendConsoleMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("remove")){
                    if(args.length <= 2){
                        sendConsoleMessage(Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "remove <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())-Integer.parseInt(args[2]));
                            sendConsoleMessage("&aVous avez retiré &d"+args[2]+"&a$ de la poche de&e "+target.getName());
                        }else{
                            sendConsoleMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        sendConsoleMessage(Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <joueur>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.resetBalance(target.getPlayer());
                            sendConsoleMessage("&aVous avez réinitialisé la poche de&e"+target.getName());
                        }else{
                            sendConsoleMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }

                if(args[0].equalsIgnoreCase("set")){
                    if(args.length <= 2){
                        sendConsoleMessage(Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "set <joueur> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), Integer.parseInt(args[2]));
                            sendConsoleMessage("&aVous avez mis &d"+args[2]+"&a$ dans la poche de&e"+target.getName());
                        }else{
                            sendConsoleMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
        }
        return true;
    }
}
