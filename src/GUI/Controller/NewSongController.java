package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.BE.Song;
import src.GUI.Model.SongModel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;


public class NewSongController extends BaseController{


    public ComboBox<String> cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;
    public String fileMusicPath = "lib/music";
    private Path target = Paths.get(fileMusicPath);
    private File mFile;
    private Song selectedSong;
    private SongModel model;

    /**
     * This method sets the model and selected song for the MyTunes application.
     * @param model
     * @param song
     */
    public void setModelMyTunes(SongModel model, Song song){
        this.model=model;
        this.selectedSong=song;
    }
    public void initialize()
    {
        //Adds items/Categories to ComboBox
        cbxDropDown.getItems().addAll("Pop", "Hiphop", "Rock");
        cbxDropDown.getSelectionModel().select("---");
    }

    public void handleButtonChoose(ActionEvent actionEvent)
    {
        //Opens file browser to select a file
        Stage stage = new Stage();
        FileChooser mFileChooser = new FileChooser();
        mFile = mFileChooser.showOpenDialog(stage);
        if (fileMusicPath != null)
        {
            txtfFile.setText((fileMusicPath +"/"+ mFile.getName()).replace("\\", "/").replaceAll(" ", "%20"));
        }
        System.out.println("Selected file " + mFile);
        System.out.println(getSongLength(mFile).toString());
    }

    /**
     * This method handles the save button function.
     * @param actionEvent
     */
    public void handleButtonSave(ActionEvent actionEvent)
    {
        String title = txtfTitle.getText();
        String artist = txtfArtist.getText();
        String category = cbxDropDown.getValue();
        String filepath = txtfFile.getText();
        Time duration = Time.valueOf(txtfTime.getText());
        try
        {
            Files.copy(mFile.toPath(), target.resolve(mFile.toPath().getFileName()));
            System.out.println("Song added: " + title + ", " + artist + ", " + category + ", " + "'"+filepath+"'" + ", " + duration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Could not add song");
        }

        mFile = new File (fileMusicPath + "/" + mFile.getName());

        try
        {
            this.model.createNewSong(title,artist,category,filepath,duration);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        //songViewController.tblSongs.setItems(songModel.getObservableSongs());
        Node source = (Node) actionEvent.getSource();
        Stage mStage = (Stage) source.getScene().getWindow();
        mStage.close();
    }

    /**
     * This method closes the window.
     * @param actionEvent
     */
    public void handleButtonCancle (ActionEvent actionEvent)
    {
        // This code closes the current window by getting a reference to the stage
        // and calling the close() method.
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleTxtTtl(ActionEvent actionEvent)
    {
        this.txtfTitle.getText();
    }

    public void handleCategory(ActionEvent actionEvent)
    {

    }

    /**
     * This Method gets the length of the song.
     * @param file
     * @return mMedia.getDuration()
     */
    public Duration getSongLength(File file)
    {
        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration();
    }
}


