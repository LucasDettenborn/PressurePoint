package DTO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import Helpers.Tools;

public class AnswersFlowDTO {

    private int Id;
    private int Users_Id;
    private Date AnswersDate;
    private int LevelBeforePressurePoints;
    private int LevelAfterPressurePoints;
    private boolean GotBetterAfter;

    public AnswersFlowDTO() {
    }

    public AnswersFlowDTO(int id, int users_Id, Date answersDate, int levelBeforePressurePoints, int levelAfterPressurePoints, boolean gotBetterAfter) {
        Id = id;
        Users_Id = users_Id;
        AnswersDate = answersDate;
        LevelBeforePressurePoints = levelBeforePressurePoints;
        LevelAfterPressurePoints = levelAfterPressurePoints;
        GotBetterAfter = gotBetterAfter;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public static String getIdColumnName() {
        return "Id";
    }

    public int getUsers_Id() {
        return Users_Id;
    }

    public void setUsers_Id(int users_Id) {
        Users_Id = users_Id;
    }

    public String getUsers_IdColumnName() {
        return "Users_Id";
    }

    public Date getAnswersDate() {
        return AnswersDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getAnswersDateToSQL() {
        LocalDateTime ldt = convertToLocalDateTimeViaInstant(getAnswersDate());
        //2021-03-15 15:33:15
        return ldt.getYear() + "-"
                + (ldt.getMonth().getValue() < 10 ? "0" + ldt.getMonth().getValue() : ldt.getMonth().getValue())
                + "-" + ldt.getDayOfMonth() + " "
                + (ldt.getHour() < 10 ? "0" + ldt.getHour() : ldt.getHour()) + ":"
                + (ldt.getMinute() < 10 ? "0" + ldt.getMinute() : ldt.getMinute()) + ":"
                + (ldt.getMinute() < 10 ? "0" + ldt.getSecond() : ldt.getSecond());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getAnswersDateToShow() {
        LocalDateTime ldt = convertToLocalDateTimeViaInstant(getAnswersDate());
        //2021-03-15 15:33:15
        return ldt.getDayOfMonth()
                + " de "
                + (Tools.getMontNamePtBR(ldt.getMonth().getValue()))
                + " de "
                + ldt.getYear() + " "
                + (ldt.getHour() < 10 ? "0" + ldt.getHour() : ldt.getHour()) + ":"
                + (ldt.getMinute() < 10 ? "0" + ldt.getMinute() : ldt.getMinute()) + ":"
                + (ldt.getMinute() < 10 ? "0" + ldt.getSecond() : ldt.getSecond());
    }

    public void setAnswersDate(Date answersDate) {
        AnswersDate = answersDate;
    }

    public String getAnswersDateColumnName() {
        return "AnswersDate";
    }

    public int getLevelBeforePressurePoints() {
        return LevelBeforePressurePoints;
    }

    public void setLevelBeforePressurePoints(int levelBeforePressurePoints) {
        LevelBeforePressurePoints = levelBeforePressurePoints;
    }

    public String getLevelBeforePressurePointsColumnName() {
        return "LevelBeforePressurePoints";
    }

    public int getLevelAfterPressurePoints() {
        return LevelAfterPressurePoints;
    }

    public void setLevelAfterPressurePoints(int levelAfterPressurePoints) {
        LevelAfterPressurePoints = levelAfterPressurePoints;
    }

    public String getLevelAfterPressurePointsColumnName() {
        return "LevelAfterPressurePoints";
    }

    public boolean isGotBetterAfter() {
        return GotBetterAfter;
    }

    public void setGotBetterAfter(boolean gotBetterAfter) {
        GotBetterAfter = gotBetterAfter;
    }

    public String getGotBetterAfterColumnName() {
        return "GotBetterAfter";
    }

    public String getTableName(){
        return this.getClass().getName().replace("DTO", "");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
