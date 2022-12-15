package src.BLL;

import src.BE.Playlist;
import src.BE.Song;
import src.DAL.db.ISongOnPlaylistDataAccess;
import src.DAL.db.SongOnPlaylistDAO;

import java.util.ArrayList;

public class SongOnPlaylistManager {

    private ISongOnPlaylistDataAccess songOnPlaylistDAO;

    public SongOnPlaylistManager() {
        songOnPlaylistDAO = new SongOnPlaylistDAO();
    }

    /**
     * This constructor initializes a new instance of the SongOnPlaylistManager class with the specified.
     * @param songOnPlaylistDAO
     */
    public SongOnPlaylistManager(ISongOnPlaylistDataAccess songOnPlaylistDAO) {this.songOnPlaylistDAO = songOnPlaylistDAO;}

    /**
     * This method adds a song to a given playlist and returns the added song.
     * @param playlist
     * @param song
     * @return songOnPlaylistDAO.addToPlaylist(playlist, song).
     * @throws Exception
     */
    public Song addToPlaylist(Playlist playlist, Song song) throws Exception {return songOnPlaylistDAO.addToPlaylist(playlist, song);}

    /**
     * This method retrieves a list of songs associated with a given playlist.
     * @param playlist
     * @return songOnPlaylistDAO.getSongsOnPlaylist(playlist)
     */
    // This method retrieves a list of songs associated with a given playlist.
    public ArrayList<Song> getSongsOnPlaylist(Playlist playlist )
    // Use the songOnPlaylistDAO object to retrieve the list of songs from the data source.
    {return songOnPlaylistDAO.getSongsOnPlaylist(playlist);}

    /**
     *The method takes two parameters: a Playlist object and a Song object.
     * It then uses the songOnPlaylistDAO object to call its deleteSongOnPlaylist method,
     * passing the playlist and song objects as arguments.
     * @param playlist
     * @param song
     */
    public void deleteSongOnPlaylist(Playlist playlist, Song song)
    {songOnPlaylistDAO.deleteSongOnPlaylist(playlist, song);}

}
