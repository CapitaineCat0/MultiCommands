package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    private String gamemode = null;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.GAMEMODE_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_NO_PERM.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            return true;

        }
        else{
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_SUCCES.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    player.sendMessage(Messenger.GAMEMODE_ERROR.getMessage().replace("%cmd%", command.getName()));
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if (args[0].equalsIgnoreCase("0")) {
                            this.gamemode = "0 (survie)";
                        } else if (args[0].equalsIgnoreCase("survival")) {
                            this.gamemode = "survie";
                        }
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if (args[0].equalsIgnoreCase("1")) {
                            this.gamemode = "1 (créatif)";
                        } else if (args[0].equalsIgnoreCase("creative")) {
                            this.gamemode = "creatif";
                        }
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if (args[0].equalsIgnoreCase("2")) {
                            this.gamemode = "2 (aventure)";
                        } else if (args[0].equalsIgnoreCase("adventure")) {
                            this.gamemode = "aventure";
                        }
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if (args[0].equalsIgnoreCase("3")) {
                            this.gamemode = "3 (spectateur)";
                        } else if (args[0].equalsIgnoreCase("spectator")) {
                            this.gamemode = "spectateur";
                        }
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    }
                }
                if (args.length == 2) {
                    if (!sender.hasPermission(Perms.ALL_PERMS.getPermission())) {
                        sender.sendMessage(Messenger.CMD_NO_PERM_TO_OTHER.getMessage().replace("%cmd%", command.getName()));
                    } else {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target != null) {
                            if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                                if (args[0].equalsIgnoreCase("0")) {
                                    this.gamemode = "0 (survie)";
                                } else if (args[0].equalsIgnoreCase("survival")) {
                                    this.gamemode = "survie";
                                }
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                                if (args[0].equalsIgnoreCase("1")) {
                                    this.gamemode = "1 (créatif)";
                                } else if (args[0].equalsIgnoreCase("creative")) {
                                    this.gamemode = "creatif";
                                }
                                target.setGameMode(GameMode.CREATIVE);
                                target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                                if (args[0].equalsIgnoreCase("2")) {
                                    this.gamemode = "2 (aventure)";
                                } else if (args[0].equalsIgnoreCase("adventure")) {
                                    this.gamemode = "aventure";
                                }
                                target.setGameMode(GameMode.ADVENTURE);
                                target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                                if (args[0].equalsIgnoreCase("3")) {
                                    this.gamemode = "3 (spectateur)";
                                } else if (args[0].equalsIgnoreCase("spectator")) {
                                    this.gamemode = "spectateur";
                                }
                                target.setGameMode(GameMode.SPECTATOR);
                                target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }
                        } else {
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }
                }
            }else if (sender instanceof ConsoleCommandSender) {
                if(args.length <= 1){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survie)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survie";
                            }
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (créatif)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creatif";
                            }
                            target.setGameMode(GameMode.CREATIVE);
                            target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (aventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "aventure";
                            }
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectateur)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectateur";
                            }
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage(Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }
                    } else {
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                    }
                }
            }
        }
        return false;
    }
}
