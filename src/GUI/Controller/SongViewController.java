package src.GUI.Controller;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
    public ImageView gif;
    @FXML
    private javafx.scene.image.ImageView imageView;
    @FXML
    private Button btnEditS, btnEditP, btnSongOnPlaylistUp, btnSongOnPlaylistDown;
    @FXML
    private TableColumn<Song, String> drtCol, catCol, artCol, tltCol;
    @FXML
    private TableColumn<Playlist, String> namCol, colTime, sngCol;
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


    /**
     * Constructor for SongViewController with 3 object being created.
     */

    public SongViewController() {
        try {
            songModel = new SongModel();
            playlistModel = new PlaylistModel();
            songOnPlaylistModel = new SongOnPlaylistModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayError(Exception e) {

    }

    /**
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        songs = new ArrayList<>();
        directory = new File("lib/music");
        files = directory.listFiles();
        // setting the tbl song and playlist, to be viewed in the UI.
        tblSongs.setItems(songModel.getObservableSongs());
        tblPlaylist.setItems(playlistModel.getObservablePlaylists());
        // sets the cell value propertyvaluefactory is used to extract the property
        // value from a given object and then be displayed in the tablecolumn.
        tltCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        // This means that the cell value for "artCol" will be the artist
        // field of the object passed to the lambda expression.
        artCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getArtist()));
        catCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getCategory()));
        drtCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        namCol.setCellValueFactory(c -> new SimpleObjectProperty<String>(c.getValue().getPlaylistName()));
        sngCol.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getSongs().size())));
        colTime.setCellValueFactory(c ->new SimpleStringProperty(String.valueOf(c.getValue().getTotalDuration())));

        //colTime.setText("00:00:00");

        // if the files variable is not null, then it will add all the files array to the songs collection
        if (files != null) {
            Collections.addAll(songs, files);
        }
        // this controls the volume on the tableview to go up and down
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
            /**
             * This method is handling change in the value of an observablevalue object.
             * @param observable
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    // setting the maximum value of a progress bar to the total duration of the song.
                    // This is used to track the time that the song is being play.
                    songProgressBar.setMax(mediaPlayer.getTotalDuration().toSeconds());

                    mediaPlayer.seek(Duration.seconds(songProgressBar.getValue()));
                }
            }
        });
        songProgressBar.valueProperty().addListener(new ChangeListener<Number>() {
            /**
             * This method is handling change in an Observablevalue object.
             * @param observable
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // This retrive the currrent time of the song.
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
            /**
             * This method is handling change in a playlist object.
             * @param observable
             * @param oldValue
             * @param newValue
             */
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

    /**
     * This method allows the media player to pause and play the song.
     */
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

    /**
     *  This method is looking for mouseevent,
     *  for how many times you have to click to get the selected song.
     * @param mouseEvent
     */
    public void tblSongsClicked(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            //checks if there has been 2 clicks
            if (mouseEvent.getClickCount() == 2) {
                songNumber = tblSongs.getSelectionModel().getSelectedIndex();
                playSelectedSong();
            }
        }
    }


    /**
     * This method is looking for mouseevent,
     * for how many times you have to click to get the selected playlist.
     * @param mouseEvent
     */
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

    /**
     * This method is looking for mouseevent,
     * for how many times you have to click to get the selected songsonplaylist.
     * @param mouseEvent
     */
    public void tblSongsOnPlaylistClicked(MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            //checks if there has been 2 clicks
            if(mouseEvent.getClickCount() == 2){
                playlistNumber = tblPlaylist.getSelectionModel().getSelectedIndex();
                songOnPlaylistNumber = tblSongsOnPlaylist.getSelectionModel().getSelectedIndex();
                System.out.println(playlistNumber);
                System.out.println(songOnPlaylistNumber);
                playSelectedSongOnPlaylist();
            }
        }
    }

    /**
     * This method is for playing the selected song form a playlist.
     */
    public void playSelectedSongOnPlaylist() {
        // if it is playing a song, then the current playing song is paused.
        if (mediaPlayer !=null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.stop();
        }
        // retrives the selected playlist and song from the tableview.
        Playlist selectedPlaylist = tblPlaylist.getItems().get(playlistNumber);
        Song songOnPlayList = tblSongsOnPlaylist.getItems().get(songOnPlaylistNumber);
        // setting the song lable for the current song.
        songLabel.setText(selectedPlaylist.getPlaylistName()+" - "+ songOnPlayList.getTitle());
        // creating an object using the filepath.
        media = new Media(new File(songOnPlayList.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songOnPlayList.getFilepath());
        // plays the new generated object and shows the progressbar begin from the start again.
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }

    /**
     * This method is for playing the selected song.
     */
    public void playSelectedSong() {
        // if it is playing a song, then the current playing song is paused.
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        playlistNumber = -1;
        Song selectedSong = tblSongs.getItems().get(songNumber);
        songLabel.setText(selectedSong.getTitle());
        // creating an object using the filepath.
        media = new Media(new File(selectedSong.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(selectedSong.getFilepath());
        // plays the new generated object and shows the progressbar begin from the start again.
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }

    /**
     * This method is for playing the selected playlist.
     */
    public void playSelectedPlaylist() {
        // if it is playing a song, then the current playing song is paused.
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        // retrives the selected playlist and song from the tableview.
        Playlist selectedPlaylist = tblPlaylist.getItems().get(playlistNumber);
        Song songOnPlayList = tblSongsOnPlaylist.getItems().get(songOnPlaylistNumber);
        // setting the song lable for the current song.
        songLabel.setText(selectedPlaylist.getPlaylistName() + " - " + songOnPlayList.getTitle());
        // creating an object using the filepath.
        media = new Media(new File(songOnPlayList.getFilepath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        System.out.println(songOnPlayList.getFilepath());
        // plays the new generated object and shows the progressbar begin from the start again.
        mediaPlayer.play();
        beginTimer();
        viewTime();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        imageView.setVisible(false);
    }

    /**
     * This method is for playing next song.
     */
    public void nextMedia() {
        System.out.println(songs.size());
        System.out.println(songNumber);

        if (playlistNumber == -1)
        {
            if (songNumber < songs.size() - 1)
            {
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

    /**
     * This method is for playing the song before.
     */
    public void previousMedia()
    {
        if (playlistNumber == -1)
        {
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
        else
        {
            if (playSongOnPlaylistNumber >0)
            {
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

    /**
     * This method begins the song progressbar.
     */
    public void beginTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                songProgressBar.setMax(mediaPlayer.getTotalDuration().toSeconds());
                // converts it into seconds.
                double current = mediaPlayer.getCurrentTime().toSeconds();
                songProgressBar.setValue(current);
            }
        };
        timer.scheduleAtFixedRate(task, 10, 10);
    }

    /**
     * // This method cancels the timer by setting the "running" flag to
     * false and calling the cancel method on the timer object.
     */
    public void cancelTimer() {
        running = false;
        timer.cancel();
    }

    /**
     * This method binds the text property of the lblCurrent label to the current time of the media player.
     */
    private void bindCurrentTimeLabel() {
        lblCurrent.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getCurrentTime());
            }
        }, mediaPlayer.currentTimeProperty()));
    }

    /**
     * This method binds the text property of the lblEnd label to the total duration of the media player.
     */
    private void bindTotalTimeLabel() {
        lblEnd.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mediaPlayer.getTotalDuration());
            }
        }, mediaPlayer.currentTimeProperty()));
    }

    /**
     * This method converts a Duration object into a formatted string representation of the time.
     * @param time
     * @return String.format("%d:%02d:%02d", hours, minutes, seconds)
     */
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

    /**
     * This method updates the labels for the total and current time of the video being played.
     */
    private void viewTime() {
        bindTotalTimeLabel();
        bindCurrentTimeLabel();
    }

    /**
     * This method is handling the delete song button.
     * @param event
     * @throws IOException
     */
    public void handleButtonDeleteSong(ActionEvent event) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeleteSongView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        // Title of the stage
        stage.setTitle("Delete the song");
        stage.setScene(new Scene(root));
        // This code creates an instance of the DeleteSongController
        // and sets the model and selected item for the controller.
        DeleteSongController controller = fxmlLoader.getController();
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    /**
     * This method is handling the new playlist  button.
     * @param event
     * @throws IOException
     */
    public void handleButtonNewPlaylist(ActionEvent event) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/NewPlayListView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        // Title of the stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new playlist");
        stage.setScene(new Scene(root));
        // This code creates an instance of the NewPlaylistController
        // and sets the model and selected item for the controller.
        NewPlaylistController controller = fxmlLoader.getController();
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    /**
     * This method is handling the new song button.
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleButtonNewSong(ActionEvent event) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/NewSongView.fxml"));
        // Loads the stage.
        Parent root = fxmlLoader.load();
        // Makes the new stage.
        Stage stage = new Stage();
        // Title of the stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new song");
        stage.setScene(new Scene(root));
        // This code creates an instance of the NewSongController
        // and sets the model and selected item for the controller.
        NewSongController controller = fxmlLoader.getController();
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());
        // The stage is then displayed and the program waits for
        // the user to interact with the delete song dialog.
        stage.showAndWait();
    }

    /**
     * This method is handling the EditSong button.
     * @param actionEvent
     * @throws Exception
     */
    public void EditSong(ActionEvent actionEvent) throws Exception {
        // Only do the event if there is a song is selected in the tblsong.
        Song selectedSong = tblSongs.getSelectionModel().getSelectedItem();
        songModel.setSelectedSong(selectedSong);
        FXMLLoader loader = new FXMLLoader();
        // Finds where the fxml is located.
        loader.setLocation(getClass().getResource("/src/GUI/View/EditSongView.fxml"));
        // Makes the new stage.
        AnchorPane pane = (AnchorPane) loader.load();
        EditSongController controller = loader.getController();
        controller.fillSongsIN(selectedSong);
        controller.setSelectedSong(selectedSong);
        controller.setModelMyTunes(songModel, tblSongs.getSelectionModel().getSelectedItem());

        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit Song");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        // Show the dialog and wait until the user closes it
        dialogWindow.showAndWait();
    }

    /**
     * This method is handling the EditPlaylist button.
     * @param event
     * @throws Exception
     */
    public void EditPlaylist(ActionEvent event) throws Exception {
        // Only do the event if there is a playlist is selected in the tblplaylist.
        Playlist selectedPlaylist = tblPlaylist.getSelectionModel().getSelectedItem();
        playlistModel.setSelectedPlaylist(selectedPlaylist);
        FXMLLoader loader = new FXMLLoader();
        // Finds where the fxml is located.
        loader.setLocation(getClass().getResource("/src/GUI/View/EditPlaylistView.fxml"));
        // Makes the new stage.
        AnchorPane pane = (AnchorPane) loader.load();
        EditPlaylistController controller = loader.getController();
        controller.fillPlaylistIN(selectedPlaylist);
        controller.setSelectedPlaylist(selectedPlaylist);
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
        // Create the dialog Stage.
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Edit playlist");
        dialogWindow.initModality(Modality.WINDOW_MODAL);
        dialogWindow.initOwner(((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(pane);
        dialogWindow.setScene(scene);
        // Show the dialog and wait until the user closes it.
        dialogWindow.showAndWait();
    }

    /**
     * This method is handling the deleteplaylist button.
     * @param event
     * @throws IOException
     */
    public void deletePlaylist(ActionEvent event) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeletePlaylistView.fxml"));
        // Makes the new stage.
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete the Playlist");
        stage.setScene(new Scene(root));
        DeletePlaylistController controller = fxmlLoader.getController();
        controller.setModelMyTunes(playlistModel, tblPlaylist.getSelectionModel().getSelectedItem());
        // Show the dialog and wait until the user closes it.
        stage.showAndWait();
    }

    /**
     * This method is handling the DeleteSongonplaylist button.
     * @param event
     * @throws IOException
     */
    public void handleButtonDeleteSongOnPlaylist(ActionEvent event) throws IOException {
        // Finds where the fxml is located.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/src/GUI/View/DeleteSongOnPlaylistView.fxml"));
        // Makes the new stage.
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Delete the song on the playlist");
        stage.setScene(new Scene(root));
        DeleteSongOnPlaylistController controller = fxmlLoader.getController();
        controller.setModelMyTunes(songOnPlaylistModel,
                tblSongsOnPlaylist.getSelectionModel().getSelectedItem(),
                tblPlaylist.getSelectionModel().getSelectedItem());
        // Show the dialog and wait until the user closes it.
        stage.showAndWait();
    }

    /**
     * This method is handling the Songtoplaylist button.
     * @param actionEvent
     */
    public void handleButtonSongToPlaylist(ActionEvent actionEvent) {
        // Only do the event if there is a playlist and a song selected.
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

    /**
     * This method makes the song go up in the playlist.
     * @param event
     */
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

    /**
     * This method makes the song go down in the playlist.
     * @param event
     */
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
