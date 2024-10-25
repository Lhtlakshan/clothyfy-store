package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;
    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clothify_store", "root", "Thilina");
    }
    public Connection getConnection(){
        return connection;
    }
    public static DbConnection getInstance() throws SQLException {
        return null==instance?instance=new DbConnection():instance;
    }
}
