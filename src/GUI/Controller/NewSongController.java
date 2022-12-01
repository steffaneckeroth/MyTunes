package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;


import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.UnsupportedAudioFileException;




public class NewSongController {

    public ComboBox cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;
    public TextField txtTitle;
    public TextField txtArtist;
    public Button btnSaveCancle;
    public Button btnSaveEdit;
    public Button btnSaveCancel;


    public void handleTxtTtl(ActionEvent actionEvent) {
    this.txtfTitle.getText();
    }

    public void handleCategory(ActionEvent actionEvent) {

    }

    public void handleButtonChoose(ActionEvent actionEvent) throws UnsupportedAudioFileException, IOException
    {
        if (actionEvent.getSource() == btnChoose) {
            FileChooser file_upload = new FileChooser();
            File add_2 = file_upload.showOpenDialog(null);
            txtfTime.setText(String.valueOf(add_2.length()));
            System.out.println("Selected file "+ add_2);
            txtfTitle.setText(add_2.getName());
            System.out.println(getSongLength(add_2).toString());

        }
    }

    public String getSongLength(File file) {

        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration().toString();

    }
    public void handleButtonSave(ActionEvent actionEvent) {

    }

    public void handleButtonCancle(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleCancleEdit(ActionEvent event) {
    }

    public void handleSaveEdit(ActionEvent event) {

    }
}