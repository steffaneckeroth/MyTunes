package src.DAL.db;


import src.BE.Song;
import src.DAL.ISongDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    private MySongDatabaseAccessController databaseConnector;

    public SongDAO_DB() {
        databaseConnector = new MySongDatabaseAccessController();
    }

    public List<Song> getAllSong() throws Exception {

        ArrayList<Song> allSong = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Song;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");

                Song song = new Song(id, title, artist, category);
                allSong.add(song);
            }
            return allSong;

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get songs from database", ex);
        }
    }

    public Song createSong(String title, String artist, String cetagory) throws Exception
    {

        String sql = "INSERT INTO Song (Title, ArtistId,) VALUES (?,?);";

        try (Connection coon = databaseConnector.getConnection())
        {
            PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,title);
            stmt.setString(2, artist);
            stmt.setString(3, cetagory);

            //Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }

            // Create movie object and send up the layers
            Song song = new Song(id, title, artist, cetagory);
            return song;
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get Song from database", ex);
        }
    }

    public void updateSong(Song song) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public void deleteSong(Song song) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public List<Song> searchSong(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }

}
