package serverSide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	  
    private static Connection con = null;
  
    static
    {
        String url = "jdbc:mysql://localhost/corona_db";
        String user = "root";
        String pass = "AyalaMysql20";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()//Singelton
    {
        return con;
    }
}