package DataBase;

import java.sql.Connection;

public interface DataBaseConnection {
    Connection getConnection();
    void closeConnection(Connection con);
}
