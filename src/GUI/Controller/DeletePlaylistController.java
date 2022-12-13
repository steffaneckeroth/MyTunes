package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;
import src.BE.Playlist;
import src.GUI.Model.PlaylistModel;

import java.net.URL;
import java.util.ResourceBundle;

public class DeletePlaylistController extends BaseController implements Initializable {

    private PlaylistModel playlistModel;
    private Playlist selectedPlaylist;
    private SongViewController songViewController;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void yesDeletePlaylist(ActionEvent event) throws Exception {

        //Playlist deletedPlaylist = songViewController.tblPlaylist.getSelectionModel().getSelectedItem();
        //playlistModel.deletePlaylist(deletedPlaylist);
        //songViewController.tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        throw new ExecutionControl.NotImplementedException("");
    }
    public void noDeletePlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


}
