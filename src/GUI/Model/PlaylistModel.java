package src.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.BE.Playlist;
import src.BLL.PlaylistManager;

public class PlaylistModel {

    private PlaylistManager playlistManager;
    private Playlist selectedPlaylist;


    private ObservableList<Playlist> playlistsToBeViewed;

    /**
     * This method creates a new playlist model and initializes it with the PlaylistManger
     * @throws Exception
     */
    public PlaylistModel() throws Exception {
        playlistManager = new PlaylistManager();
        playlistsToBeViewed = FXCollections.observableArrayList();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    /**
     * This method returns an ObservableList of Playlist objects that can be observed and updated in real time.
     * @return playlistsToBeViewed
     */
    public ObservableList<Playlist> getObservablePlaylists() {
        return playlistsToBeViewed;
    }

    /**
     * This method creates a new playlist with the given name and adds it to the list of playlists to be viewed.
     * @param playlistname
     * @throws Exception
     */
    public void createNewPlaylist(String playlistname) throws Exception {
        Playlist mPlaylist = playlistManager.createNewPlaylist(playlistname);
        playlistsToBeViewed.add(mPlaylist);

    }

    /**
     * This method updates the playlist in the playlistManager and clears the
     * playlistToBeViewed before adding all the playlists.
     * @param updatedPlaylist
     * @throws Exception
     */
    public void updatePlaylist(Playlist updatedPlaylist) throws Exception {
        playlistManager.updatePlaylist(updatedPlaylist);
        playlistsToBeViewed.clear();
        playlistsToBeViewed.addAll(playlistManager.getAllPlaylists());
    }

    /**
     * This method is responsible for deleting a playlist from the playlist
     * manager and removing it from the list of playlists to be viewed.
     * @param deletedPlaylist
     * @throws Exception
     */
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


