package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class StoreReport {
    private static final ArrayList<ReportData> notes = new ArrayList<>();

    public static void createReport(@NotNull Player player, @NotNull Player author, String note) {
        ReportData reportData = new ReportData(player, author.getName(), note);
        notes.add(reportData);
        //saveReport();
    }

    private static @Nullable ReportData findReportById(UUID id) {
        for (ReportData noteReport : notes) {
            if (noteReport.getId().equals(id)) {
                return noteReport;
            }
        }
        return null;
    }

    public static @Nullable ReportData findReport(UUID id) {
        return findReportById(id);
    }

    public static void deleteReport(UUID id) {
        ReportData reportData = findReportById(id);
            if (reportData != null) {
            notes.remove(reportData);
            //saveReport();
        }
    }

    public static void updateReport(UUID id, Player player, String author, String note) {
        ReportData reportData = findReportById(id);
        if (reportData != null) {
            reportData.setPlayer(player);
            reportData.setAuthor(author);
            reportData.setNote(note);
            reportData.setDate(new Date());
            if(!reportData.isIdentified()){
                reportData.setIdentifier(1);
            }
            reportData.setIdentifier(reportData.getIdentifier()+1);
            //saveReport();
        }
    }

    public static ArrayList<ReportData> getNotes() {
        return notes;
    }

    /*public static void saveReport() {
        Gson gson = new Gson();
        saveResourceAs(MultiCommands.getInstance().getClass(), "reports.json", false);
        saveFile(new File(MultiCommands.getInstance().getDataFolder(), "reports.json"), gson.toJson(notes));
    }*/
}
