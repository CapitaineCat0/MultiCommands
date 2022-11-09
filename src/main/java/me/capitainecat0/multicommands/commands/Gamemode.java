package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.GUICreator;
import me.capitainecat0.multicommands.utils.ItemCreator;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Gamemode implements CommandExecutor {
    private String gamemode = null;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<mode> [joueur]"));
                    return true;
                }else if (args.length == 1) {
                    if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")){
                        if(sender.hasPermission(GAMEMODE_SURVIVAL_PERM_SELF.getPermission()) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survie)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survie";
                            }
                            player.setGameMode(GameMode.SURVIVAL);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                        if(sender.hasPermission(GAMEMODE_CREATIVE_PERM_SELF.getPermission()) || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())) {
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (créatif)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creatif";
                            }
                            player.setGameMode(GameMode.CREATIVE);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if(sender.hasPermission(GAMEMODE_ADVENTURE_PERM_SELF.getPermission())
                                || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (aventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "aventure";
                            }
                            player.setGameMode(GameMode.ADVENTURE);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }

                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if(sender.hasPermission(GAMEMODE_SPECTATOR_PERM_SELF.getPermission())
                                || sender.hasPermission(GAMEMODE_PERM_ALL.getPermission())
                                || sender.hasPermission(ALL_PERMS.getPermission())){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectateur)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectateur";
                            }
                            player.setGameMode(GameMode.SPECTATOR);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
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
                                    this.gamemode = "0 (survie)";
                                } else if (args[0].equalsIgnoreCase("survival")) {
                                    this.gamemode = "survie";
                                }
                                target.setGameMode(GameMode.SURVIVAL);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
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
                                    this.gamemode = "1 (créatif)";
                                } else if (args[0].equalsIgnoreCase("creative")) {
                                    this.gamemode = "creatif";
                                }
                                target.setGameMode(GameMode.CREATIVE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
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
                                    this.gamemode = "2 (aventure)";
                                } else if (args[0].equalsIgnoreCase("adventure")) {
                                    this.gamemode = "aventure";
                                }
                                target.setGameMode(GameMode.ADVENTURE);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
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
                                    this.gamemode = "3 (spectateur)";
                                } else if (args[0].equalsIgnoreCase("spectator")) {
                                    this.gamemode = "spectateur";
                                }
                                target.setGameMode(GameMode.SPECTATOR);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                getMsgSendConfig(sender, command.getName(), GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                                return true;
                            }
                        }
                    } else {
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }else if (sender instanceof ConsoleCommandSender) {
                if(args.length <= 1){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayerExact(args[1]);
                    hideActiveBossBar();
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                            if (args[0].equalsIgnoreCase("0")) {
                                this.gamemode = "0 (survie)";
                            } else if (args[0].equalsIgnoreCase("survival")) {
                                this.gamemode = "survie";
                            }
                            target.setGameMode(GameMode.SURVIVAL);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (créatif)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creatif";
                            }
                            target.setGameMode(GameMode.CREATIVE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (aventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "aventure";
                            }
                            target.setGameMode(GameMode.ADVENTURE);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectateur)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectateur";
                            }
                            target.setGameMode(GameMode.SPECTATOR);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sendConsoleMessage(GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                    }
                }
            }

        return false;
    }

    /*public Gamemode() {
        super(9, "&6&lMultiHelp &9V."+MultiCommands.getInstance().getDescription().getVersion()+" &aGamemode");
        setItem(0, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(1, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(2, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

        setItem(3, ItemCreator.create(Material.STONE, "&6Gamemode survie"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GAMEMODE_ALL_PERM.getPermission()) || !player.hasPermission(Perms.GAMEMODE_SURVIVAL_SELF_PERM.getPermission()) || !player.hasPermission(Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode survie", Collections.singletonList("&cVous n'avez pas la permission d'utiliser cette commande!")));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.SURVIVAL);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode survie", GAMEMODE_SELF.getMessage().replace("%gamemode%", "survie"));
                    close(player);
                }
            }
        });
        setItem(4, ItemCreator.create(Material.STONE, "&6Gamemode créatif"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GAMEMODE_ALL_PERM.getPermission()) || !player.hasPermission(Perms.GAMEMODE_ADVENTURE_SELF_PERM.getPermission()) || !player.hasPermission(Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode créatif", Collections.singletonList("&cVous n'avez pas la permission d'utiliser cette commande!")));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.CREATIVE);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode créatif", GAMEMODE_SELF.getMessage().replace("%gamemode%", "créatif"));
                    close(player);
                }
            }
        });
        setItem(5, ItemCreator.create(Material.STONE, "&6Gamemode aventure"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GAMEMODE_ALL_PERM.getPermission()) || !player.hasPermission(Perms.GAMEMODE_CREATIVE_SELF_PERM.getPermission()) || !player.hasPermission(Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode aventure", Collections.singletonList("&cVous n'avez pas la permission d'utiliser cette commande!")));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.ADVENTURE);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode aventure", GAMEMODE_SELF.getMessage().replace("%gamemode%", "aventure"));
                    close(player);
                }
            }
        });
        setItem(6, ItemCreator.create(Material.STONE, "&6Gamemode spectateur"), (player, event) -> {
            GUICreator gui = GUICreator.getGUI(player);
            ItemStack item = event.getCurrentItem();
            assert item != null;
            if(!player.hasPermission(Perms.GAMEMODE_ALL_PERM.getPermission()) || !player.hasPermission(Perms.GAMEMODE_SPECTATOR_SELF_PERM.getPermission()) || !player.hasPermission(Perms.ALL_PERMS.getPermission()) && item.getType() == Material.STONE){
                if(soundEnabled()){
                    playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                gui.setItem(event.getRawSlot(), ItemCreator.create(Material.BARRIER, "&3Gamemode spectateur", Collections.singletonList("&cVous n'avez pas la permission d'utiliser cette commande!")));
            }
            else{
                if(item.getType() != Material.BARRIER){
                    player.setGameMode(GameMode.SPECTATOR);
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, "gamemode spectateur", GAMEMODE_SELF.getMessage().replace("%gamemode%", "spectateur"));
                    close(player);
                }
            }
        });

        setItem(7, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(8, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));
        setItem(9, ItemCreator.create(Material.BLACK_STAINED_GLASS_PANE, " "));

    }*/
}
