package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.GUI.Model.PlaylistModel;


public class DeletePlaylistController extends BaseController {
    private Playlist selectedPlaylist;
    private PlaylistModel model;

    public void setModelMyTunes(PlaylistModel model, Playlist playlist)
    {
        this.model=model;
        this.selectedPlaylist = playlist;
    }
    public void yesDeletePlaylist(ActionEvent event) throws Exception
    {
        model.deletePlaylist(selectedPlaylist);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void noDeletePlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
