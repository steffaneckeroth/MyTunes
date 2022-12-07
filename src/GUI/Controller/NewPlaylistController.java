package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BLL.PlaylistManager;
import src.GUI.Model.PlaylistModel;


public class NewPlaylistController extends BaseController {

    private PlaylistModel playlistModel;
    public Button btnSavePlaylist;
    public Button btnCancelPlaylist;
    public TextField txtfName;

    private PlaylistManager playlistManager;
    private SongViewController songViewController;

    public NewPlaylistController()
    {
        try {
            playlistModel = new PlaylistModel();
            songViewController = new SongViewController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void handleButtonSavePlaylist(ActionEvent actionEvent) {

        try {
            //Get data from textfields and combobox
            String playlistname = txtfName.getText();

            //Calls createNewSong method from SongModel
            this.playlistModel.createNewPlaylist(playlistname);
            System.out.println("PLayList added: " + playlistname);
            songViewController.tblPlaylist.setItems(playlistModel.getObservablePlaylists());

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

    @Override
    public void setup() throws Exception {
    }
    public void setController(SongViewController songViewController)
    {
        this.songViewController=songViewController;
    }
}
