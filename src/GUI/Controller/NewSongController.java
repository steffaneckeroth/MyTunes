package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.sql.*;


import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import src.BE.Artist;
import src.BE.Category;
import src.BE.Song;
import src.DAL.db.DatabaseConnector;
import src.GUI.Model.SongModel;


import javax.sound.sampled.UnsupportedAudioFileException;


public class NewSongController {

    public ComboBox<String> cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;

    private SongModel songModel;

    private DatabaseConnector databaseConnector;


    public void initialize()
    {
        //cbxDropDown.getItems().removeAll(cbxDropDown.getItems());
        cbxDropDown.getItems().addAll("Pop", "Hiphop", "Rock");
        cbxDropDown.getSelectionModel().select("---");

    }


    public void handleTxtTtl(ActionEvent actionEvent) {
        this.txtfTitle.getText();
    }

    public void handleCategory(ActionEvent actionEvent) {

    }

    public void handleButtonChoose(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnChoose) {
            FileChooser file_upload = new FileChooser();
            File add_2 = file_upload.showOpenDialog(null);
            //txtfTime.setText(String.valueOf(add_2.length()));
            System.out.println("Selected file " + add_2);
            txtfFile.setText(add_2.getName());
            //System.out.println(getSongLength(add_2).toString());

        }
    }

    public String getSongLength(File file) {

        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration().toString();

    }

    public Song handleButtonSave(ActionEvent actionEvent) throws Exception {





        String title = txtfTitle.getText();
        String artist = txtfArtist.getText();
        Category category = (Category) cbxDropDown.getItems();
        String filepath = txtfFile.getText();
        String duration = txtfTime.getText();

        //Artist mArtist = new Artist(0, artist);
        //Category mCategory = new Category(1, category);

        try {
            songModel.createNewSong(title, artist, category, filepath, Time.valueOf(duration));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return null;
    }


        public void handleButtonCancle (ActionEvent actionEvent)
        {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

        public void handleCancleEdit (ActionEvent event){
        }

        public void handleSaveEdit (ActionEvent event){


        }

    }
