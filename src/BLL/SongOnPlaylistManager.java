package src.BLL;

import src.BE.Playlist;
import src.BE.Song;
import src.BE.SongOnPlaylist;
import src.DAL.db.ISongOnPlaylistDataAccess;
import src.DAL.db.SongOnPlaylistDAO;

import java.util.ArrayList;
import java.util.List;

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
        return songOnPlaylistDAO.addToPlaylist(playlist, song);
    }

    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist ) {
        return songOnPlaylistDAO.getSongsOnPlaylist(playlist);
    }
    public void deleteSongOnPlaylist(Playlist playlist, Song song)
    {
        songOnPlaylistDAO.deleteSongOnPlaylist(playlist, song);
    }

}
