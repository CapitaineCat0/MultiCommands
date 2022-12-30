package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class VaultBank implements CommandExecutor {
    private static Economy economy = null;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(args.length < 1){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "get <bankName>"));
            }else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                String bankName = args[1];
                if(economy.bankBalance(bankName) != null){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    economy.createBank(bankName, (OfflinePlayer) sender);
                    getMsgSendConfig(sender, command.getName(), ECONOMY_BANK_BALANCE.getMessage().replace("%bankName%", bankName).replace("%balance%", economy.bankBalance(bankName).toString()));
                }else{
                    getMsgSendConfig(sender, command.getName()+" create", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "create <bankName>"));
                }
            }
            if(sender.hasPermission(ECONOMY_PERM_BANK_CREATE.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("create")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" create", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "create <bankName>"));
                    }else{
                        String bankName = args[1];
                        if(bankName != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            economy.createBank(bankName, (OfflinePlayer) sender);
                            getMsgSendConfig(sender, command.getName(), ECONOMY_BANK_CREATED.getMessage().replace("%bankName%", bankName));
                        }else{
                            getMsgSendConfig(sender, command.getName(), "&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
            if(sender.hasPermission(ECONOMY_PERM_BANK_ADD.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("deposit")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" deposit", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "deposit <bankName> <value>"));
                    }else{
                        String bankName = args[1];
                        double value = Double.parseDouble(args[2]);
                        if(bankName != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            economy.bankDeposit(bankName, value);
                            getMsgSendConfig(sender, command.getName(), ECONOMY_BANK_DEPOSIT.getMessage().replace("%bankName%", bankName).replace("%balance%", args[2]));
                        }else{
                            getMsgSendConfig(sender, command.getName(), "&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
            if(sender.hasPermission(ECONOMY_PERM_BANK_REMOVE.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("withdraw")){
                    if(args.length <= 2){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" withdraw", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "withdraw <bankName> <value>"));
                    }else{
                        String bankName = args[1];
                        double value = Double.parseDouble(args[2]);
                        if(bankName != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            economy.bankWithdraw(bankName, value);
                            getMsgSendConfig(sender, command.getName(), ECONOMY_BANK_WITHDRAW.getMessage().replace("%bankName%", bankName).replace("%balance%", args[2]));
                        }else{
                            getMsgSendConfig(sender, command.getName(), "&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
            if(sender.hasPermission(ECONOMY_PERM_BANK_RESET.getPermission()) || sender.hasPermission(ECONOMY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                if(args[0].equalsIgnoreCase("reset")){
                    if(args.length <= 1){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName()+" reset", CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <bankName>"));
                    }else{
                        String bankName = args[1];
                        if(bankName != null){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            economy.bankWithdraw(bankName, economy.bankBalance(bankName).balance);
                            getMsgSendConfig(sender, command.getName(), ECONOMY_BANK_RESET.getMessage().replace("%bankName%", bankName));
                        }else{
                            getMsgSendConfig(sender, command.getName(), "&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                        }
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }
        }
        else if(sender instanceof ConsoleCommandSender){
            if(args.length < 1){
                sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<add | remove | reset | set> <bankName> <value>"));
                return true;
            }else if(args.length == 2 && args[0].equalsIgnoreCase("get")){
                String bankName = args[1];
                if(bankName != null){
                    sendConsoleMessage(ECONOMY_BANK_BALANCE.getMessage().replace("%bankName%", bankName).replace("%balance%", economy.bankBalance(bankName).toString()));
                    return false;
                }
            }
            if(args[0].equalsIgnoreCase("add")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "add <player> <value>"));
                }else{
                    String bankName = args[1];
                    double value = Double.parseDouble(args[2]);
                    if(bankName != null){
                        economy.bankDeposit(bankName, value);
                        sendConsoleMessage(ECONOMY_BANK_DEPOSIT.getMessage().replace("%bankName%", bankName).replace("%balance%", args[2]));
                    }else{
                        sendConsoleMessage("&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                    }
                }
            }
            if(args[0].equalsIgnoreCase("remove")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "remove <player> <value>"));
                }else{
                    String bankName = args[1];
                    double value = Double.parseDouble(args[2]);
                    if(bankName != null){
                        economy.bankWithdraw(bankName, value);
                        sendConsoleMessage(ECONOMY_BANK_WITHDRAW.getMessage().replace("%bankName%", bankName).replace("%balance%", args[2]));
                    }else{
                        sendConsoleMessage("&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                    }
                }
            }
            if(args[0].equalsIgnoreCase("reset")){
                if(args.length <= 1){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "reset <player>"));
                }else{
                    String bankName = args[1];
                    if(bankName != null){
                        economy.bankWithdraw(bankName, economy.bankBalance(bankName).balance);
                        sendConsoleMessage(ECONOMY_BANK_RESET.getMessage().replace("%bankName%", bankName));
                    }else{
                        sendConsoleMessage("&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                    }
                }
            }
            if(args[0].equalsIgnoreCase("set")){
                if(args.length <= 2){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "set <player> <value>"));
                }else{
                    String bankName = args[1];
                    double value = Double.parseDouble(args[2]);
                    if(bankName != null){
                        economy.bankWithdraw(bankName, economy.bankBalance(bankName).balance);
                        economy.bankDeposit(bankName, value);
                        sendConsoleMessage(ECONOMY_SET.getMessage().replace("%bankName%", bankName).replace("%balance%", args[2]));
                    }else{
                        sendConsoleMessage("&cNo bank with that name: &e"+args[1]+"&c exists! Create one with &e/bank create <bankName> &c!");
                    }
                }
            }
        }
        return false;
    }
}
