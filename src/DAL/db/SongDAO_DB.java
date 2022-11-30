package src.DAL.db;
import src.BE.Artist;
import src.BE.Category;
import src.BE.Song;
import src.DAL.ISongDataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB() {
        databaseConnector = new DatabaseConnector();
    }

    public List<Song> getAllSong() throws Exception {

        ArrayList<Song> allSong = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Song INNER JOIN Category ON Song.CategoryId = Category.Id INNER JOIN Artist ON Song.ArtistId = Artist.Id";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to Song object
                int id = rs.getInt(1);
                String title = rs.getString(2);
                int artist = rs.getInt(3);
                int category = rs.getInt(4);
                String filepath = rs.getString(5);
                Time duration = rs.getTime(6);
                String artistName = rs.getString(10);
                String categoryName = rs.getString(8);
                Artist artist1 = new Artist(artist,artistName);
                Category category1 = new Category(category, categoryName);


                Song song = new Song(id, title, artist1, category1, filepath, duration);
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

    public Song createSong(String title, Artist artist, Category category, String filepath, Time duration) throws Exception
    {
        String sql = "INSERT INTO Song (Title, ArtistId, CategoryId, FilePath, Duration) VALUES (?,?,?,?,?);";

        try (Connection coon = databaseConnector.getConnection())
        {
            PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,title);
            stmt.setString(2, String.valueOf(artist));
            stmt.setString(3, String.valueOf(category));
            stmt.setString(4, filepath);
            stmt.setTime(5, duration);


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
            Song song = new Song(id, title, artist, category, filepath, duration);
            return song;
        }

        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get Song from database", ex);
        }
    }

    public void updateSongs(Song song) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public void deleteSongs(Song song) throws Exception {
        //TODO Do this
        throw new UnsupportedOperationException();
    }

    public List<Song> searchSong(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }
}
