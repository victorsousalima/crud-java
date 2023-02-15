package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = lerProperties();
                String url = props.getProperty("dburl");
                String user = props.getProperty("dbuser");
                String password = props.getProperty("dbpassword");
                return DriverManager.getConnection(url, user, password);
            }
            catch (SQLException | IOException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties lerProperties() throws IOException {
        Properties props = new Properties();
        String path = "db.properties";
        props.load(new FileInputStream(path));
        return props;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
