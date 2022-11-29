package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import src.BE.Song;
import src.GUI.Model.SongModel;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Timer;

public class SongViewController implements Initializable {

    public javafx.scene.image.ImageView imageView;
    public Button playButton;
    @FXML
    private Slider songProgressBar;
    @FXML
    private javafx.scene.control.Label songLabel;

    @FXML
    private Button uploadButton ;

    @FXML
    private Slider volumeSlider;
    public ListView<Song> lstSongs;
    private Media media;
    private MediaPlayer mediaPlayer;
    public File directory;
    public  File [] files;

    private ArrayList<File> songs;
    @FXML
    private Label lblCurrent, lblEnd;
    private int songNumber;
    private Timer timer;
    private boolean running;
    private SongModel songModel;

    public SongViewController()  {
        try {
            songModel = new SongModel();
        } catch (Exception e) {
            displayError(e);
        }
    }
    private void displayError(Exception e) {
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        songs = new ArrayList<>();
        directory = new File("lib/music");
        files = directory.listFiles();
        lstSongs.setItems(songModel.getObservableSongs());
        if (files != null)
        {
            Collections.addAll(songs, files);
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
    }
    @FXML
    public void playMedia(){
        mediaPlayer.play();
    }
    public void previousMedia()
    {

    }
    public void UploadSong()
    {
    }
    public void nextMedia()
    {

    }
}
