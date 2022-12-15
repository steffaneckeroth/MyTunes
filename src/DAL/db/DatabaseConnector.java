package src.DAL.db;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private SQLServerDataSource dataSource;

    /**
     * Constructor for the databaseconnector class.
     * It sets up a new SQLServerDataSource object and then it assigns it to the datasource field.
     */
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

    /**
     * This method gets a connecton to the database and if it cant, then it will throw a sqlserverexeption.
     * @return dataSource.getConnection().
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {return dataSource.getConnection();}

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        // Create a new DatabaseConnector object
        DatabaseConnector databaseConnector = new DatabaseConnector();
        // Get a connection to the database using the DatabaseConnector
        // This uses a try-with-resources block, so the connection will be automatically closed
        // when the block is finished executing
        try (Connection connection = databaseConnector.getConnection()) {
            // Print whether the connection is open or closed
            System.out.println("Is it open? " + !connection.isClosed());
        }
    }
}
