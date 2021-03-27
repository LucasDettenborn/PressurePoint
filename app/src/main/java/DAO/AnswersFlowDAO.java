package DAO;

import android.os.Build;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.pressurepoints.ConnectionHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import DTO.AnswersFlowDTO;

public class AnswersFlowDAO {

    private String messageBoxText = "";
    private boolean isSucess = false;

    public AnswersFlowDAO() {
    }

    private AnswersFlowDTO getAnswersFlowDTOExecuted(String query){
        try{
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            if(con == null){
                setMessageBoxText("Verifique sua conexão com a internet!");
                return null;
            }else{
                if(query == null || query.isEmpty()){
                    setMessageBoxText("Não fora encontrada a query de execução!");
                    return null;
                }else{
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        AnswersFlowDTO afDtoNames = new AnswersFlowDTO();
                        SimpleDateFormat formatterDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                        AnswersFlowDTO afDtoData = new AnswersFlowDTO(
                                Integer.parseInt(rs.getString(afDtoNames.getUsers_IdColumnName())),
                                Integer.parseInt(rs.getString(afDtoNames.getUsers_IdColumnName())),
                                formatterDate.parse(rs.getString(afDtoNames.getAnswersDateColumnName())),
                                Integer.parseInt(rs.getString(afDtoNames.getLevelBeforePressurePointsColumnName())),
                                Integer.parseInt(rs.getString(afDtoNames.getLevelAfterPressurePointsColumnName())),
                                Boolean.parseBoolean(rs.getString(afDtoNames.getGotBetterAfterColumnName()))
                        );
                        setMessageBoxText("Query executada com sucesso!");
                        setSucess(true);
                        con.close();
                        return afDtoData;
                    }else{
                        setMessageBoxText("Query inválida!");
                        setSucess(false);
                        con.close();
                        return null;
                    }
                }
            }
        }catch(Exception e) {
            setSucess(false);
            setMessageBoxText(e.getMessage());
            Log.d("sql error", getMessageBoxText());
            return null;
        }
    }

    public ArrayList<AnswersFlowDTO> getAnswersFlowDTOList(int userID){
        try
        {
            AnswersFlowDTO answersFlowDTO = new AnswersFlowDTO();
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            String query = "SELECT "
                    + answersFlowDTO.getIdColumnName() + ", "
                    + answersFlowDTO.getUsers_IdColumnName() + ", "
                    + answersFlowDTO.getAnswersDateColumnName() + ", "
                    + answersFlowDTO.getLevelBeforePressurePointsColumnName() + ", "
                    + answersFlowDTO.getLevelAfterPressurePointsColumnName() + ", "
                    + answersFlowDTO.getGotBetterAfterColumnName() +
                    " FROM "
                    + answersFlowDTO.getTableName() +
                    " WHERE " + answersFlowDTO.getUsers_IdColumnName() + " = "
                    + userID;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.isBeforeFirst()){
                AnswersFlowDTO answersFlowDtoNames = new AnswersFlowDTO();
                ArrayList<AnswersFlowDTO> answersFlowDTOArrayList = new ArrayList<AnswersFlowDTO>();
                while (rs.next()){
                    answersFlowDTOArrayList.add(new AnswersFlowDTO(
                            rs.getInt(answersFlowDtoNames.getIdColumnName()),
                            rs.getInt(answersFlowDtoNames.getUsers_IdColumnName()),
                            rs.getTimestamp(answersFlowDtoNames.getAnswersDateColumnName()),
                            rs.getInt(answersFlowDtoNames.getLevelBeforePressurePointsColumnName()),
                            rs.getInt(answersFlowDtoNames.getLevelAfterPressurePointsColumnName()),
                            rs.getBoolean(answersFlowDtoNames.getGotBetterAfterColumnName())
                    ));
                }
                if(!answersFlowDTOArrayList.isEmpty()){
                    setMessageBoxText("Query executada com sucesso!");
                    setSucess(true);
                    con.close();
                    return answersFlowDTOArrayList;
                }else{
                    setMessageBoxText("Não foi possível montar os dados prodivos da base de dados para o uso do sistema!");
                    setSucess(false);
                    con.close();
                    return null;
                }
            }else{
                setMessageBoxText("Query inválida!");
                setSucess(false);
                con.close();
                return null;
            }
        }
        catch (SQLException se)
        {
            setSucess(false);
            setMessageBoxText(se.getMessage());
            Log.d("sql error", getMessageBoxText());
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertAnswersFlowDTO(AnswersFlowDTO  answersFlowDTO){
        try
        {
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            String query = "INSERT INTO " + answersFlowDTO.getTableName()
                    +"(" + answersFlowDTO.getUsers_IdColumnName() + ", "
                    + answersFlowDTO.getAnswersDateColumnName() + ", "
                    + answersFlowDTO.getLevelBeforePressurePointsColumnName() + ", "
                    + answersFlowDTO.getLevelAfterPressurePointsColumnName() + ", "
                    + answersFlowDTO.getGotBetterAfterColumnName() + ")" +
                    "VALUES (" + answersFlowDTO.getUsers_Id() + ", '"
                    + answersFlowDTO.getAnswersDateToSQL() + "', "
                    + answersFlowDTO.getLevelBeforePressurePoints() + ", "
                    + answersFlowDTO.getLevelAfterPressurePoints() + ", '"
                    + answersFlowDTO.isGotBetterAfter() + "'"
                    +")" ;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            setMessageBoxText("Query executada com sucesso!");
            setSucess(true);
            con.close();
        }
        catch (SQLException se)
        {
            setSucess(false);
            setMessageBoxText(se.getMessage());
            Log.e("ERROR", se.getMessage());
        }
    }

    public AnswersFlowDTO selectUserDTO(int userID, Date answersDate){
        try
        {
            AnswersFlowDTO answersFlowDTO = new AnswersFlowDTO();
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            String query = "SELECT "
                    + answersFlowDTO.getIdColumnName() + ", "
                    + answersFlowDTO.getUsers_IdColumnName() + ", "
                    + answersFlowDTO.getAnswersDateColumnName() + ", "
                    + answersFlowDTO.getLevelBeforePressurePointsColumnName() + ", "
                    + answersFlowDTO.getLevelAfterPressurePointsColumnName() + ", "
                    + answersFlowDTO.getGotBetterAfterColumnName() +
                    " FROM "
                    + answersFlowDTO.getTableName() +
                    " WHERE " + answersFlowDTO.getUsers_IdColumnName() + " = "
                    + userID +
                    " AND "
                    + answersFlowDTO.getAnswersDateColumnName() + " = '"
                    + answersDate + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                AnswersFlowDTO answersFlowDtoNames = new AnswersFlowDTO();
                SimpleDateFormat formatterDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                AnswersFlowDTO answersFlowDTOData = new AnswersFlowDTO(
                        rs.getInt(answersFlowDtoNames.getIdColumnName()),
                        rs.getInt(answersFlowDtoNames.getUsers_IdColumnName()),
                        rs.getDate(answersFlowDtoNames.getAnswersDateColumnName()),
                        rs.getInt(answersFlowDtoNames.getLevelBeforePressurePointsColumnName()),
                        rs.getInt(answersFlowDtoNames.getLevelAfterPressurePointsColumnName()),
                        rs.getBoolean(answersFlowDtoNames.getGotBetterAfterColumnName())
                );
                setMessageBoxText("Query executada com sucesso!");
                setSucess(true);
                con.close();
                return answersFlowDTOData;
            }else{
                setMessageBoxText("Query inválida!");
                setSucess(false);
                con.close();
                return null;
            }
        }
        catch (SQLException se)
        {
            setSucess(false);
            setMessageBoxText(se.getMessage());
            Log.d("sql error", getMessageBoxText());
            return null;
        }
    }

    public String getMessageBoxText() {
        return messageBoxText;
    }

    private void setMessageBoxText(String messageBoxText) {
        this.messageBoxText = messageBoxText;
    }

    public boolean isSucess() {
        return isSucess;
    }

    private void setSucess(boolean sucess) {
        isSucess = sucess;
    }
}
