package me.capitainecat0.multicommands.utils;

import org.bukkit.entity.Player;

public class TPARequest {
    private final Player from;
    private final Player to;
    public TPARequest(Player from, Player to) {
        this.from = from;
        this.to = to;
    }

    public Player getFrom() {
        return from;
    }

    public Player getTo() {
        return to;
    }
}
