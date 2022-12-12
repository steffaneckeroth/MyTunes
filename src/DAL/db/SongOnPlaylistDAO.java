package src.DAL.db;

import src.BE.Playlist;
import src.BE.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongOnPlaylistDAO implements ISongOnPlaylistDataAccess {

    private DatabaseConnector databaseConnector;

    public SongOnPlaylistDAO() {
        this.databaseConnector = new DatabaseConnector();
    }

    @Override
    public Song addToPlaylist(Playlist playlist, Song song) throws Exception {
        String sql = "INSERT INTO SongOnPlayList (SongId, PlayListId) VALUES (?,?);";
        try (Connection con = databaseConnector.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, song.getId());
            stmt.setInt(2, playlist.getPlaylistId());


            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }

            return song;
        } catch (SQLException ex)
        {
            return null;
        }
    }

    @Override
    public List<Playlist> getAllSongOnPlaylists() {
        return null;
    }

    @Override
    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist) {
        return null;
    }
}
