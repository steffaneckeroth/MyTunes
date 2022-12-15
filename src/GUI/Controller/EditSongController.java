package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.BE.Song;
import src.GUI.Model.SongModel;

public class EditSongController extends BaseController {
    @FXML
    private TextField txtTitle, txtArtist;
    private Song selectedSong;
    private SongModel model;

    /**
     * This method sets the SongModel and selected Song for the MyTunes class.
     * The SongModel instance is used to access the list of songs, while the selected Song
     * @param model
     * @param song
     */
    public void setModelMyTunes(SongModel model, Song song){
        this.model=model;
        this.selectedSong=song;
    }

    /**
     * This method handles the save/edit action for a Song object.
     * @param actionEvent
     * @throws Exception
     */
    public void handleSaveEdit(ActionEvent actionEvent) throws Exception {
        String updatedTitle = txtTitle.getText();
        String updatedArtist = txtArtist.getText();
        Song updatedSongs = new Song(selectedSong.getId(), updatedTitle, updatedArtist, selectedSong.getCategory(), selectedSong.getFilepath(), selectedSong.getDuration());

        model.updateSongs(updatedSongs);
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method closes the window.
     * @param actionEvent
     * @throws Exception
     */
    public void handleCancleEdit(ActionEvent actionEvent) throws Exception {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * This method populates the text fields with the song's title and artist.
     * @param song
     * @throws Exception
     */
    public void fillSongsIN(Song song) throws Exception {
        model = getModel().getSongModel();
        txtTitle.setText(song.getTitle());
        txtArtist.setText(song.getArtist());
    }

    /**
     * This method sets the selectedSong field to the provided Song object.
     * @param s
     */
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }
}