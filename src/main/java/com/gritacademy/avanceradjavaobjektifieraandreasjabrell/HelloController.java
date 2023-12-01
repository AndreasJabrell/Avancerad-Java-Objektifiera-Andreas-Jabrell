package com.gritacademy.avanceradjavaobjektifieraandreasjabrell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class HelloController extends HelloApplication{

    @FXML
    private Button btnOpen;

    @FXML
    private TableView<?> myTable;

    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Label welcomeText;

    @FXML
    void onButtonOpen(ActionEvent event) {
        System.out.println("öppet");
        Filehandler.chooseFile();
        CsvFile();
        //JsonFile();
    }

    @FXML
    void onButtonReset(ActionEvent event) {
        myTable.getColumns().clear();
        myTable.getItems().clear();
        System.out.println("Rensat!");
    }

    @FXML
    void onButtonSave(ActionEvent event) {

        System.out.println("sparat");
    }

}
