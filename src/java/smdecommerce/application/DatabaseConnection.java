/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smdecommerce.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ivalm
 */
public class DatabaseConnection {
    
    private Connection con = null;
    
    private String hostName = null;
    private String userName = null;
    private String password = null;
    private String postgresDriver = null;
    private String dataBaseName = null;
    private String dataBasePrefix = null;
    private String dataBasePort = null;
    
    private String url = null;
    
    public DatabaseConnection () {
        super();

        hostName = "localhost";
        userName = "postgres";
        password = "ufc123";
        postgresDriver = "org.postgresql.Driver";
        dataBaseName = "smdecommerce";
        dataBasePrefix = "jdbc:postgresql://";
        dataBasePort = "5432";
        
        url = dataBasePrefix + hostName + ":" + dataBasePort + "/" +  dataBaseName;
    }
    
    public Connection getConnection(){
        try{
            if(con == null){
                Class.forName(postgresDriver);
                con = DriverManager.getConnection(url, userName, password);
            } else if (con.isClosed()){
                con = null;
                return getConnection();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return con;
    }
    
    public void closeConnection(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
