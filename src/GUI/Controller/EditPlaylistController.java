package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.GUI.Model.PlaylistModel;

public class EditPlaylistController extends BaseController {
    public TextField txtEditPlaylist;
    private PlaylistModel model;
    private Playlist selectedPlaylist;

    /**
     *  This method sets the model and selected playlist for the MyTunes class.
     * @param model
     * @param playlist
     */
    public void setModelMyTunes(PlaylistModel model, Playlist playlist){
        this.model=model;
        this.selectedPlaylist = playlist;
    }
    /**
     * This method updates the name of the selected playlist with the new name entered by the user.
     * @param event
     * @throws Exception
     */
    public void handleSaveEditPlaylist(ActionEvent event) throws Exception {
        String updatedName= txtEditPlaylist.getText();
        Playlist updatedPlaylist = new Playlist(selectedPlaylist.getPlaylistId(), updatedName);
        model.updatePlaylist(updatedPlaylist);
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method closes the window.
     * @param event
     */
    public void handleCancelEditPlaylist(ActionEvent event)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method fills a playlist with data from a Playlist object.
     * @param playlist
     * @throws Exception
     */
    public void fillPlaylistIN(Playlist playlist) throws Exception
    {
        model = getModel().getPlaylistModel();
        txtEditPlaylist.setText(playlist.getPlaylistName());
    }

    /**
     * This method sets the selected playlist to the Playlist object provided as an argument.
     * @param a
     */
    public void setSelectedPlaylist(Playlist a)
    {
        selectedPlaylist = a;
    }
}
