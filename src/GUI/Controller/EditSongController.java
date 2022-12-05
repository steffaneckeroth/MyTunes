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
        String updatedArtist = txtArtist.getText();
        //Song updatedSongs = new Song(model.getSelectedSong().getId(), updatedTitle, updatedArtist);

        //model.updateSongs(updatedSongs);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleCancleEdit(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    private void fillSongsIN()
    {
        //model = getModel().getMovieModel();
        txtTitle.setText(model.getSelectedSong().getTitle());
        txtArtist.setText(model.getSelectedSong().getArtist());
    }
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }
    public void setup() {
        fillSongsIN();
    }
}