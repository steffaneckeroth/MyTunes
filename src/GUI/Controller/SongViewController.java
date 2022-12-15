package src.GUI.Controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;

import javafx.collections.ListChangeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.BE.Playlist;
import src.BE.Song;
import src.GUI.Model.PlaylistModel;
import src.GUI.Model.SongModel;
import src.GUI.Model.SongOnPlaylistModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class SongViewController extends BaseController implements Initializable {
    private int totalDuration = 0;
    public TableColumn colTime;
    @FXML
    private javafx.scene.image.ImageView imageView;
    @FXML
    private Button playButton, btnEditS, btnDeleteSong, previousButton, uploadButton,
            btnEditP, btnNewPlaylist, btnSongToPlaylist, btnDeleteSongOnPlaylist,
            btnSongOnPlaylistUp, btnSongOnPlaylistDown;
    @FXML
    private TableColumn<Song, String> drtCol, catCol, artCol, tltCol;
    @FXML
    private TableColumn<Playlist, String> namCol;
    @FXML
    private TableView<Playlist> tblPlaylist;
    @FXML
    private ListView<Song> tblSongsOnPlaylist;
    @FXML
    private Slider songProgressBar, volumeSlider;
    @FXML
    private javafx.scene.control.Label songLabel;
    @FXML
    private TableView<Song> tblSongs;
    @FXML
    private TextField txtTitle, txtArtist, txtCategory, txtTime, txtSongSearch, txtEditPlaylist;
    @FXML
    private Label lblCurrent, lblEnd;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private int songOnPlaylistNumber;
    private int playlistNumber = -1;
    private int playSongOnPlaylistNumber;
    private Timer timer;
    private boolean running;
    private SongModel songModel;
    private PlaylistModel playlistModel;
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
        txtSongSearch.textProperty().addListener((observableValue, oldValue, newValue) ->
        {
            try {
                songModel.searchSong(newValue);
            } catch (Exception e) {
                displayError(e);
            }
        });


// Update the total duration label whenever the playlist is modified

        tblSongsOnPlaylist.setItems(songOnPlaylistModel.getObservableSongOnPlaylist());
        tblPlaylist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            songOnPlaylistModel.setSelectedPlaylist(newValue);
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
    public void pauseAndPlayMedia() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            imageView.setVisible(true);

        } else if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            mediaPlayer.play();
            imageView.setVisible(false);
        } else {
            playSelectedSong();
            playSelectedPlaylist();
            playSelectedSongOnPlaylist();
            mediaPlayer.play();
        }
    }

    public void tblSongsClicked(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            //checks if there has been 2 clicks
            if (mouseEvent.getClickCount() == 2) {
                songNumber = tblSongs.getSelectionModel().getSelectedIndex();
                playSelectedSong();
            }
        }
    }

    public void tblPlaylistClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            //checks if there has been 2 clicks
            if (mouseEvent.getClickCount() == 2) {
                playlistNumber = tblPlaylist.getSelectionModel().getSelectedIndex();
                songOnPlaylistNumber = 0;
                playSelectedPlaylist();
            }
        }
    }
    public void tblSongsOnPlaylistClicked(MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            //checks if there has been 2 clicks
            if(mouseEvent.getClickCount() == 2){
                playlistNumber = tblPlaylist.getSelectionModel().getSelectedIndex();
                playSongOnPlaylistNumber = 0;
                playSelectedSongOnPlaylist();
            }
        }
    }
    public void playSelectedSongOnPlaylist() {
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        Playlist selectedPlaylist = tblPlaylist.getItems().get(playlistNumber);
        Song songOnPlayList = tblSongsOnPlaylist.getItems().get(playSongOnPlaylistNumber);
        songLabel.setText(selectedPlaylist.getPlaylistName()+" - "+ songOnPlayList.getTitle());
        media = new Media(new File(songOnPlayList.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songOnPlayList.getFilepath());
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }

    public void playSelectedSong() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        playlistNumber = -1;
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

    public void playSelectedPlaylist() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Playlist selectedPlaylist = tblPlaylist.getItems().get(playlistNumber);
        Song songOnPlayList = tblSongsOnPlaylist.getItems().get(songOnPlaylistNumber);
        songLabel.setText(selectedPlaylist.getPlaylistName() + " - " + songOnPlayList.getTitle());
        media = new Media(new File(songOnPlayList.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songOnPlayList.getFilepath());
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }
    public void nextMedia() {
        System.out.println(songs.size());
        System.out.println(songNumber);

        if (playlistNumber == -1) {
            if (songNumber < songs.size() - 1) {
                songNumber++;
            } else {
                songNumber = 0;
            }
            mediaPlayer.stop();
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                cancelTimer();
            }
            playSelectedSong();
            imageView.setVisible(false);

        }
        else if (playlistNumber >= 0)
         {
            if (songOnPlaylistNumber < songOnPlaylistModel.getObservableSongOnPlaylist().size() - 1) {
                songOnPlaylistNumber++;
            } else {
                songOnPlaylistNumber = 0;
            }
            mediaPlayer.stop();
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                cancelTimer();
            }
            playSelectedPlaylist();
            imageView.setVisible(false);
        }

        else
        {
            if (playSongOnPlaylistNumber < songOnPlaylistModel.getObservableSongOnPlaylist().size() -1) {
                playSongOnPlaylistNumber ++;
            } else {
                playSongOnPlaylistNumber = 0;
            }
            mediaPlayer.stop();
            if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                cancelTimer();
            }
            playSelectedSongOnPlaylist();
            imageView.setVisible(false);
        }
    }

    public void previousMedia() {
        if (playlistNumber == -1) {
            if (songNumber > 0) {
                songNumber--;
            } else {
                songNumber = songs.size() - 1;
            }
            mediaPlayer.stop();
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                cancelTimer();
            }
            playSelectedSong();
            imageView.setVisible(false);

        }
        else if (playlistNumber >= 0)
        {
            if (songOnPlaylistNumber >0) {
                songOnPlaylistNumber --;

            } else {
                songOnPlaylistNumber = songOnPlaylistModel.getObservableSongOnPlaylist().size() - 1;
            }
            mediaPlayer.stop();
            if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                cancelTimer();
            }
            playSelectedPlaylist();
            imageView.setVisible(false);
        }
        else {
        if (playSongOnPlaylistNumber >0) {
            playSongOnPlaylistNumber --;
        } else {
            playSongOnPlaylistNumber = songOnPlaylistModel.getObservableSongOnPlaylist().size() -1;
        }
        mediaPlayer.stop();
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            cancelTimer();
        }
        playSelectedSongOnPlaylist();
        imageView.setVisible(false);
        }
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
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());
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
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
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
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());
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
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());

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
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
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
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
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
        controller.setModelMyTunes(songOnPlaylistModel,
                tblSongsOnPlaylist.getSelectionModel().getSelectedItem(),
                tblPlaylist.getSelectionModel().getSelectedItem());
        stage.showAndWait();
    }

    public void handleButtonSongToPlaylist(ActionEvent actionEvent) {
        Playlist mPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        Song mSelectedSong = tblSongs.getSelectionModel().getSelectedItem();
        //tblSongsOnPlaylist.getItems().add(mSelectedSong);
        System.out.println(mSelectedSong);
        try {
            songOnPlaylistModel.addToPlaylist(mPlaylist, mSelectedSong);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void handleButtonUpSongOnPlaylist(ActionEvent event) {
// set the action for the move up button
        btnSongOnPlaylistUp.setOnAction(event1 -> {
            int selectedIndex = tblSongsOnPlaylist.getSelectionModel().getSelectedIndex();
            if (selectedIndex > 0) {
                // swap the items at the selected index and the one above it
                Collections.swap(tblSongsOnPlaylist.getItems(), selectedIndex, selectedIndex - 1);
                // update the selection
                tblSongsOnPlaylist.getSelectionModel().clearAndSelect(selectedIndex - 1);
            }
        });
    }

    public void handleButtonDownSongOnPlaylist(ActionEvent event) {
// set the action for the move down button
        btnSongOnPlaylistDown.setOnAction(event1 -> {
            int selectedIndex = tblSongsOnPlaylist.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < tblSongsOnPlaylist.getItems().size() - 1) {
                // swap the items at the selected index and the one below it

                Collections.swap(tblSongsOnPlaylist.getItems(), selectedIndex, selectedIndex + 1);
                // update the selection
                tblSongsOnPlaylist.getSelectionModel().clearAndSelect(selectedIndex + 1);
            }
        });

    }

}
