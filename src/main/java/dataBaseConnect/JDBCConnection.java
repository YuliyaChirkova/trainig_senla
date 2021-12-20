package dataBaseConnect;

import utils.Log;

import java.sql.*;

public class JDBCConnection {

    private static final String HOST = "86.57.161.116";
    private static final String DB_NANE = "register_office";
    private static final String SCHEMA_NAME = "reg_office";
    private static final String URL = "jdbc:postgresql://" + HOST + ":50432/" + DB_NANE + "?currentSchema=" + SCHEMA_NAME;
    private static final String user = "user";
    private static final String PASSWORD = "user_senla";

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static Connection connectToDB() {
        Log.info("Connect to DB " + URL + " by user " + user);

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, user, PASSWORD);
            Log.info("Connection to DB successful!");
        } catch (ClassNotFoundException e) {
            Log.error(e.getMessage());
        } catch (SQLException sqlEx) {
            Log.error("Connection to DB failed!\n" + sqlEx.getMessage());
        }

        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                Log.info("Connection to DB closed successfully");
            } catch (SQLException se) {
                Log.error("Connection to DB was not closed. Reason:\n" + se.getMessage());
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
                Log.info("Statement closed successfully");
            } catch (SQLException se) {
                Log.error("Statement was not closed. Reason:\n" + se.getMessage());
            }
        }

        if (rs != null) {
            try {
                rs.close();
                Log.info("ResultSet closed successfully");
            } catch (SQLException se) {
                Log.error("ResultSet was not closed. Reason:\n" + se.getMessage());
            }
        }
    }

    public static ResultSet selectFromTable(String query) {
        try {
            stmt = connectToDB().createStatement();
            Log.info("Send request to DB: " + query);
            rs = stmt.executeQuery(query);
            rs.next();

        } catch (SQLException se) {
            Log.error(se.getMessage());
        }
        return rs;
    }

    public static int deleteFromTable(String query) {
        int row=0;
        try {
            Log.info("Send request to DB: " + query);
            stmt = connectToDB().createStatement();
            row= stmt.executeUpdate(query);
            Log.info("Data from table was deleted successfully");
        } catch (SQLException se) {
            Log.error("Data from table was not deleted. Reason:\n" + se.getMessage());
        }
        return row;
    }

}
