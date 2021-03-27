package DAO;

import android.util.Log;

import com.example.pressurepoints.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import DTO.AnswersFlowDTO;
import DTO.UsersDTO;

public class UsersDAO {

    private String messageBoxText = "";
    private boolean isSucess = false;

    public UsersDAO() {
    }

    public UsersDTO getUsersDTOExecuted(String query){
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
                        UsersDTO usDtoNames = new UsersDTO();
                        SimpleDateFormat formatterDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                        UsersDTO usDtoData = new UsersDTO(
                                Integer.parseInt(rs.getString(usDtoNames.getIdColumnName())),
                                rs.getString(usDtoNames.getUserNameColumnName()),
                                rs.getString(usDtoNames.getPasswordColumnName()),
                                rs.getString(usDtoNames.getConfirmPasswordColumnName()),
                                rs.getString(usDtoNames.getGenreColumnName()).charAt(0),
                                Integer.parseInt(rs.getString(usDtoNames.getAgeColumnName())),
                                rs.getString(usDtoNames.getHeightColumnName()),
                                rs.getString(usDtoNames.getWeight()),
                                rs.getString(usDtoNames.getChronicDiseaseObsColumnName())
                        );
                        setMessageBoxText("Query executada com sucesso!");
                        setSucess(true);
                        con.close();
                        return usDtoData;
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

    public UsersDTO selectUserDTO(String UserNameFromView, String PasswordFromView){
        try
        {
            UsersDTO userDTO = new UsersDTO();
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            String query = "SELECT "
                    + userDTO.getIdColumnName() + ", "
                    + userDTO.getUserNameColumnName() + ", "
                    + userDTO.getPasswordColumnName() + ", "
                    + userDTO.getConfirmPasswordColumnName() + ", "
                    + userDTO.getGenreColumnName() + ", "
                    + userDTO.getAgeColumnName() + ", "
                    + userDTO.getHeightColumnName() + ", "
                    + userDTO.getWeightColumnName() + ", "
                    + userDTO.getChronicDiseaseObsColumnName() +
                    " FROM "
                    + userDTO.getTableName() +
                    " WHERE " + userDTO.getUserNameColumnName() + " = '"
                    + UserNameFromView + "'" +
                    " AND "
                    + userDTO.getPasswordColumnName() + " = '"
                    + PasswordFromView + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                UsersDTO usDtoNames = new UsersDTO();
                SimpleDateFormat formatterDate =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                UsersDTO usDtoData = new UsersDTO(
                        rs.getInt(usDtoNames.getIdColumnName()),
                        rs.getString(usDtoNames.getUserNameColumnName()),
                        rs.getString(usDtoNames.getPasswordColumnName()),
                        rs.getString(usDtoNames.getConfirmPasswordColumnName()),
                        rs.getString(usDtoNames.getGenreColumnName()).charAt(0),
                        rs.getInt(usDtoNames.getAgeColumnName()),
                        rs.getString(usDtoNames.getHeightColumnName()),
                        rs.getString(usDtoNames.getWeightColumnName()),
                        rs.getString(usDtoNames.getChronicDiseaseObsColumnName())
                );
                setMessageBoxText("Query executada com sucesso!");
                setSucess(true);
                con.close();
                return usDtoData;
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

    public void insertUserDTO(UsersDTO userDTO){
        try
        {
            ConnectionHelper ch = new ConnectionHelper();
            Connection con = ch.connectionMethod();
            String query = "INSERT INTO " + userDTO.getTableName()
                    +"(" + userDTO.getUserNameColumnName() + ", "
                    + userDTO.getPasswordColumnName() + ", "
                    + userDTO.getConfirmPasswordColumnName() + ", "
                    + userDTO.getGenreColumnName() + ", "
                    + userDTO.getAgeColumnName() + ", "
                    + userDTO.getHeightColumnName() + ", "
                    + userDTO.getWeightColumnName() + ", "
                    + userDTO.getChronicDiseaseObsColumnName() + ")" +
                    "VALUES ('" + userDTO.getUserName() + "', '"
                    + userDTO.getPassword() + "', '"
                    + userDTO.getConfirmPassword() + "', '"
                    + userDTO.getGenre() + "', "
                    + userDTO.getAge() + ", '"
                    + userDTO.getHeight() + "', '"
                    + userDTO.getWeight() + "', '"
                    + userDTO.getChronicDiseaseObs() + "'"
                    +")" ;
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
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
