package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Song;
import src.GUI.Model.SongModel;

public class DeleteSongController extends BaseController {

    public Button btnOK;
    public Button btnCancel;
    private Node label;
    private Song selectedSong;
    private SongModel model;

    /**
     * This method sets the model and selected song for MyTunes.
     * @param model
     * @param song
     */
    public void setModelMyTunes(SongModel model, Song song)
    {
        this.model=model;
        this.selectedSong=song;
    }

    /**
     * The deletesong method is called on the model, passing in the selected song object.
     * @param event
     * @throws Exception
     */
    public void yesDeleteSong(ActionEvent event) throws Exception
    {
            model.deleteSong(selectedSong);
             // This code closes the current window by getting a reference to the stage
             // and calling the close() method.
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
    }

    /**
     * This method closes the window.
     * @param event
     */
    public void noDeleteSong(ActionEvent event)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}




