package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.GamemodeGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

public class Gamemode implements CommandExecutor {
    private String gamemode = null;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player player) {
                if (args.length == 0) {
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    new GamemodeGUI().open((Player) sender);
                    //getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<mode> [player]"));
                    return true;
                }else if (args.length == 1) {
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
                        if(sender.hasPermission(GAMEMODE_SURVIVAL_PERM_SELF.getPermission()) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survival)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survival";
                            }
                            player.setGameMode(GameMode.SURVIVAL);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                        if(sender.hasPermission(GAMEMODE_CREATIVE_PERM_SELF.getPermission()) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (creative)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creative";
                            }
                            player.setGameMode(GameMode.CREATIVE);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(sender.hasPermission(GAMEMODE_ADVENTURE_PERM_SELF.getPermission())
                                || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (adventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "adventure";
                            }
                            player.setGameMode(GameMode.ADVENTURE);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            return true;
                        }

                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(sender.hasPermission(GAMEMODE_SPECTATOR_PERM_SELF.getPermission())
                                || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectator)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectator";
                            }
                            player.setGameMode(GameMode.SPECTATOR);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            return true;
                        }
                    }
                }else if (args.length == 2) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    hideActiveBossBar();
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if(sender.hasPermission(GAMEMODE_SURVIVAL_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("0")) {
                                    this.gamemode = "0 (survival)";
                                } else if (args[0].equalsIgnoreCase("survival")) {
                                    this.gamemode = "survival";
                                }
                                target.setGameMode(GameMode.SURVIVAL);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                return true;
                            }
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if(sender.hasPermission(GAMEMODE_CREATIVE_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("1")) {
                                    this.gamemode = "1 (creative)";
                                } else if (args[0].equalsIgnoreCase("creative")) {
                                    this.gamemode = "creative";
                                }
                                target.setGameMode(GameMode.CREATIVE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                                return true;
                            }
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if(sender.hasPermission(GAMEMODE_ADVENTURE_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("2")) {
                                    this.gamemode = "2 (adventure)";
                                } else if (args[0].equalsIgnoreCase("adventure")) {
                                    this.gamemode = "adventure";
                                }
                                target.setGameMode(GameMode.ADVENTURE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                return true;
                            }
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if(sender.hasPermission(GAMEMODE_SPECTATOR_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("3")) {
                                    this.gamemode = "3 (spectator)";
                                } else if (args[0].equalsIgnoreCase("spectator")) {
                                    this.gamemode = "spectator";
                                }
                                target.setGameMode(GameMode.SPECTATOR);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                return true;
                            }
                        }
                    } else {
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }else if (args.length == 2 && args[1].equalsIgnoreCase("all")) {
                    hideActiveBossBar();
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if(sender.hasPermission(GAMEMODE_SURVIVAL_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("0")) {
                                    this.gamemode = "0 (survival)";
                                } else if (args[0].equalsIgnoreCase("survival")) {
                                    this.gamemode = "survival";
                                }
                                target.setGameMode(GameMode.SURVIVAL);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                                return true;
                            }
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if(sender.hasPermission(GAMEMODE_CREATIVE_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("1")) {
                                    this.gamemode = "1 (creative)";
                                } else if (args[0].equalsIgnoreCase("creative")) {
                                    this.gamemode = "creative";
                                }
                                target.setGameMode(GameMode.CREATIVE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                return true;
                            }
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if(sender.hasPermission(GAMEMODE_ADVENTURE_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("2")) {
                                    this.gamemode = "2 (adventure)";
                                } else if (args[0].equalsIgnoreCase("adventure")) {
                                    this.gamemode = "adventure";
                                }
                                target.setGameMode(GameMode.ADVENTURE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                                return true;
                            }
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if(sender.hasPermission(GAMEMODE_SPECTATOR_PERM_OTHER.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                    || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission())
                                    || sender.hasPermission(ALL_PERMS.getPermission())){
                                if (args[0].equalsIgnoreCase("3")) {
                                    this.gamemode = "3 (spectator)";
                                } else if (args[0].equalsIgnoreCase("spectator")) {
                                    this.gamemode = "spectator";
                                }
                                target.setGameMode(GameMode.SPECTATOR);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                return true;
                            }
                        }
                    }
                }
            }else if (sender instanceof ConsoleCommandSender) {
                if(args.length <= 1){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayerExact(args[1]);
                    hideActiveBossBar();
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survival)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survival";
                            }
                            target.setGameMode(GameMode.SURVIVAL);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (creative)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creative";
                            }
                            target.setGameMode(GameMode.CREATIVE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (adventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "adventure";
                            }
                            target.setGameMode(GameMode.ADVENTURE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectator)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectator";
                            }
                            target.setGameMode(GameMode.SPECTATOR);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }if(args.length == 2 && args[1].equalsIgnoreCase("all")){
                    Player target = (Player) Bukkit.getOnlinePlayers();
                    hideActiveBossBar();
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survival)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survival";
                            }
                            target.setGameMode(GameMode.SURVIVAL);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (creative)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creative";
                            }
                            target.setGameMode(GameMode.CREATIVE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (adventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "adventure";
                            }
                            target.setGameMode(GameMode.ADVENTURE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectator)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectator";
                            }
                            target.setGameMode(GameMode.SPECTATOR);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", gamemode).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", gamemode).replace("{1}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }
            }
        return false;
    }
}
