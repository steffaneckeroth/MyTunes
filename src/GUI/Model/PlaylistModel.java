package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BLL.PlaylistManager;

public class PlaylistModel {

    private PlaylistManager playlistManager;
    private ObservableList<Playlist> playlistToBeViewed;

    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        ObservableList<Playlist> playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistToBeViewed;
    }

    public void createNewPlaylist(String playlistname) throws Exception {

        Playlist mPlaylist = playlistManager.createNewPlaylist(playlistname);
        playlistToBeViewed.add(mPlaylist);

    }

}
