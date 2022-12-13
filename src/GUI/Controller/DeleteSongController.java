package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.BE.Song;
import src.GUI.Model.PlaylistModel;
import src.GUI.Model.SongModel;

public class DeleteSongController extends BaseController {

    public Button btnOK;
    public Button btnCancel;
    private Node label;
    private Song selectedSong;
    private SongModel model;
    private SongViewController songViewController;

    public DeleteSongController() {
        try {
            model = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void yesDeleteSong(ActionEvent event) throws Exception
    {
            /*Song deletedSong = songViewController.tblSongs.getSelectionModel().getSelectedItem();
            model.deleteSong(deletedSong);
            songViewController.tblSongs.setItems(model.getObservableSongs());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();*/
    }
    public void noDeleteSong(ActionEvent event)
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

}




