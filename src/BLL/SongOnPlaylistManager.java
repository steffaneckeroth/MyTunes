package src.BLL;

import src.BE.Playlist;
import src.BE.Song;
import src.DAL.db.ISongOnPlaylistDataAccess;
import src.DAL.db.SongOnPlaylistDAO;

public class SongOnPlaylistManager {
    private ISongOnPlaylistDataAccess songOnPlaylistDAO;
    public SongOnPlaylistManager() {
        songOnPlaylistDAO = new SongOnPlaylistDAO();
    }

    public SongOnPlaylistManager(ISongOnPlaylistDataAccess songOnPlaylistDAO) {
        this.songOnPlaylistDAO = songOnPlaylistDAO;
    }


    public Song addToPlaylist(Playlist playlist, Song song) throws Exception

    {
        return songOnPlaylistDAO.addToPlaylist(String.valueOf(playlist), song);
    }


}
