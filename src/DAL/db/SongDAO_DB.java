package src.DAL.db;

import src.BE.Song;
import src.DAL.ISongDataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB implements ISongDataAccess {

    private DatabaseConnector databaseConnector;

    public SongDAO_DB()
    {
        databaseConnector = new DatabaseConnector();
    }

    /**
     * This method is getting all the songs from the database.
     * @return allSong
     * @throws Exception
     */
    public List<Song> getAllSong() throws Exception {

        ArrayList<Song> allSong = new ArrayList<>();
        // try-with-resources block to establish a connection to the database using a databaseConnector object.
        try (Connection conn = databaseConnector.getConnection())
        {
            // selecting everything from song.
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
                String filepath = rs.getString("FilePath");
                Time duration = rs.getTime("Duration");

                Song song = new Song(id, title, artist, category, filepath, duration);
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

    /**
     * This method is creating a new song in the database.
     * @param title
     * @param artist
     * @param category
     * @param filepath
     * @param duration
     * @return song
     * @throws Exception
     */
    public Song createSong(String title, String artist, String category, String filepath, Time duration) throws Exception
    {
        // making a new song where it has to have all the values
        String sql = "INSERT INTO Song (Title, Artist, Category, FilePath, Duration) VALUES (?,?,?,?,?);";

        try (Connection coon = databaseConnector.getConnection())
        {
            PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,title);
            stmt.setString(2, artist);
            stmt.setString(3, category);
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

    /**
     * This method is updating a song in the database.
     * @param song
     * @throws Exception
     */
    public void updateSongs(Song song) throws Exception
    {
        try (Connection conn = databaseConnector.getConnection()) {
            // it updates the title and artist of a song record in the database.
            String sql = "UPDATE Song SET Title = ?, Artist = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setInt(3, song.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update song", ex);
        }
    }

    /**
     * This method is deleting a song form the database.
     * @param song
     * @return song
     * @throws Exception
     */
    public Song deleteSongs(Song song) throws Exception {
        //Delete a song from the song table with the selected id.
        String sql = "DELETE FROM Song WHERE Id=?";

        try(Connection conn = databaseConnector.getConnection();){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, song.getId());

                int rowsDeleted = statement.executeUpdate();
                // It checks if there was a row that have been deletet, that means if it is less than 0.
                if (rowsDeleted > 0)
                {
                    System.out.println("Song was successfully deleted");
                }

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("could not delete song", ex);
        }
        return song;
    }

    public List<Song> searchSong(String query) throws Exception {

        //TODO Do this
        throw new UnsupportedOperationException();
    }
}
