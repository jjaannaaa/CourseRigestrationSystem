package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DataBaseConnection {
    Connection getConnection();
    void closeConnection(Connection con);
    void closeConnection(Connection con, ResultSet set);
}
