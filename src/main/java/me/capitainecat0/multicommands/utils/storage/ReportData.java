package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

public class ReportData {
    private Player player;
    private String author;
    private String note;
    private Date date;
    private UUID id;
    private int identifier;
    private boolean identifierBool;

    public ReportData(@NotNull Player player, String author, String note) {
        this.player = player;
        this.author = author;
        this.note = note;
        this.date = new Date();
        this.id = player.getUniqueId();
    }

    public int getIdentifier() {
        return identifier;
    }

    public boolean isIdentified() {
        if(identifier == 0){
            identifierBool = false;
        }
        return identifierBool;
    }

    public void setIdentifier(int id) {
        if(isIdentified()){
            identifier = id;
        }else{
            identifier = 1;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getPlayer() {
        return player.getName();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
