package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Song;

public class DeleteSongView {

    public Button btnOK;
    public Button btnCancel;
    private Node label;
    private Song selectedSong;
    public void handleOK(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }

    public void setup() {
        fillSongsIN();
    }

    private void fillSongsIN() {
    }

}




