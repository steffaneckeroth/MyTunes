package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.GUI.Model.PlaylistModel;


public class DeletePlaylistController extends BaseController {
    private Playlist selectedPlaylist;
    private PlaylistModel model;


    /**
     * This method sets the specified PlaylistModel and Playlist as the current model and selected playlist, respectively.
     * @param model
     * @param playlist
     */
    public void setModelMyTunes(PlaylistModel model, Playlist playlist)
    {
        this.model=model;
        this.selectedPlaylist = playlist;
    }

    /**
     * The deletePlaylist method is called on the model, passing in the selected playlist object.
     * @param event
     * @throws Exception
     */
    public void yesDeletePlaylist(ActionEvent event) throws Exception
    {
        model.deletePlaylist(selectedPlaylist);
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method closes the window
     * @param event
     */
    public void noDeletePlaylist(ActionEvent event)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
