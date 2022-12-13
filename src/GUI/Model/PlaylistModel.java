package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BLL.PlaylistManager;

public class PlaylistModel {

    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;


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

    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistManager.updatePlaylist(updatedPlaylist);
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    public void deletePlaylist(Playlist deletedPlaylist) throws Exception {
        playlistManager.deletePlaylist(deletedPlaylist);
        playlistsToBeViewed.remove(deletedPlaylist);
    }
    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }
    public void setSelectedPlaylist(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }
}


