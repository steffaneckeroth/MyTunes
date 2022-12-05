package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BE.Song;
import src.GUI.Model.SongModel;

public class EditSongController  extends BaseController
{
    @FXML
    private TextField txtTitle, txtArtist;
    private SongViewController songViewController;
    private SongModel model;
    private Song selectedSong;

    public EditSongController() {
        try {
            model = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void handleSaveEdit(ActionEvent actionEvent) throws Exception {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        Song updatedSongs = new Song(selectedSong.getId(), updatedTitle, updatedArtist, selectedSong.getCategory(), selectedSong.getFilepath(), selectedSong.getDuration());

        model.updateSongs(updatedSongs);
        songViewController.tblSongs.setItems(model.getObservableSongs());

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleCancleEdit(ActionEvent actionEvent) throws Exception {

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    public void fillSongsIN(Song song) throws Exception {
        model = getModel().getSongModel();
        txtTitle.setText(song.getTitle());
        txtArtist.setText(song.getArtist());
    }
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }

    @Override
    public void setup() throws Exception {
        //fillSongsIN();
    }
    public void setController(SongViewController songViewController) {
        this.songViewController=songViewController;
    }
}