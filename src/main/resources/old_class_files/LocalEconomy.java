package old_class_files;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class LocalEconomy implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(args.length < 1){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), ECONOMY_BALANCE.getMessage().replace("{0}", BalanceData.getBalance((Player) sender)+"").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                return false;
            } else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if(target != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), ECONOMY_BALANCE_OTHER.getMessage().replace("{0}", target.getName()).replace("{1}", BalanceData.getBalance(target)+"").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    return false;
                }
            }
            if(sender.hasPermission(ECONOMY_PERM_ADD.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("add")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" add", CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "add <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())+Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), ECONOMY_DEPOSIT.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            if(sender.hasPermission(ECONOMY_PERM_REMOVE.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("remove")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" remove", CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "remove <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())-Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), ECONOMY_WITHDRAW.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            if(sender.hasPermission(ECONOMY_PERM_RESET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" reset", CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "reset <player>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.resetBalance(target.getPlayer());
                            getMsgSendConfig(sender, command.getName(), ECONOMY_RESET.getMessage().replace("{0}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }

            if(sender.hasPermission(ECONOMY_PERM_SET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("set")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" set", CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "set <player> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            BalanceData.setBalance(target.getPlayer(), Integer.parseInt(args[2]));
                            getMsgSendConfig(sender, command.getName(), ECONOMY_SET.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(args.length < 1){
                sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<add | remove | reset | set> <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                return true;
            }else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if(target != null){
                    sendConsoleMessage(ECONOMY_BALANCE_OTHER.getMessage().replace("{0}", target.getName()).replace("{1}", BalanceData.getBalance(target)+""));
                    return false;
                }
            }
                if(args[0].equalsIgnoreCase("add")){
                    if(args.length <= 2){
                        sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "add <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())+Integer.parseInt(args[2]));
                            sendConsoleMessage(ECONOMY_DEPOSIT.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("remove")){
                    if(args.length <= 2){
                        sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "remove <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), BalanceData.getBalance(target.getPlayer())-Integer.parseInt(args[2]));
                            sendConsoleMessage(ECONOMY_WITHDRAW.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "reset <player>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.resetBalance(target.getPlayer());
                            sendConsoleMessage(ECONOMY_RESET.getMessage().replace("{0}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
                if(args[0].equalsIgnoreCase("set")){
                    if(args.length <= 2){
                        sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "set <player> <value>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            BalanceData.setBalance(target.getPlayer(), Integer.parseInt(args[2]));
                            sendConsoleMessage(ECONOMY_SET.getMessage().replace("{0}", target.getName()).replace("{1}", args[2]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }
        }
        return true;
    }
}
