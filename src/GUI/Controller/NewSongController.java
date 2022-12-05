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
import src.DAL.db.DatabaseConnector;
import src.GUI.Model.SongModel;


public class NewSongController {

    private SongModel songModel;

    public ComboBox<String> cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle, getBtnSaveEdit;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;
    public TextField txtTitle;
    public TextField txtArtist;
    public Button btnSaveCancle;
    public Button btnSaveEdit;
    public Button btnSaveCancel;
    private DatabaseConnector databaseConnector;

    public NewSongController() {
        try {
            songModel = new SongModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
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
            FileChooser mFileChooser = new FileChooser();
            File mFile = mFileChooser.showOpenDialog(null);
            //txtfTime.setText(String.valueOf(mFile.length()));
            System.out.println("Selected file " + mFile);
            txtfFile.setText(mFile.getName());
            //System.out.println(getSongLength(mFile).toString());

        }
    }

    public String getSongLength(File file) {

        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration().toString();

    }

    public void handleButtonSave(ActionEvent actionEvent)
    {
        try {
            String title = txtfTitle.getText();
            String artist = txtfArtist.getText();
            String category = cbxDropDown.getId();
            String filepath = txtfFile.getText();
            Time duration = Time.valueOf(txtfTime.getText());

            System.out.println("Creating:  " + "title" + " ; " + "artist" + ";" + "category" + ";" + "filepath" + ";" + "duration");

            this.songModel.createNewSong(title, artist, category, filepath, Time.valueOf(duration.toLocalTime()));

            System.out.println("Song " + title + " Created");

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Song couldn't be saved");
        }
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



