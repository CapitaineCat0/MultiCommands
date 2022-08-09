package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class Gamemode implements CommandExecutor {
    private String gamemode = null;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.GAMEMODE_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    ItemStack book = BookUtil.writtenBook()
                            .author("CapitaineCat0")
                            .title("MultiCommands")
                            .pages(new BookUtil.PageBuilder()
                                            .add("§6§lAide de la commande §b§l/"+command.getName()+"§6:")
                                            .newLine().newLine()
                                            .add("§3Cette commande s'utilise avec des arguments. Sinon, bah elle ne fonctionne pas !")
                                            .newLine()
                                            .newLine()
                                            .add("§3Sans marquer le mode de jeu que vous souhaitez, comment puis-je le deviner ?")
                                            .build(),
                                    new BookUtil.PageBuilder()
                                            .add("§3Ne vous connaissant pas, je ne vais pas vous traiter de con comme ça. Surtout que je suis un livre,")
                                            .newLine()
                                            .add("§3et que si vous perdez votre temps à me lire, vous êtes une personne très stupide x)")
                                            .newLine()
                                            .newLine()
                                            .add(new TextComponent("§3Donc §5tapez:"))
                                            .add(BookUtil.TextBuilder.of("§6/gamemode §d0/1/2/3 §6ou §d survival/creative/adventure/spectator")
                                                    .onClick(BookUtil.ClickAction.runCommand("/gamemode adventure"+sender.getName()))
                                                    .build()
                                            )
                                            .build()
                            )
                            .build();

                    BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                }else{
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_ERROR.getMessage().replace("%cmd%", command.getName()));
                    return true;
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
                        if (args[0].equalsIgnoreCase("0")) {
                            this.gamemode = "0 (survie)";
                        } else if (args[0].equalsIgnoreCase("survival")) {
                            this.gamemode = "survie";
                        }
                        player.setGameMode(GameMode.SURVIVAL);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
                        if (args[0].equalsIgnoreCase("1")) {
                            this.gamemode = "1 (créatif)";
                        } else if (args[0].equalsIgnoreCase("creative")) {
                            this.gamemode = "creatif";
                        }
                        player.setGameMode(GameMode.CREATIVE);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
                        if (args[0].equalsIgnoreCase("2")) {
                            this.gamemode = "2 (aventure)";
                        } else if (args[0].equalsIgnoreCase("adventure")) {
                            this.gamemode = "aventure";
                        }
                        player.setGameMode(GameMode.ADVENTURE);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")) {
                        if (args[0].equalsIgnoreCase("3")) {
                            this.gamemode = "3 (spectateur)";
                        } else if (args[0].equalsIgnoreCase("spectator")) {
                            this.gamemode = "spectateur";
                        }
                        player.setGameMode(GameMode.SPECTATOR);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_SELF.getMessage().replace("%gamemode%", gamemode));
                    }
                }
                if (args.length == 2) {
                    if (!sender.hasPermission(Perms.ALL_PERMS.getPermission())) {
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM_TO_OTHER.getMessage());
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
                                MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                                if (args[0].equalsIgnoreCase("1")) {
                                    this.gamemode = "1 (créatif)";
                                } else if (args[0].equalsIgnoreCase("creative")) {
                                    this.gamemode = "creatif";
                                }
                                target.setGameMode(GameMode.CREATIVE);
                                MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                                if (args[0].equalsIgnoreCase("2")) {
                                    this.gamemode = "2 (aventure)";
                                } else if (args[0].equalsIgnoreCase("adventure")) {
                                    this.gamemode = "aventure";
                                }
                                target.setGameMode(GameMode.ADVENTURE);
                                MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                                if (args[0].equalsIgnoreCase("3")) {
                                    this.gamemode = "3 (spectateur)";
                                } else if (args[0].equalsIgnoreCase("spectator")) {
                                    this.gamemode = "spectateur";
                                }
                                target.setGameMode(GameMode.SPECTATOR);
                                MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                                MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                            }
                        } else {
                            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
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
                            MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")){
                            if (args[0].equalsIgnoreCase("1")) {
                                this.gamemode = "1 (créatif)";
                            } else if (args[0].equalsIgnoreCase("creative")) {
                                this.gamemode = "creatif";
                            }
                            target.setGameMode(GameMode.CREATIVE);
                            MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")){
                            if (args[0].equalsIgnoreCase("2")) {
                                this.gamemode = "2 (aventure)";
                            } else if (args[0].equalsIgnoreCase("adventure")) {
                                this.gamemode = "aventure";
                            }
                            target.setGameMode(GameMode.ADVENTURE);
                            MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
                            sender.sendMessage(Messenger.GAMEMODE_OTHER_ADMIN.getMessage().replace("%gamemode%", gamemode).replace("%p", target.getName()));
                        }else if(args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectator")){
                            if (args[0].equalsIgnoreCase("3")) {
                                this.gamemode = "3 (spectateur)";
                            } else if (args[0].equalsIgnoreCase("spectator")) {
                                this.gamemode = "spectateur";
                            }
                            target.setGameMode(GameMode.SPECTATOR);
                            MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.GAMEMODE_OTHER.getMessage().replace("%gamemode%", gamemode));
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
