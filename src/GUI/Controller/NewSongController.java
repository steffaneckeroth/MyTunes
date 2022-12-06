package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.File;
import java.sql.*;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import src.BE.Song;
import src.GUI.Model.SongModel;

public class NewSongController extends BaseController{

    private SongModel songModel;
    private SongViewController songViewController;
    public ComboBox<String> cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;




    public NewSongController()
    {
        try {
            songModel = new SongModel();
            songViewController = new SongViewController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize()
    {
        //Adds items/Categories to ComboBox
        cbxDropDown.getItems().addAll("Pop", "Hiphop", "Rock");
        cbxDropDown.getSelectionModel().select("---");
    }

    public void handleButtonChoose(ActionEvent actionEvent)
    {
        if (actionEvent.getSource() == btnChoose)
        {
            //Opens file browser to select a file
            FileChooser mFileChooser = new FileChooser();
            File mFile = mFileChooser.showOpenDialog(null);

            txtfFile.setText(mFile.getName());
            System.out.println("Selected file " + mFile);
            System.out.println(getSongLength(mFile).toString());
        }
    }

    public void handleButtonSave(ActionEvent actionEvent)
    {
        try {
            //Get data from textfields and combobox
            String title = txtfTitle.getText();
            String artist = txtfArtist.getText();
            String category = cbxDropDown.getValue();
            String filepath = txtfFile.getText();
            Time duration = Time.valueOf(txtfTime.getText());

            //Calls createNewSong method from SongModel
            this.songModel.createNewSong(title, artist, category, filepath, Time.valueOf(duration.toLocalTime()));
            System.out.println("Song added: " + title + ", " + artist + ", " + category + ", " + "'"+filepath+"'" + ", " + duration);
            songViewController.tblSongs.setItems(songModel.getObservableSongs());
            //Close stage if Save button is clicked
            Node source = (Node) actionEvent.getSource();
            Stage mStage = (Stage) source.getScene().getWindow();
            mStage.close();




        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not add song");
        }


    }

    public void handleButtonCancle (ActionEvent actionEvent)
    {
        //Closes stage if Cancle button is clicked
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

    public Duration getSongLength(File file)
    {
        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration();
    }

        public void handleCancleEdit (ActionEvent event)
        {

        }

        public void handleSaveEdit (ActionEvent event)
        {

        }

    @Override
    public void setup() throws Exception {

    }

    public void setController(SongViewController songViewController)
    {
        this.songViewController=songViewController;
    }
}



