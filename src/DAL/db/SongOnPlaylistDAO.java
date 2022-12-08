package src.DAL.db;

import src.BE.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongOnPlaylistDAO implements ISongOnPlaylistDataAccess {

    private DatabaseConnector databaseConnector;

    public SongOnPlaylistDAO() {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public Song addToPlaylist(String name, Song song) throws Exception {
        String sql = "INSERT INTO SongOnPlayList (Titel) VALUES (?);";
        try (Connection con = databaseConnector.getConnection())
        {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, song.getId());


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
}
