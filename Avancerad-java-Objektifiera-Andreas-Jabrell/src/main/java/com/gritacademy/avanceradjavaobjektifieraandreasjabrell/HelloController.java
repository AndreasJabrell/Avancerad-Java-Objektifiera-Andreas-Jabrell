package com.gritacademy.avanceradjavaobjektifieraandreasjabrell;

import com.eclipsesource.json.JsonObject;
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
    private TableView<JsonObject> table;
    @FXML
    private TableView<?> jsonTable;
    @FXML
    private Button reset;

    @FXML
    private Button save;

    @FXML
    private Label welcomeText;


    @FXML
    void onButtonOpen(ActionEvent event) {
        System.out.println("Öppna fil");
        chooseFile();;
        String fileName = file.getName();
        if (fileName.endsWith("csv")){
            CsvFile();
        }else{
            JsonFile2();
        }
        //JsonFile();

    }

    @FXML
    void onButtonReset(ActionEvent event) {
jsonTable.getColumns().clear();           myTable.getColumns().clear();
jsonTable.getItems().clear();                          myTable.getItems().clear();
        System.out.println("Rensat!");
    }

    @FXML
    void onButtonSave(ActionEvent event) {
        SaveFile();
        System.out.println("sparat");
    }

}
