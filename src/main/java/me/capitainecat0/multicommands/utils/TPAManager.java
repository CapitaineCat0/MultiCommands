package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class TPAManager {
    private final Map<UUID, TPARequest> requests = new HashMap<>();
    public void acceptTPA(Player target) {
        try{
            TPARequest request = requests.get(target.getUniqueId());
            if(request == null){
                getMsgSendConfig(target, "tpa", Messenger.TPA_NO_REQUEST.getMessage());
                return;
            }
            requests.remove(target.getUniqueId());
            getMsgSendConfig(target, "tpa", Messenger.TPA_REQUEST_ACCEPT.getMessage().replace("{0}", request.getTo().getName()));
            request.getFrom().teleport(target.getLocation());

        }catch(Exception e){
            sendCommandExceptionMessage(e, "tpa accept");
        }
    }
    public void denyTPA(Player target) {
        try{
            TPARequest request = requests.get(target.getUniqueId());
            if(request == null){
                getMsgSendConfig(target, "tpa", Messenger.TPA_NO_REQUEST.getMessage());
                return;
            }
            requests.remove(target.getUniqueId());
            getMsgSendConfig(target, "tpa", Messenger.TPA_REQUEST_DENY.getMessage().replace("{0}", request.getTo().getName()));
        }catch(Exception e){
            sendCommandExceptionMessage(e, "tpa deny");
        }
    }
}
