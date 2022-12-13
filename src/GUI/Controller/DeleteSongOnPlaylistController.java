package src.GUI.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.BE.Song;
import src.BE.SongOnPlaylist;
import src.GUI.Model.SongModel;
import src.GUI.Model.SongOnPlaylistModel;

public class DeleteSongOnPlaylistController extends BaseController{
    public Button btnOK;
    public Button btnCancel;
    private Node label;
    public Button btnYesDeleteSongOnPlaylist;
    public Button btnNoDeleteSongOnPlaylist;
    private Song selectedSong;
    private SongModel model;
    private SongViewController songViewController;

    private SongOnPlaylistModel songOnPlaylistModel;

    public DeleteSongOnPlaylistController() {
        try {
            model = new SongModel();
            songOnPlaylistModel = new SongOnPlaylistModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleButtonYesDeleteSongOnPlaylist(ActionEvent event) throws Exception
    {
        Playlist mPlaylist = songViewController.tblPlaylist.getSelectionModel().getSelectedItem();
        Song mSong = (Song) songViewController.tblSongsOnPlaylist.getSelectionModel().getSelectedItem();
        songOnPlaylistModel.deleteSongOnPlaylist(mPlaylist, mSong);
        songViewController.tblSongsOnPlaylist.setItems(songOnPlaylistModel.getObservableSongOnPlaylist());



        //songViewController.tblSongsOnPlaylist.setItems(songOnPlaylistModel.getObservableSongOnPlaylist());
        //songViewController.tblSongsOnPlaylist.setItems(songOnPlaylistModel.getObservableSongOnPlaylist());
        //songOnPlaylistModel.getObservableSongOnPlaylist().setAll(songOnPlaylistModel.getObservableSongOnPlaylist());
        //songViewController.tblSongsOnPlaylist.setItems(songOnPlaylistModel.getObservableSongOnPlaylist());

        //TODO "refresh liste"
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

        //TODO fiz pls
    }

}
