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

    public void setModelMyTunes(PlaylistModel model, Playlist playlist){
        this.model=model;
        this.selectedPlaylist = playlist;
    }
    public void handleSaveEditPlaylist(ActionEvent event) throws Exception {
        String updatedName= txtEditPlaylist.getText();
        Playlist updatedPlaylist = new Playlist(selectedPlaylist.getPlaylistId(), updatedName);
        model.updatePlaylist(updatedPlaylist);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleCancelEditPlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void fillPlaylistIN(Playlist playlist) throws Exception
    {
        model = getModel().getPlaylistModel();
        txtEditPlaylist.setText(playlist.getPlaylistName());
    }
    public void setSelectedPlaylist(Playlist a)
    {
        selectedPlaylist = a;
    }
}
