package in.nawasrah.ecommercesystemAPI.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnection {
    Connection connection();

    void closeConnection() throws SQLException;
}