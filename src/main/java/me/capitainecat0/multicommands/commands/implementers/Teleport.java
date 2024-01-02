package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Teleport implements CommandsImpl {

    private final Player sender;
    private final Player target;
    private final Player target2;

    /**
     * La methode Teleport utilise la classe Player pour se téléporter.
     * <br>Si le joueur cible est null, cela téléportera le joueur (sender)
     * qui a exécuté la commande vers le joueur cible (target).
     * <br>Si le joueur cible n'est pas null, cela téléportera le joueur (target)
     * vers le joueur cible (target2).
     * <br>La variable sender est utile uniquement pour envoyer des messages au joueur
     * qui a exécuté la commande ou pour téléporter le joueur (sender) vers le joueur cible (target)
     * si le joueur cible (target2) est null.
     * @param sender Le joueur qui a exécuté la commande.
     * @param target Le joueur cible.
     * @param target2 Le joueur cible 2.
     */

    public Teleport(Player sender, Player target, Player target2) {
        this.sender = sender;
        this.target = target;
        this.target2 = target2;
    }
    @Override
    public void execute() {
        if (target != null && target2 == null) {
                sender.teleport(target.getLocation());
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, "Teleport", TELEPORT_SELF_TO_PLAYER.getMessage().replace("{0}", target.getName()));
            }else {
            assert target != null;
            target.teleport(target2.getLocation());
            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(target, "Teleport", TELEPORT_OTHER_TO_OTHER.getMessage().replace("{0}", target2.getName()));
            getMsgSendConfig(sender, "Teleport", TELEPORT_OTHER_TO_OTHER_SENDER.getMessage().replace("{0}", target.getName()).replace("{1}", target2.getName()));
        }
    }
}
