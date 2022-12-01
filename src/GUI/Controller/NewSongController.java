package src.GUI.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class NewSongController implements Initializable {
    public TextField txtFTtl;
    public ComboBox cbxDropDown;


import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewSongController {
    public TextField txtFTtl;

    public void handleTxtTtl(ActionEvent actionEvent)
    {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboBox<Object> comboBox = new ComboBox<>();
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Option A", "Option B", "Option C");
        comboBox.getSelectionModel().select("Option B");
    }


        public void handleTxtTtl (ActionEvent actionEvent){


        }

        public void handleDropDown (ActionEvent actionEvent){

        }
    }

