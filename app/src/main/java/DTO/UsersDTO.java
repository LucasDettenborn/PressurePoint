package DTO;

import android.widget.EditText;

import com.example.pressurepoints.R;

public class UsersDTO {
    private int Id;
    private String UserName;
    private String Password;
    private String ConfirmPassword;
    private char Genre;
    private int Age;
    private String Height;
    private String Weight;
    private String ChronicDiseaseObs;

    public UsersDTO() {
    }

    public UsersDTO(int id, String userName, String password, String confirmPassword, char genre, int age, String height, String weight, String chronicDiseaseObs) {
        Id = id;
        UserName = userName;
        Password = password;
        ConfirmPassword = confirmPassword;
        Genre = genre;
        Age = age;
        Height = height;
        Weight = weight;
        ChronicDiseaseObs = chronicDiseaseObs;
    }

    public UsersDTO(String userName, String password, String confirmPassword, char genre, int age, String height, String weight, String chronicDiseaseObs) {
        UserName = userName;
        Password = password;
        ConfirmPassword = confirmPassword;
        Genre = genre;
        Age = age;
        Height = height;
        Weight = weight;
        ChronicDiseaseObs = chronicDiseaseObs;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIdColumnName() {
        return "Id";
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNameColumnName() {
        return "UserName";
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPasswordColumnName() {
        return "Password";
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getConfirmPasswordColumnName() {
        return "ConfirmPassword";
    }

    public char getGenre() {
        return Genre;
    }

    public void setGenre(char genre) {
        Genre = genre;
    }

    public String getGenreColumnName() {
        return "Genre";
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getAgeColumnName() {
        return "Age";
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getHeightColumnName() {
        return "Height";
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getWeightColumnName() {
        return "Weight";
    }

    public String getChronicDiseaseObs() {
        return ChronicDiseaseObs;
    }

    public void setChronicDiseaseObs(String chronicDiseaseObs) {
        ChronicDiseaseObs = chronicDiseaseObs;
    }

    public String getChronicDiseaseObsColumnName() {
        return "ChronicDiseaseObs";
    }

    public String getTableName(){
        return this.getClass().getName().replace("DTO", "");
    }

    public static String checkRequiredFields(String userName, char userGenreRegister, String userPasswordRegister, String userConfirmPasswordRegister, String userChronicDiseaseObsRegister, int userAgeRegister, String userHeightRegister, String userWeightRegister){
        //Put here the validation about fields
        return "";
    }
}
