package old_class_files;

import me.capitainecat0.multicommands.MultiCommands;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.MultiCommands.econ;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class NewVaultEconomy implements CommandExecutor {
    private EconomyResponse r;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(args.length < 1){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), String.format("Vous avez %s dans votre poche", econ.format(econ.getBalance(((Player) sender).getPlayer()))));
                return true;
            } else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if(target != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), String.format("La poche de "+target.getName()+" contient %s", econ.format(econ.getBalance(target))));
                }else{
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                }
            }else if(sender.hasPermission(ECONOMY_PERM_SET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                    if(args[0].equalsIgnoreCase("set")){
                        if(args.length <= 2){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName()+" set", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "set <player> <value>"));
                        }else{
                            Player target = Bukkit.getPlayerExact(args[1]);
                            double value = Double.parseDouble(args[2]);
                            if(target != null){
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                econ.withdrawPlayer(target, econ.getBalance(target.getName()));
                                econ.depositPlayer(target, value);
                                if(r.transactionSuccess()){
                                    getMsgSendConfig(sender, command.getName(), String.format("Vous avez mis %s dans la poche de "+target.getName(), econ.format(r.amount)));
                                    getMsgSendConfig(target, command.getName(), String.format("Votre poche contient maintenant %s", econ.format(r.amount)));
                                    //getMsgSendConfig(sender, command.getName(), ECONOMY_SET.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                                }else{
                                    if(soundEnabled()){
                                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    }
                                    sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
                                }
                            }else{
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                            }
                        }
                    }
                }else{
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                }
            if(args.length == 3 && args[0].equalsIgnoreCase("pay")){
                Player target = Bukkit.getPlayerExact(args[1]);
                double value = Double.parseDouble(args[2]);
                if(target != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    econ.withdrawPlayer(((Player) sender).getPlayer(), value);
                    econ.depositPlayer(target, value);
                    if(r.transactionSuccess()){
                        getMsgSendConfig(sender, command.getName(), String.format("Vous avez payé "+target.getName()+" %s", econ.format(r.amount)));
                        getMsgSendConfig(target, command.getName(), String.format("Vous avez été payé %s par "+sender.getName(), econ.format(r.amount)));
                        //getMsgSendConfig(sender, command.getName(), ECONOMY_SET.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
                    }
                    getMsgSendConfig(sender, command.getName(), ECONOMY_PAY_SENT.getMessage().replace("%value%", args[2]).replace("%player%", target.getName()));
                    getMsgSendConfig(target, command.getName(), ECONOMY_PAY_OTHER.getMessage().replace("%value%", args[2]).replace("%player%", sender.getName()));
                }else{
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                }
            }
            if(sender.hasPermission(ECONOMY_PERM_ADD.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("deposit")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" deposit", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "deposit <player> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        double value = Double.parseDouble(args[2]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            econ.depositPlayer(target, value);
                            if(r.transactionSuccess()){
                                getMsgSendConfig(sender, command.getName(), ECONOMY_DEPOSIT.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
                            }
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
            if(sender.hasPermission(ECONOMY_PERM_REMOVE.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("withdraw")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" withdraw", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "withdraw <player> <value>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        double value = Double.parseDouble(args[2]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            econ.withdrawPlayer(target, value);
                            if(r.transactionSuccess()){
                                getMsgSendConfig(sender, command.getName(), ECONOMY_WITHDRAW.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
                            }

                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
            if(sender.hasPermission(ECONOMY_PERM_RESET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" reset", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <player>"));
                    }else{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            econ.withdrawPlayer(target, econ.getBalance(target));
                            if(r.transactionSuccess()){
                                getMsgSendConfig(sender, command.getName(), ECONOMY_RESET.getMessage().replace("%player%", target.getName()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                sender.sendMessage(String.format("An error occured: %s", r.errorMessage));
                            }
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
        }
        else if(sender instanceof ConsoleCommandSender){
            if(args.length < 1){
                sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<add | remove | reset | set> <player> <value>"));
                return true;
            }else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if(target != null){
                    sendConsoleMessage(ECONOMY_BALANCE_OTHER.getMessage().replace("%player%", target.getName()).replace("%balance%", econ.format(econ.getBalance(target))));
                    return false;
                }
            }
            if(args[0].equalsIgnoreCase("add")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "add <player> <value>"));
                }else{
                    Player target = Bukkit.getPlayerExact(args[1]);
                    double value = Double.parseDouble(args[2]);
                    if(target != null){
                        econ.depositPlayer(target, value);
                        if(r.transactionSuccess()){
                            sendConsoleMessage(ECONOMY_DEPOSIT.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                        }else{
                            sendConsoleMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                    }
                }
            }
            if(args[0].equalsIgnoreCase("remove")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "remove <player> <value>"));
                }else{
                    Player target = Bukkit.getPlayerExact(args[1]);
                    double value = Double.parseDouble(args[2]);
                    if(target != null){
                        econ.withdrawPlayer(target, value);
                        if(r.transactionSuccess()){
                            sendConsoleMessage(ECONOMY_WITHDRAW.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                        }else{
                            sendConsoleMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                    }
                }
            }
            if(args[0].equalsIgnoreCase("reset")){
                if(args.length <= 1){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <player>"));
                }else{
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(target != null){
                        econ.withdrawPlayer(target, econ.getBalance(target));
                        if(r.transactionSuccess()){
                            sendConsoleMessage(ECONOMY_RESET.getMessage().replace("%player%", target.getName()));
                        }else{
                            sendConsoleMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                    }
                }
            }
            if(args[0].equalsIgnoreCase("set")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "set <player> <value>"));
                }else{
                    Player target = Bukkit.getPlayerExact(args[1]);
                    double value = Double.parseDouble(args[2]);
                    if(target != null){
                        econ.withdrawPlayer(target, econ.getBalance(target));
                        econ.depositPlayer(target, value);
                        if(r.transactionSuccess()){
                            sendConsoleMessage(ECONOMY_SET.getMessage().replace("%player%", target.getName()).replace("%balance%", args[2]));
                        }else{
                            sendConsoleMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[1]));
                    }
                }
            }
        }
        return false;
    }
}
