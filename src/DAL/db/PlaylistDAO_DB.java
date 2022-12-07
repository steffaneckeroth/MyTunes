package src.DAL.db;

import src.BE.Playlist;
import src.BE.Song;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO_DB implements IPlaylistDataAccess {
    private DatabaseConnector databaseConnector;


    public PlaylistDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }


    public List<Playlist> getAllPlaylists() throws Exception {

        ArrayList<Playlist> allPlaylist = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM PlayList;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Id");
                String playlistname = rs.getString("Name");
                Playlist playlist = new Playlist(id, playlistname);
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
        String sql = "INSERT INTO PlayList (Name) VALUES (?);";

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
            // Create song object and send up the layers
            Playlist playlist = new Playlist(id, playlistname);
            return playlist;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not get Playlist from database", ex);
        }
    }


    @Override
    public Playlist updatePlaylist(Playlist playlist) throws Exception {
        return null;
    }

    @Override
    public Playlist deletePlaylist(Playlist playlist) throws Exception {
        return null;
    }


}
