package src.DAL.db;

import src.BE.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {
    private DatabaseConnector databaseConnector;

    private SongOnPlaylistDAO songOnPlaylistDAO;


    public PlaylistDAO_DB(){
        databaseConnector = new DatabaseConnector();
        songOnPlaylistDAO = new SongOnPlaylistDAO();
    }

    /**
     *
     * @return allPlaylist
     * @throws Exception
     */
    public List<Playlist> getAllPlaylists() throws Exception {

        ArrayList<Playlist> allPlaylist = new ArrayList<>();
        // try-with-resources block to establish a connection to the database using a databaseConnector object.
        try (Connection conn = databaseConnector.getConnection())
        {
            // selecting all form the playlist.
            String sql = "SELECT * FROM PlayList;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // Get the generated ID from DB
            while (rs.next()) {
                int id = rs.getInt("Id");
                String playlistname = rs.getString("Name");

                Playlist playlist = new Playlist(id, playlistname);
                playlist.setSongs(songOnPlaylistDAO.getSongsOnPlaylist(playlist));
                allPlaylist.add(playlist);
            }

            return allPlaylist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get playlists from database", ex);
        }
    }
    public Playlist createPlaylist(String playlistname) throws Exception
    {
        // inserting a new row into the table with the parameters as name.
        String sql = "INSERT INTO PlayList (Name) VALUES (?);";
        // try-with-resources block to establish a connection to the database using a databaseConnector object.
        try (Connection coon = databaseConnector.getConnection())
        {
            PreparedStatement stmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters
            stmt.setString(1,playlistname);


            //Run the specified SQL statement
            stmt.executeUpdate();

            // Get the generated ID from DB
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }
            // Create playlist object and send up the layers
            Playlist playlist = new Playlist(id, playlistname);
            return playlist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get Playlist from database", ex);
        }
    }
    /**
     *
     * @param playlist
     * @return playlist
     * @throws Exception
     */
    public Playlist deletePlaylist(Playlist playlist) throws Exception {
        //deleting from the playlist with a specific id
        String sql = "DELETE FROM PlayList WHERE Id=?";
        // try-with-resources block to establish a connection to the database using a databaseConnector object.
        try(Connection conn = databaseConnector.getConnection();){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlist.getPlaylistId());
            // Check the number of rows affected by the DELETE operation.
            int rowsDeleted = statement.executeUpdate();
            // If at least one row was deleted, print a success message.
            if (rowsDeleted > 0)
            {
                System.out.println("Song was successfully deleted");
            }

        } catch (SQLException ex){
            ex.printStackTrace();
            throw new Exception("could not delete playlist", ex);
        }
        return playlist;
    }

    /**
     *
     * @param playlist
     * @throws Exception
     */
    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {
        try (Connection conn = databaseConnector.getConnection()) {
            //updating the name of a playlist
            String sql = "UPDATE PlayList SET Name = ? WHERE Id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Bind parameters
            stmt.setString(1,playlist.getPlaylistName() );
            stmt.setInt(2,playlist.getPlaylistId() );

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Could not update playlist", ex);
        }
    }
}
