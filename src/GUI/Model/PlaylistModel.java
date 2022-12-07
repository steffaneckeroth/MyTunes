package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BE.Song;
import src.BLL.PlaylistManager;

public class PlaylistModel {

    private PlaylistManager playlistManager;
    private ObservableList<Playlist> playlistsToBeViewed;

    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }
    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistsToBeViewed;
    }

    public void createNewPlaylist(String playlistname) throws Exception {
        Playlist mPlaylist = playlistManager.createNewPlaylist(playlistname);
        playlistsToBeViewed.add(mPlaylist);

    }

}
