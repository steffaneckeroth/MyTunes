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

    /**
     * This method sets the model and selected playlist for the MyTunes application.
     * @param model
     * @param playlist
     */
    public void setModelMyTunes(PlaylistModel model, Playlist playlist){
        this.model=model;
        this.selectedPlaylisst = playlist;
    }

    /**
     * This method handles if you want to save the playlist.
     * @param actionEvent
     */
    public void handleButtonSavePlaylist(ActionEvent actionEvent) {

        try {
            //Get data from textfields and combobox
            String playlistname = txtfName.getText();

            //Calls createNewSong method from SongModel
            this.model.createNewPlaylist(playlistname);
            System.out.println("PLayList added: " + playlistname);
            // This code closes the current window by getting a reference to the stage
            // and calling the close() method.
            Node source = (Node) actionEvent.getSource();
            Stage mStage = (Stage) source.getScene().getWindow();
            mStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add song");
        }
    }

    /**
     * This method closes the window.
     * @param actionEvent
     */
    public void handleButtonCanclePlaylist(ActionEvent actionEvent) {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
