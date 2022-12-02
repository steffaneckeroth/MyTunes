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
import src.DAL.db.SongDAO_DB;
import src.GUI.Model.SongModel;

import javax.sound.sampled.UnsupportedAudioFileException;




public class NewSongController {

    private SongModel songModel;

    public ComboBox cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle, getBtnSaveEdit;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;
    public TextField txtTitle;
    public TextField txtArtist;
    public Button btnSaveCancle;
    public Button btnSaveEdit;
    public Button btnSaveCancel;
    private DatabaseConnector databaseConnector;


    public void handleTxtTtl(ActionEvent actionEvent) {
        this.txtfTitle.getText();
    }

    public void handleCategory(ActionEvent actionEvent) {

    }

    public void handleButtonChoose(ActionEvent actionEvent) throws UnsupportedAudioFileException, IOException {
        if (actionEvent.getSource() == btnChoose) {
            FileChooser file_upload = new FileChooser();
            File add_2 = file_upload.showOpenDialog(null);
            txtfTime.setText(String.valueOf(add_2.length()));
            System.out.println("Selected file " + add_2);
            txtfTitle.setText(add_2.getName());
            System.out.println(getSongLength(add_2).toString());

        }
    }

    public String getSongLength(File file) {

        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration().toString();

    }

    public Song handleButtonSave(ActionEvent actionEvent) throws Exception {


        String sql = "INSERT INTO Song (Title) VALUES (txtfTitle);";

        try(Connection conn = databaseConnector.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            String title = String.valueOf(txtfTitle);
            stmt.setString(1, title);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next())
            {
                id = rs.getInt(1);
            }

            Song mSong = new Song(1, title, null, null, null, null);
            return mSong;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new Exception("Could not create song", ex);
        }



      /*  String title = txtfTitle.getText();

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


        String artist = txtfArtist.getText();
        String category = cbxDropDown.getId();
        String filepath = txtfFile.getText();
        String duration = txtfTime.getText();

        Artist mArtist = new Artist(0, artist);
        Category mCategory = new Category(1, category);

        try {
            songModel.createNewSong(title, mArtist, mCategory, filepath, Time.valueOf(duration));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
            */

    }


        public void handleButtonCancle (ActionEvent actionEvent){

            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

        public void handleCancleEdit (ActionEvent event){
        }

        public void handleSaveEdit (ActionEvent event){


        }

    }
