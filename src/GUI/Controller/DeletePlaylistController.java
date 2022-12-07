package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
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
        try {
            playlistModel=new PlaylistModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void yesDeletePlaylist(ActionEvent event) throws Exception {
        Playlist deletedPlaylist = songViewController.tblPlaylist.getSelectionModel().getSelectedItem();
        playlistModel.deletePlaylist(deletedPlaylist);
        songViewController.tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void noDeletePlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setSelectedPlaylist(Playlist p)
    {
        selectedPlaylist = p;
    }

    private void fillSongsIN() throws Exception {
        playlistModel = getModel().getPlaylistModel();
    }
    public void setController(SongViewController songViewController) {
        this.songViewController=songViewController;
    }
    @Override
    public void setup() throws Exception {

    }
}
