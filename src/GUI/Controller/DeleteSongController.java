package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.BE.Song;
import src.GUI.Model.PlaylistModel;
import src.GUI.Model.SongModel;
import src.GUI.Model.SongOnPlaylistModel;

public class DeleteSongController extends BaseController {

    public Button btnOK;
    public Button btnCancel;
    private Node label;
    private Song selectedSong;
    private SongModel model;

    public void setModelMyTunes(SongModel model, Song song)
    {
        this.model=model;
        this.selectedSong=song;
    }
    public void yesDeleteSong(ActionEvent event) throws Exception
    {
            model.deleteSong(selectedSong);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
    }
    public void noDeleteSong(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}




