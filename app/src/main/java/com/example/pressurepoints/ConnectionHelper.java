package com.example.pressurepoints;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHelper {

    public ConnectionHelper() {
    }

    @SuppressLint("NewApi")
    public Connection connectionMethod(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            // The database connection string goes below
            ConnectionURL = "jdbc:jtds:sqlserver://yourDataBaseRepository.database.windows.net:1433/yourDatabaseName;user=yourUserAccountName;password=YourUserAccountPassword;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch (SQLException se){
            Log.e("error here 1 : ", se.getMessage());
        }catch (ClassNotFoundException e){
            Log.e("error here 2 : ", e.getMessage());
        }catch (Exception e){
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
