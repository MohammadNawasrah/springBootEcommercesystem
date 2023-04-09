package in.nawasrah.ecommercesystemAPI.database.sql;

import in.nawasrah.ecommercesystemAPI.database.connection.DbConnectionImp;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHandler {

    DbConnectionImp dbConnectionImp;

    public SqlHandler(String stringConnection) throws SQLException {
        this.dbConnectionImp = new DbConnectionImp(stringConnection);
    }

    public boolean tableExists(String tableName) throws SQLException {
        try {
            DatabaseMetaData databaseMetaData = dbConnectionImp.connection().getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String createTable(String tableName, String columns) throws SQLException {

        if (!tableExists(tableName)) {
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")";
            try {
                Statement stmt = dbConnectionImp.connection().createStatement();
                stmt.executeUpdate(query);
                return "Done create Table Name " + tableName;
            } catch (SQLException e) {
                return "error";
            }
        }
        return "table has already exist";

    }

    public void insertData(String tableName, String values) throws SQLException {
        String query = "INSERT INTO " + tableName + " VALUES (" + values + ")";
        try (Statement stmt = dbConnectionImp.connection().createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public ResultSet selectData(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        try {
            Statement statement = dbConnectionImp.connection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResultSet selectDataWhere(String tableName, String where, Object value) throws SQLException {
        String query = "", format = "";
        if (value.getClass().toString().equals("class java.lang.Integer")) {
            query = "SELECT * FROM %s WHERE %s = %d";
            format = String.format(query, tableName, where, value);
        }
        if (value.getClass().toString().equals("class java.lang.String")) {
            query = "SELECT * FROM %s WHERE %s = \'%s\'";
            format = String.format(query, tableName, where, value);
        }
        try {
            Statement statement = dbConnectionImp.connection().createStatement();
            ResultSet resultSet = statement.executeQuery(format);
            return resultSet;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
