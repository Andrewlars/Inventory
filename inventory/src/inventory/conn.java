package inventory;

import java.sql.*;

public class conn {

    private static final String URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ResultSet query(String sql) {
        try {
            Connection conn = connect();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                return stmt.executeQuery(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
