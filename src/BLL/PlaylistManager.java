
package src.BLL;

import src.BE.Playlist;
import src.BE.Song;
import src.DAL.db.IPlaylistDataAccess;
import src.DAL.db.PlaylistDAO_DB;

import java.util.List;

public class PlaylistManager {
    private IPlaylistDataAccess playlistDAO;

    public PlaylistManager() {
        playlistDAO = new PlaylistDAO_DB();
    }
    public Playlist createNewPlaylist (String playlistname) throws Exception {
        return playlistDAO.createPlaylist(playlistname);
    }
    public List<Playlist> getAllPlaylists() throws Exception {
        return playlistDAO.getAllPlaylists();
    }
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistDAO.updatePlaylist(updatedPlaylist);
    }

    public void deletePlaylist(Playlist deletedPlaylist) throws Exception{
        playlistDAO.deletePlaylist(deletedPlaylist);
    }

    public static void addToPlaylist(Playlist playlist, Song song) throws Exception{


    }
}
