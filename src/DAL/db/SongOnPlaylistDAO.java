package src.DAL.db;

import src.BE.Playlist;
import src.BE.Song;
import java.sql.*;
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
    public List<Playlist> getAllSongOnPlaylists() throws Exception {

        return null;
    }

    @Override
    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist) {

        ArrayList<Song> allSongOnPlaylist = new ArrayList<>();

        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Song\n" +
                         "JOIN SongOnPlaylist ON SongOnPlaylist.SongId = Song.Id\n" +
                         "JOIN PlayList ON PlayListId = PlayList.Id\n" +
                         "WHERE PlayListId = ?;";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, playlist.getPlaylistId());
            ResultSet rs = pstmt.executeQuery();

            // Loop through rows from the database result set
            while (rs.next()) {

                int id = rs.getInt("Id");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                String filepath = rs.getString("FilePath");
                Time duration = rs.getTime("Duration");

                Song song = new Song(id, title, artist, category, filepath, duration);
                allSongOnPlaylist.add(song);
            }
            return allSongOnPlaylist;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return allSongOnPlaylist;
    }
}
