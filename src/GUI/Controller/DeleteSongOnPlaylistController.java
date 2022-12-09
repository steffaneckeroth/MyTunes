package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Song;
import src.GUI.Model.SongModel;

public class DeleteSongOnPlaylistController extends BaseController{
    public Button btnOK;
    public Button btnCancel;
    private Node label;
    public Button btnYesDeleteSongOnPlaylist;
    public Button btnNoDeleteSongOnPlaylist;
    private Song selectedSong;
    private SongModel model;
    private SongViewController songViewController;

    public DeleteSongOnPlaylistController() {
        try {
            model = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleButtonYesDeleteSongOnPlaylist(ActionEvent event) throws Exception
    {
        Song deletedSong = (Song) songViewController.tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
        model.deleteSong(deletedSong);
        songViewController.tblSongsOnPlaylist.setItems(model.getObservableSongs());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleButtonNoDeleteSongOnPlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setSelectedSong(Song s)
    {
        selectedSong = s;
    }

    private void fillSongsIN() throws Exception {
        model = getModel().getSongModel();
    }
    public void setController(SongViewController songViewController) {
        this.songViewController=songViewController;
    }
    @Override
    public void setup() throws Exception {
    }

}
