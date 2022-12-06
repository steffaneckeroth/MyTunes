package src.DAL.db;

import src.BE.PlayList;

import java.sql.*;

public class PlayListDAO_DB implements IPlayListDataAccess{
    private DatabaseConnector databaseConnector;

    public PlayList createPlayList(String name) throws Exception
    {
        String sql = "INSERT INTO PlayList (Name) VALUES (?);";

        try (Connection coon = databaseConnector.getConnection())
        {
            PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,name);


            //Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }
            // Create song object and send up the layers
            PlayList playlist = new PlayList(id, name);
            return playlist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get PlayList from database", ex);
        }
    }



}
