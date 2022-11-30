package src.DAL.db;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private SQLServerDataSource dataSource;

    public DatabaseConnector()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("EASV_MYTUNES_2022");
        dataSource.setUser("CSe22A_9");
        dataSource.setPassword("CSe22A_9");
        dataSource.setTrustServerCertificate(true);
        // dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
    public static void main(String[] args) throws SQLException {

        DatabaseConnector databaseConnector = new DatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());

        } //Connection gets closed here
    }

}
