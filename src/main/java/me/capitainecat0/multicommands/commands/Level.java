package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
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

public class Level implements CommandExecutor {

    /**
     *
     * The Level command can manage player levels
     * @params:
     * <br>add - Add levels to player
     * <br>set - Set the player level
     * <br>remove - Remove levels from player
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            try{
                if(args.length <= 1){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<add | set | remove> <value> [player]"));
                }else{
                    String action = args[0].toLowerCase();
                    int level = 0;
                    try {
                        level = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sendConsoleMessage(e.getMessage());
                    }
                    if(action.equals("add") || action.equals("set") || action.equals("remove")){
                        if(!sender.hasPermission(LEVEL_ADD_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        }else{
                            if(args.length == 2){
                                processCommand(sender, command.getName(), action, level);
                            }else if(args.length == 3){
                                Player target = Bukkit.getPlayerExact(args[2]);
                                processCommand(target, command.getName(), action, level);
                                processCommand(sender, command.getName(), action, level);
                            }
                        }
                    }
                }
            }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
            }
        }else if(sender instanceof ConsoleCommandSender){
            try{
                if(args.length >= 2){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("{0}", command.getName()).replace("{1}", "<add | set | remove> <value> <player>"));
                }else if(args.length == 3){
                    String action = args[0].toLowerCase();
                    int level = 0;
                    try {
                        level = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sendConsoleMessage(e.getMessage());
                    }
                    if(action.equals("add") || action.equals("set") || action.equals("remove")){
                        if(args.length <= 2){
                            sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("{0}", command.getName()).replace("{1}", "<add | set | remove> <value> <player>"));
                        }else if(args.length == 3){
                            Player target = Bukkit.getPlayerExact(args[2]);
                            processConsoleCommand(target, action, level);
                        }
                    }
                }
            }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
            }
        }
        return false;
    }

    /**
     * The processConsoleCommand function is used to process the console command.
     *
     *
     * @param player Get the player's name and send them a message
     * @param action Determine what action to take
     * @param level Determine how much the player's level will be changed
     */
    private void processConsoleCommand(Player player, String action, int level) {
        try{
            if(action.equals("add")){
                sendConsoleMessage(LEVEL_ADD_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", player.getName()));
                getMsgSendConfig(player, "level", LEVEL_ADD.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(player.getLevel() + level);
            }else if(action.equals("set")){
                sendConsoleMessage(LEVEL_SET_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", player.getName()));
                getMsgSendConfig(player, "level", LEVEL_SET.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(level);
            }else if(action.equals("remove")){
                if(level <= player.getLevel()){
                    sendConsoleMessage(LEVEL_REMOVE_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", player.getName()));
                    getMsgSendConfig(player, "level", LEVEL_REMOVE.getMessage().replace("{0}", String.valueOf(level)));
                    player.setLevel(player.getLevel() - level);
                }else{
                    player.setLevel(0);
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, "level (consoleProcess)");
        }
    }

    /**
     * The processCommand function is used to process the command that was sent by the player.
     * It will check if it's an add, set or remove action and then execute it accordingly.
     *
     *
     * @param player Get the player who executed the command
     * @param commandName Get the message from the config
     * @param action Determine what action to take
     * @param level Add, set or remove the level of a player
     */
    private void processCommand(Player player, String commandName, String action, int level){
        try{
            if(action.equals("add")){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(player, commandName, LEVEL_ADD.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(player.getLevel() + level);
            }else if(action.equals("set")){
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(player, commandName, LEVEL_SET.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(level);
            }else if(action.equals("remove")){
                if(level <= player.getLevel()){
                    playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(player, commandName, LEVEL_REMOVE.getMessage().replace("{0}", String.valueOf(level)));
                    player.setLevel(player.getLevel() - level);
                }else{
                    player.setLevel(0);
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, "level (commandProcess > player)");
        }
    }
    /**
     * The processCommand function is used to process the command that was sent by the player.
     * It takes in a CommandSender, which is usually a Player object, but can also be ConsoleCommandSender or BlockCommandSender.
     * The function also takes in two strings: commandName and action. The first string represents the name of the command that was sent by the player (e.g., /level).
     * The second string represents what action should be taken on this particular level (e.g., add, set, remove). Finally, it takes an integer representing how many levels should be added/removed/set to for
     *
     * @param sender Send the message to the player
     * @param commandName Get the message from the config
     * @param action Determine what to do with the level
     * @param level Determine how much the player's level should be changed by
     */
    private void processCommand(CommandSender sender, String commandName, String action, int level){
        try{
            Player player = (Player)sender;
            if(action.equals("add")){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, commandName, LEVEL_ADD.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(player.getLevel() + level);
            }else if(action.equals("set")){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, commandName, LEVEL_SET.getMessage().replace("{0}", String.valueOf(level)));
                player.setLevel(level);
            }else if(action.equals("remove")){
                if(level <= player.getLevel()){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(sender, commandName, LEVEL_REMOVE.getMessage().replace("{0}", String.valueOf(level)));
                    player.setLevel(player.getLevel() - level);
                }else{
                    player.setLevel(0);
                }
            }
        }catch(Exception e) {
            sendCommandExceptionMessage(e, "level (commandProcess > sender)");
        }
    }
}
