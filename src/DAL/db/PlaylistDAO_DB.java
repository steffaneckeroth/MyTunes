package src.DAL.db;

import src.BE.Playlist;
import src.BE.Song;


import javax.xml.crypto.Data;
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

    public Playlist deletePlaylist(Playlist playlist) throws Exception {
        String sql = "DELETE FROM PlayList WHERE Id=?";

        try(Connection conn = databaseConnector.getConnection();){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, playlist.getPlaylistId());
            int rowsDeleted = statement.executeUpdate();
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

    @Override
    public void updatePlaylist(Playlist playlist) throws Exception {
        try (Connection conn = databaseConnector.getConnection()) {

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
