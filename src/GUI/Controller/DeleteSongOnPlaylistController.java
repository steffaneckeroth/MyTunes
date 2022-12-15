package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.BE.Song;
import src.GUI.Model.SongOnPlaylistModel;

public class DeleteSongOnPlaylistController extends BaseController{
    private Song selectedSong;
    private SongOnPlaylistModel model;
    private Playlist playlist;

    /**
     * This method sets the SongOnPlaylistModel, selectedSong, and playlist
     * properties of the current object using the provided model, song, and playlist arguments.
     * @param model
     * @param song
     * @param playlist
     */
    public void setModelMyTunes(SongOnPlaylistModel model, Song song, Playlist playlist)
    {
        this.model=model;
        this.selectedSong=song;
        this.playlist = playlist;
    }

    /**
     * The deletesongonplaylist method is called on the model, passing in the selected song object.
     * @param event
     * @throws Exception
     */
    public void handleButtonYesDeleteSongOnPlaylist(ActionEvent event) throws Exception
    {
        model.deleteSongOnPlaylist(playlist,selectedSong);
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method closes the window.
     * @param event
     */
    public void handleButtonNoDeleteSongOnPlaylist(ActionEvent event)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
