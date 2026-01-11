package DataBase;

import java.sql.*;
public class OracleConnection implements DataBaseConnection {

    @Override
    public Connection getConnection() {
        DataBaseInfo dbi = Properties.getDataBaseInfo();
        if (dbi == null){
            return null;
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbi.getHost(),dbi.getUsername(),dbi.getPassword());
            System.out.println("Connected to Oracle database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Override
    public void closeConnection(Connection con) {
        try {
            if (con != null){
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeConnection(Connection con, ResultSet set) {
        try {
            if (con != null){
                con.close();
            }
            if (set != null)
                set.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeConnection(Connection con, ResultSet set, CallableStatement cs) {
        try {
            if (con != null){
                con.close();
            }
            if (set != null)
                set.close();
            if (cs != null)
                cs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void closeConnection(Connection con, CallableStatement cs) {
        try {
            if (con != null){
                con.close();
            }
            if (cs != null)
                cs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
