package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BLL.PlayListManager;
import src.GUI.Model.PlayListModel;

public class NewPlayListController {

    private PlayListModel playListModel;
    public Button btnSavePlayList;
    public Button btnCancelPlayList;
    public TextField txtfName;
    private PlayListManager playlistModel;

    public void handleButtonSavePlayList(ActionEvent actionEvent) {


        try {
            //Get data from textfields and combobox
            String name = txtfName.getText();

            //Calls createNewSong method from SongModel
            this.playlistModel.createNewPlayList(name);
            System.out.println("PLayList added: " + name);

            //Close stage if Save button is clicked
            Node source = (Node) actionEvent.getSource();
            Stage mStage = (Stage) source.getScene().getWindow();
            mStage.close();




        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add song");
        }


    }

    public void handleButtonCanclePlayList(ActionEvent actionEvent) {
        //Closes stage if Cancle button is clicked
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
