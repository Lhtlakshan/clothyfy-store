package util;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String SQL , Object... val) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(SQL);

        if (val != null) {
            for (int i = 0; i < val.length; i++) {
                stmt.setObject(i + 1, val[i]); // JDBC indices start at 1
            }
        }
        if(SQL.startsWith("SELECT") || SQL.startsWith("select")){
            return (T) stmt.executeQuery();
        }else {
            return (T) (Boolean) (stmt.executeUpdate()>0);
        }
    }
}