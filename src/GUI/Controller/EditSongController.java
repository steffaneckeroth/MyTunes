package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BE.Song;
import src.GUI.Model.SongModel;

public class EditSongController
{
    public TextField txtTitle, txtArtist;;
    public Button btnSaveE;
    public Button btnCancleE;
    private SongModel model;
    private Song selectedSong;

    public void handleSaveEdit(ActionEvent actionEvent) throws Exception {

        String updatedTitle = txtTitle.getText();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleCancleEdit(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    private void fillSongsIN()
    {
        txtTitle.setText(selectedSong.getTitle());
        txtArtist.setText(selectedSong.getArtist().toString());
    }
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }
    public void setup() {
        fillSongsIN();
    }
}