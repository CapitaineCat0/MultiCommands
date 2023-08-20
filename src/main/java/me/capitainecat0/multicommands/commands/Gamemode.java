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

    /**
     *
     * The Gamemode command can change player game-mode
     * @params:
     * <br>0 | survival - Change to survival game-mode
     * <br>1 | creative - Change to creative game-mode
     * <br>2 | adventure - Change to adventure game-mode
     * <br>3 | spectator - Change to spectator game-mode
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        if (sender instanceof Player player) {
            if (args.length == 0) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                new GamemodeGUI().open((Player) sender);
                //getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<mode> [player]"));
                return true;
            } else if (args.length == 1) {
                try{
                    String gamemode = args[0].toLowerCase();
                    GameMode mode = null;
                    if (gamemode.equals("0") || gamemode.equals("survival")) {
                        mode = GameMode.SURVIVAL;
                        gamemode = "0 (survival)";
                    } else if (gamemode.equals("1") || gamemode.equals("creative")) {
                        mode = GameMode.CREATIVE;
                        gamemode = "1 (creative)";
                    } else if (gamemode.equals("2") || gamemode.equals("adventure")) {
                        mode = GameMode.ADVENTURE;
                        gamemode = "2 (adventure)";
                    } else if (gamemode.equals("3") || gamemode.equals("spectator")) {
                        mode = GameMode.SPECTATOR;
                        gamemode = "3 (spectator)";
                    }
                    if (mode != null) {
                        if (sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                            player.setGameMode(mode);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("{0}", gamemode));
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName()+" "+gamemode);
                }
            } else if (args.length == 2) {
                try{
                    Player target = Bukkit.getPlayerExact(args[1]);
                    hideActiveBossBar();
                    if (target != null) {
                        String mode = args[0].toLowerCase();
                        GameMode gameMode = null;
                        String permission = "";
                        if (mode.equals("0") || mode.equals("survival")) {
                            gameMode = GameMode.SURVIVAL;
                            permission = GAMEMODE_SURVIVAL_PERM_OTHER.getPermission();
                        } else if (mode.equals("1") || mode.equals("creative")) {
                            gameMode = GameMode.CREATIVE;
                            permission = GAMEMODE_CREATIVE_PERM_OTHER.getPermission();
                        } else if (mode.equals("2") || mode.equals("adventure")) {
                            gameMode = GameMode.ADVENTURE;
                            permission = GAMEMODE_ADVENTURE_PERM_OTHER.getPermission();
                        } else if (mode.equals("3") || mode.equals("spectator")) {
                            gameMode = GameMode.SPECTATOR;
                            permission = GAMEMODE_SPECTATOR_PERM_OTHER.getPermission();
                        }
                        if (gameMode != null) {
                            if (sender.hasPermission(permission) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                                target.setGameMode(gameMode);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", mode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", mode).replace("{1}", target.getName()));
                            } else {
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                            }
                        }
                    } else {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName()+" "+gamemode+" "+args[1]);
                }
            } else if (args.length == 2 && args[1].equalsIgnoreCase("all")) {
                try{
                    hideActiveBossBar();
                    Player target = (Player) Bukkit.getOnlinePlayers();
                    String mode = args[0].toLowerCase();
                    GameMode gameMode = null;
                    String permission = "";
                    if (mode.equals("0") || mode.equals("survival")) {
                        gameMode = GameMode.SURVIVAL;
                        permission = GAMEMODE_SURVIVAL_PERM_OTHER.getPermission();
                    } else if (mode.equals("1") || mode.equals("creative")) {
                        gameMode = GameMode.CREATIVE;
                        permission = GAMEMODE_CREATIVE_PERM_OTHER.getPermission();
                    } else if (mode.equals("2") || mode.equals("adventure")) {
                        gameMode = GameMode.ADVENTURE;
                        permission = GAMEMODE_ADVENTURE_PERM_OTHER.getPermission();
                    } else if (mode.equals("3") || mode.equals("spectator")) {
                        gameMode = GameMode.SPECTATOR;
                        permission = GAMEMODE_SPECTATOR_PERM_OTHER.getPermission();
                    }
                    if (gameMode != null) {
                        if (sender.hasPermission(permission) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                            target.setGameMode(gameMode);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", mode));
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", mode).replace("{1}", target.getName()));
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName()+" "+gamemode+" all");
                }
            } else if (sender instanceof ConsoleCommandSender) {
                if (args.length <= 1) {
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                }
                if (args.length == 2) {
                    try{
                        Player target = Bukkit.getPlayerExact(args[1]);
                        hideActiveBossBar();
                        if (target != null) {
                            String mode = args[0].toLowerCase();
                            GameMode gameMode = null;
                            String permission = "";
                            if (mode.equals("0") || mode.equals("survival")) {
                                gameMode = GameMode.SURVIVAL;
                                permission = GAMEMODE_SURVIVAL_PERM_OTHER.getPermission();
                            } else if (mode.equals("1") || mode.equals("creative")) {
                                gameMode = GameMode.CREATIVE;
                                permission = GAMEMODE_CREATIVE_PERM_OTHER.getPermission();
                            } else if (mode.equals("2") || mode.equals("adventure")) {
                                gameMode = GameMode.ADVENTURE;
                                permission = GAMEMODE_ADVENTURE_PERM_OTHER.getPermission();
                            } else if (mode.equals("3") || mode.equals("spectator")) {
                                gameMode = GameMode.SPECTATOR;
                                permission = GAMEMODE_SPECTATOR_PERM_OTHER.getPermission();
                            }
                            if (gameMode != null) {
                                if (sender.hasPermission(permission) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                                    target.setGameMode(gameMode);
                                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", mode));
                                    getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", mode).replace("{1}", target.getName()));
                                } else {
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                                }
                            }
                        } else {
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[1]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName()+" "+gamemode+" "+args[1]);
                    }
                }
                if (args.length == 2 && args[1].equalsIgnoreCase("all")) {
                    try{
                        Player target = (Player) Bukkit.getOnlinePlayers();
                        hideActiveBossBar();
                        String mode = args[0].toLowerCase();
                        GameMode gameMode = null;
                        String permission = "";
                        if (mode.equals("0") || mode.equals("survival")) {
                            gameMode = GameMode.SURVIVAL;
                            permission = GAMEMODE_SURVIVAL_PERM_OTHER.getPermission();
                        } else if (mode.equals("1") || mode.equals("creative")) {
                            gameMode = GameMode.CREATIVE;
                            permission = GAMEMODE_CREATIVE_PERM_OTHER.getPermission();
                        } else if (mode.equals("2") || mode.equals("adventure")) {
                            gameMode = GameMode.ADVENTURE;
                            permission = GAMEMODE_ADVENTURE_PERM_OTHER.getPermission();
                        } else if (mode.equals("3") || mode.equals("spectator")) {
                            gameMode = GameMode.SPECTATOR;
                            permission = GAMEMODE_SPECTATOR_PERM_OTHER.getPermission();
                        }
                        if (gameMode != null) {
                            if (sender.hasPermission(permission) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(GAMEMODE_PERM_OTHER_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                                target.setGameMode(gameMode);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("{0}", mode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("{0}", mode).replace("{1}", target.getName()));
                            } else {
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            }
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName()+" "+gamemode+ " all");
                    }
                }
            }
        }
        return false;
    }
}
