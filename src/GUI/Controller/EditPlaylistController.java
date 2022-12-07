package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.GUI.Model.PlaylistModel;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPlaylistController extends BaseController implements Initializable {
    public TextField txtEditPlaylist;

    private SongViewController songViewController;
    private PlaylistModel model;
    private Playlist selectedPlaylist;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            model=new PlaylistModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void handleSaveEditPlaylist(ActionEvent event) throws Exception {
        String updatedName= txtEditPlaylist.getText();

        Playlist updatedPlaylist = new Playlist(selectedPlaylist.getPlaylistId(), updatedName);

        this.model.updatePlaylist(updatedPlaylist);
        songViewController.tblPlaylist.setItems(this.model.getObservablePlaylists());

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void fillPlaylistIN(Playlist playlist) throws Exception
    {
        txtEditPlaylist.setText(playlist.getPlaylistName());
    }
    public void setSelectedPlaylist(Playlist a)
    {
        selectedPlaylist = a;
    }
    public void handleCancelEditPlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setController(SongViewController songViewController) {
        this.songViewController=songViewController;
    }
    @Override
    public void setup() throws Exception {

    }
}
