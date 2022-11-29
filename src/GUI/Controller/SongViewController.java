package src.GUI.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.*;
import java.util.List;

public class SongViewController implements Initializable {

    public javafx.scene.image.ImageView imageView;
    public Button playButton;
    public Button previousButton;
    @FXML
    private Slider songProgressBar;
    private TextField txtTitle;
    private TextField txtArtist;
    private TextField txtCategory;
    private TextField txtFilePath;
    private TextField txtDuration;
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
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume(volumeSlider.getValue()* 0.01));

        lstSongs.setItems(songModel.getObservableSongs());
        lstSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                if (newValue != null){
                    txtTitle.setText(newValue.getTitle());
                    txtArtist.setText(newValue.getArtist().toString());
                    txtCategory.setText(newValue.getCategory().toString());
                    txtFilePath.setText(newValue.getFilepath());
                    txtDuration.setText(String.valueOf(newValue.getDuration()));
                }
            }
        });
    }
    @FXML
    public void playMedia(){

        beginTimer();
        mediaPlayer.play();
        mediaPlayer.setVolume(volumeSlider.getValue()* 0.01);
        imageView.setVisible(false);
        if (running)
        {
            mediaPlayer.stop();
            imageView.setVisible(true);
        }
        else
        {
            mediaPlayer.play();
            imageView.setVisible(false);
        }
    }

    public void previousMedia()
    {
        if (songNumber > 0)
        {
            songNumber--;
        }
        else {
            songNumber = songs.size() -1;
        }
        mediaPlayer.stop();
        if (running)
        {
            cancelTimer();
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        playMedia();
        imageView.setVisible(false);
    }

    public void nextMedia()
    {
        if (songNumber < songs.size()-1)
        {
            songNumber++;
        }
        else {
            songNumber = 0;
        }
        mediaPlayer.stop();
        if (running)
        {
            cancelTimer();
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        playMedia();
        imageView.setVisible(false);
    }
    public void UploadSong()
    {

    }
    public void beginTimer()
    {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setValue(current);

                if (current / end == 1) {
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task,1000, 1000);
    }
    public void cancelTimer()
    {
        running = false;
        timer.cancel();
    }
}
