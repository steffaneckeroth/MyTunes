package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.GUI.Model.PlaylistModel;
import src.BE.Playlist;




public class NewPlaylistController extends BaseController {
    public Button btnSavePlaylist;
    public Button btnCancelPlaylist;
    public TextField txtfName;


    private PlaylistModel model;
    private Playlist selectedPlaylisst;

    public void setModelMyTunes(PlaylistModel model, Playlist playlist){
        this.model=model;
        this.selectedPlaylisst = playlist;
    }
    public void handleButtonSavePlaylist(ActionEvent actionEvent) {

        try {
            //Get data from textfields and combobox
            String playlistname = txtfName.getText();

            //Calls createNewSong method from SongModel
            this.model.createNewPlaylist(playlistname);
            System.out.println("PLayList added: " + playlistname);
            //Close stage if Save button is clicked
            Node source = (Node) actionEvent.getSource();
            Stage mStage = (Stage) source.getScene().getWindow();
            mStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add song");
        }
    }

    public void handleButtonCanclePlaylist(ActionEvent actionEvent) {
        //Closes stage if Cancle button is clicked
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
