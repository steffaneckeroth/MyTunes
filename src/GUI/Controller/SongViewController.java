package src.GUI.Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.media.Media;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;


import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.util.Duration;

import src.BE.Song;
import src.BE.Playlist;
import src.BE.SongOnPlaylist;
import src.GUI.Model.PlaylistModel;
import src.GUI.Model.SongModel;
import src.GUI.Model.SongOnPlaylistModel;
//import src.GUI.Model.SongOnPlaylistModel;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class SongViewController extends BaseController implements Initializable {
    public javafx.scene.image.ImageView imageView;
    public Button playButton, btnEditS, btnDeleteSong, previousButton, uploadButton, btnEditP, btnNewPlaylist, btnSongToPlaylist, btnDeleteSongOnPlaylist;
    public TableColumn<Song, String> drtCol, catCol, artCol, tltCol;
    public TableColumn<Playlist, String> namCol;
    public TableView<Playlist> tblPlaylist;
    public ListView tblSongsOnPlaylist;
    @FXML
    private Slider songProgressBar, volumeSlider;
    @FXML
    private javafx.scene.control.Label songLabel;
    public TableView<Song> tblSongs;
    private Media media;
    private MediaPlayer mediaPlayer;
    public File directory;
    public File[] files;
    private ArrayList<File> songs;

    @FXML
    private Label lblCurrent, lblEnd;
    private int songNumber;
    private Timer timer;
    private boolean running;
    private SongModel songModel;
    private PlaylistModel playlistModel;
    @FXML
    private TextField txtTitle, txtArtist, txtCategory, txtTime, txtSongSearch, txtEditPlaylist;
    private SongOnPlaylistModel songOnPlaylistModel;

    public SongViewController() {
        try {
            songModel = new SongModel();
            playlistModel = new PlaylistModel();
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


        try {
            songOnPlaylistModel = new SongOnPlaylistModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        tblSongs.setItems(songModel.getObservableSongs());
        tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        tltCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        artCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getArtist()));
        catCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getCategory()));
        drtCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        namCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getPlaylistName()));

        if (files != null) {
            Collections.addAll(songs, files);
        }

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume(volumeSlider.getValue() * 0.01));
        tblSongs.setItems(songModel.getObservableSongs());

        txtSongSearch.textProperty().addListener((observableValue, oldValue, newValue) ->
        {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });

        tblPlaylist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tblSongsOnPlaylist.getItems().clear();
            ArrayList<Song> songs1 = songOnPlaylistModel.getSongsOnPlaylist(newValue);
            tblSongsOnPlaylist.getItems().addAll(songs1);
        });

        songProgressBar.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    songProgressBar.setMax(mediaPlayer.getTotalDuration().toSeconds());

                    mediaPlayer.seek(Duration.seconds(songProgressBar.getValue()));
                }
            }
        });
        songProgressBar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                    mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                }
            }
        });
    }

    public void setup() {
        btnEditP.setDisable(true);
        tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        tblPlaylist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {
            @Override
            public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
                if (newValue != null) {
                    btnEditP.setDisable(false);
                    txtEditPlaylist.setText(newValue.getPlaylistName());
                } else
                    btnEditP.setDisable(true);
            }
        });
        btnEditS.setDisable(true);
        tblSongs.setItems(songModel.getObservableSongs());
        tblSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {
            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {

                if (newValue != null) {
                    btnEditS.setDisable(false);
                    txtTitle.setText(newValue.getTitle());
                    txtArtist.setText(String.valueOf(newValue.getArtist()));
                } else
                    btnEditS.setDisable(true);
            }
        });
    }

    @FXML
    public void pauseAndPlayMedia()
    {
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.pause();
            imageView.setVisible(true);

        }
        else if (mediaPlayer !=null  && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED){
            mediaPlayer.play();
            imageView.setVisible(false);
        }
        else
        {
            playSelectedSong();
            mediaPlayer.play();
        }
    }

    public void tblSongsClicked(javafx.scene.input.MouseEvent mouseEvent) {
        songNumber = tblSongs.getSelectionModel().getSelectedIndex();
        playSelectedSong();
    }


    public void playSelectedSong() {
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        Song selectedSong = tblSongs.getItems().get(songNumber);
        songLabel.setText(selectedSong.getTitle());
        media = new Media(new File(selectedSong.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(selectedSong.getFilepath());
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }

    public void nextMedia() {
        System.out.println(songs.size());
        System.out.println(songNumber);
        if (songNumber < songs.size() -1) {
            songNumber ++;
        } else {
            songNumber = 0;
        }
        mediaPlayer.stop();
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            cancelTimer();
        }
        playSelectedSong();
        imageView.setVisible(false);
    }

    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
        } else {
            songNumber = songs.size() - 1;
        }
        mediaPlayer.stop();
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            cancelTimer();
        }
        playSelectedSong();
        imageView.setVisible(false);
    }



    public void beginTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                songProgressBar.setMax(mediaPlayer.getTotalDuration().toSeconds());
                double current = mediaPlayer.getCurrentTime().toSeconds();
                songProgressBar.setValue(current);
            }
        };
        timer.scheduleAtFixedRate(task, 10, 10);
    }

    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

    private void bindCurrentTimeLabel() {
        lblCurrent.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime());
            }
        }, mediaPlayer.currentTimeProperty()));
    }

    private void bindTotalTimeLabel() {
        lblEnd.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getTotalDuration());
            }
        }, mediaPlayer.currentTimeProperty()));
    }

    private String getTime(Duration time) {
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();
        if (seconds > 59)
            seconds = seconds % 60;
        if (minutes > 59)
            minutes = minutes % 60;
        if (hours > 59)
            hours = hours % 60;
        if (hours > 0)
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        else return String.format("%02d:%02d", minutes, seconds);
    }

    private void viewTime() {
        bindTotalTimeLabel();
        bindCurrentTimeLabel();
    }


    public void handleButtonDeleteSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeleteSongView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete the song");
        stage.setScene(new Scene(root));
        DeleteSongController controller = fxmlLoader.getController();
        controller.setController(this);
        stage.showAndWait();
    }

    public void handleButtonNewPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/NewPlayListView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new song");
        stage.setScene(new Scene(root));
        NewPlaylistController controller = fxmlLoader.getController();
        controller.setController(this);
        stage.showAndWait();
    }

    @FXML
    private void handleButtonNewSong(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/NewSongView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new song");
        stage.setScene(new Scene(root));
        NewSongController controller = fxmlLoader.getController();
        controller.setController(this);
        stage.showAndWait();
    }

    public void EditSong(ActionEvent actionEvent) throws Exception {
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/src/GUI/View/EditSongView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        EditSongController controller = loader.getController();
        //controller.setModel(super.getModel());
        controller.fillSongsIN(selectedSong);
        controller.setSelectedSong(selectedSong);
        controller.setup();
        controller.setController(this);

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Movie");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }


    public void EditPlaylist(ActionEvent event) throws Exception {
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        playlistModel.setSelectedPlaylist(selectedPlaylist);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/src/GUI/View/EditPlaylistView.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        EditPlaylistController controller = loader.getController();
        //controller.setModel(super.getModel());
        controller.fillPlaylistIN(selectedPlaylist);
        controller.setSelectedPlaylist(selectedPlaylist);
        controller.setup();
        controller.setController(this);

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Movie");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

    public void deletePlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeletePlaylistView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete the Playlist");
        stage.setScene(new Scene(root));
        DeletePlaylistController controller = fxmlLoader.getController();
        controller.setController(this);
        stage.showAndWait();
    }

    public void handleButtonDeleteSongOnPlaylist(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeleteSongOnPlaylistView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete the song on the playlist");
        stage.setScene(new Scene(root));
        DeleteSongOnPlaylistController controller = fxmlLoader.getController();
        controller.setController(this);
        stage.showAndWait();
    }

    public void handleButtonSongToPlaylist(ActionEvent actionEvent) {
        Playlist mPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        Song mSelectedSong = tblSongs.getSelectionModel().getSelectedItem();
        tblSongsOnPlaylist.getItems().add(mSelectedSong);
        try {
            songOnPlaylistModel.addToPlaylist(mPlaylist, mSelectedSong);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
