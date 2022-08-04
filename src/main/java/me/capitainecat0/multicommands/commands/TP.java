package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;

public class TP implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission(Perms.TELEPORT_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if(sender instanceof Player){
                if(args.length == 0){
                    if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                        double y = 500;
                        ItemStack book = BookUtil.writtenBook()
                                .author("CapitaineCat0")
                                .title("MultiCommands")
                                .pages(new BookUtil.PageBuilder()
                                                .add("§6§lAide de la commande §b§l/"+command.getName()+"§6:")
                                                .newLine().newLine()
                                                .add("§3Cette commande s'utilise avec des arguments. Sinon, bah je vous téléporte où ?")
                                                .newLine()
                                                .newLine()
                                                .add("§3Sans nomer un joueur ou donner des coordonnées, vous voulez que je vous téléporte où ?")
                                                .build(),
                                        new BookUtil.PageBuilder()
                                                .add("§3Ne vous connaissant pas, je ne peut pas savoir ce que vous voulez")
                                                .newLine()
                                                .add("§3donc, je pourrais vous téléporter au hazard x)")
                                                .newLine()
                                                .newLine()
                                                .add(new TextComponent("§3Donc §5tapez:"))
                                                .add(BookUtil.TextBuilder.of("§6/tp §fXx_DarkEaven-du93_xX")
                                                        .onClick(BookUtil.ClickAction.runCommand("/tp ~ 5000 ~"))
                                                        .build()
                                                )
                                                .build()
                                )
                                .build();

                        BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                    }else{
                        sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                        return true;
                    }
                }
                if(args.length <= 4){
                    if(args.length == 1){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            ((Player) sender).teleport(target.getLocation());
                            sender.sendMessage(Messenger.TELEPORT_SELF_TO_PLAYER.getMessage().replace("%p", target.getName()));
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                    if(args.length == 2){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(target2.getName())){
                            if(target2 != null){
                                if(target != null){
                                    target.teleport(target2.getLocation());
                                    target.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER.getMessage().replace("%p", target2.getName()));
                                    sender.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("%p1", target.getName()).replace("%p2", target2.getName()));
                                }else{
                                    sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                                }
                            }else{
                                sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                            }
                        }else{
                            sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                        }
                    }
                    if(args.length == 3){
                        try{
                            int x = Integer.parseInt(args[0].replace('~', (char) ((Player) sender).getLocation().getX()));
                            int y = Integer.parseInt(args[1].replace('~', (char) ((Player) sender).getLocation().getY()));
                            int z = Integer.parseInt(args[2].replace('~', (char) ((Player) sender).getLocation().getZ()));
                            ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                            sender.sendMessage(Messenger.TELEPORT_SELF_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                        }catch(NumberFormatException ex){
                            sender.sendMessage(Messenger.TELEPORT_INVALID_COORDINATES.getMessage());
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
                                target.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                                sender.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("%p", target.getName()).replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                            }catch(NumberFormatException ex){
                                sender.sendMessage(Messenger.TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                            }
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length <= 1){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 2){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    Player target2 = Bukkit.getPlayerExact(args[1]);
                    if(args[0].equalsIgnoreCase(target.getName()) && args[1].equalsIgnoreCase(target2.getName())){
                        if(target2 != null){
                            if(target != null){
                                target.teleport(target2.getLocation());
                                target.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER.getMessage().replace("%p", target2.getName()));
                                sender.sendMessage(Messenger.TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("%p1", target.getName()).replace("%p2", target2.getName()));
                            }else{
                                sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                            }
                        }else{
                            sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[1]));
                        }
                    }else{
                        sender.sendMessage(Messenger.TELEPORT_ERROR.getMessage());
                    }
                }
                if(args.length == 3){
                    try{
                        int x = Integer.parseInt(args[0].replace('~', (char) ((Player) sender).getLocation().getX()));
                        int y = Integer.parseInt(args[1].replace('~', (char) ((Player) sender).getLocation().getY()));
                        int z = Integer.parseInt(args[2].replace('~', (char) ((Player) sender).getLocation().getZ()));
                        ((Player) sender).teleport(new Location(((Player) sender).getWorld(), x, y, z));
                        sender.sendMessage(Messenger.TELEPORT_SELF_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                    }catch(NumberFormatException ex){
                        sender.sendMessage(Messenger.TELEPORT_INVALID_COORDINATES.getMessage());
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
                            target.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES.getMessage().replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                            sender.sendMessage(Messenger.TELEPORT_OTHER_TO_COORDINATES_SENDER.getMessage().replace("%p", target.getName()).replace("%loc%", "X "+x+" Y "+y+" Z"+z));
                        }catch(NumberFormatException ex){
                            sender.sendMessage(Messenger.TELEPORT_OTHER_INVALID_COORDINATES.getMessage());
                        }
                    }else{
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }
        }
        return false;
    }
}