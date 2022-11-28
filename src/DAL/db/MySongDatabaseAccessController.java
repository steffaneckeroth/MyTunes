package src.DAL.db;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class MySongDatabaseAccessController {

    private SQLServerDataSource dataSource;

    public MySongDatabaseAccessController()
    {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MyTuunes");
        dataSource.setUser("CSe22A_25");
        dataSource.setPassword("CSe22A_25");
        dataSource.setTrustServerCertificate(true);
        // dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
    public static void main(String[] args) throws SQLException {

        MySongDatabaseAccessController databaseConnector = new MySongDatabaseAccessController();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());

        } //Connection gets closed here
    }

}
