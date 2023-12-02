package com.gritacademy.avanceradjavaobjektifieraandreasjabrell;

import com.eclipsesource.json.JsonArray;
//import com.google.gson.*;
//import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.eclipsesource.json.*;
import javafx.util.Callback;

import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public static File file;
    public TableView<String[]> myTable = new TableView<String[]>();

    public TableView<JsonObject> jsonTable = new TableView<>();
    public ObservableList<String[]> tableViewData = FXCollections.observableArrayList();
    public static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        myTable.setEditable(true);
        stage.setScene(scene);
        stage.show();
    }

    public void CsvFile() { //metod för att få in CSV fil, mycket cred på denna till Joel Bech för hjälp med att få fram och förstå koden
        try {
            File f = new File(String.valueOf(file));//Hämtar värde från chooseFile i Filehandler
            Scanner sc = new Scanner(f);

            if (sc.hasNext()) { //Läser första raden i filen för att få rubriker till headers
                String headerLine = sc.nextLine();
                String[] columnHeaders = headerLine.split(",");

                for (int i = 0; i < columnHeaders.length; i++) { //loop genom varje header för att skapa kolumner i tabellen
                    int columnNumber = i;

                    //Sätter ut rubrikerna
                    TableColumn<String[], String> column = new TableColumn<>(columnHeaders[i]);

                    //CellValue bestämmer vad som finns i kolumnerna
                    column.setCellValueFactory(param -> {
                        //sätter värdet av en kolumn till en lista med rader
                        String[] row = param.getValue();
                        if (row != null && row.length > columnNumber) {
                            return new SimpleStringProperty(row[columnNumber]);
                        } else {
                            return null;
                        }
                    });
                    myTable.getColumns().add(column); //lägger till kolumner i tabell från for loopen
                }
            }

            int maxColumns = 0; //läser in resten av raderna till rows
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] rowData = line.split(",");
                tableViewData.add(rowData);
                if (rowData.length > maxColumns) {
                    maxColumns = rowData.length;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        //Sätter ut allt i tabellen
        myTable.setItems(tableViewData);
    }
    public static void SaveFile(){ //kod vi gick igenom på lektion, tack Alrik!
        System.out.println("Spara");
        FileChooser fileC = new FileChooser();
        fileC.setInitialDirectory(new File("src")); // init path annars C
        fileC.setTitle("Save File");

        fileC.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("json", "*.json"),
                new FileChooser.ExtensionFilter("ALL FILES", "*.*")
        );

        File file = fileC.showSaveDialog(scene.getWindow());
        if (file != null) {
            System.out.println(file.getPath());
            writeFile(file);
        } else  {
            System.out.println("save canceled!!!!"); // or something else
        }
    }
    private static void writeFile(File file) {
        try {
            System.out.println(file.getName());
            String[] sArray= file.getName().split("\\.");

            //String  fileExtention = file.getName().split("\\.")[1];
            String  fileExtention = file.getName().split("\\.")[sArray.length-1];
            System.out.println(fileExtention);

            file.createNewFile(); //empty file med namn

        }catch (IOException io){
            System.out.println(io);
        }
    }
    public static void chooseFile() {
        //metod för att hämta in fil
        FileChooser fileC = new FileChooser();
        fileC.setInitialDirectory(new File("src"));
        fileC.setTitle("Choose file to display");

        fileC.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("json", "*.json"),
                new FileChooser.ExtensionFilter("xml", "*.xml"),
                new FileChooser.ExtensionFilter("ALL FILES", ".")
        );

        file = fileC.showOpenDialog(null);
        if (file != null) {
            System.out.println(file.getPath());
        } else {
            System.out.println("Error");
        }
    }

    public void JsonFile() {

        //PÅBÖRJAT FÖRSÖK TILL ATT MANUELLT FIXA JSON FILEN OCH GÖRA DEN ANVÄNDBAR men det gick inte
        try {
            File f = new File(String.valueOf(file));//hämtar värde från inläst fil
            Scanner sc = new Scanner(f);

            String page = ""; //som CSV, läser första raden för att få fram värde till headers i kolumnerna
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] array = line.split(",");
                //System.out.println(line);
                page += line;
                System.out.println(line);
            }
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] array = line.split(":");
                //System.out.println(line);
                page += line;
                System.out.println(line);
            }
            //HÄR FÅR VI FRAM JSON LISTAN SNYGGT I RADER, VARJE LINE
            //System.out.println(page);
            //System.out.println(Arrays.deepToString(page.split("}")));
            //System.out.println(Arrays.deepToString(page.split("\\{")));
            String[] array = page.split("}");
            for (int i = 0; i < array.length; i++) {

                //System.out.println(array[i]+"\n");
            }

            //ska läsa resten av raderna för att sätta ut värden i raderna i kolumner också
            int maxColumns = 0;
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] rowData = line.split(",");
                tableViewData.add(rowData);
                if (rowData.length > maxColumns) {
                    maxColumns = rowData.length;
                }
            }
            sc.close(); //stänga scanner, alltid bra att göra
        } catch (IOException e) {
            System.out.println(e);
        }
        myTable.setItems(tableViewData); //sätter ut värdet i tabellen
    }
    public JsonObject JsonFile2(){
        try { //cred och tack till Aleksander Bjelk för hjälp och genomgång av kod, hjälp med hur det funkade. Också cred till Alrik då som hjälpte Aleksander
            //en temporär fil
            //File temp = new File(file.getPath());
            Scanner sc = new Scanner(file);
            //Scanner scan = new Scanner(temp);
            String data = "";
            while (sc.hasNext()){
                data += sc.next();
            }

            //parsar
            JsonValue jv = Json.parse(data);
            JsonArray ja = jv.asArray();

            //hämtar dem första namnen för att få dem som kolumn
            JsonObject columnNames = ja.get(0).asObject();

            //skapar kolumner för TableView baserat på namnen i JSON
            for (String columnNameKey : columnNames.names()){
                String columnNameValue = columnNames.get(columnNameKey).asString();
                TableColumn tc = new TableColumn(columnNameValue);

                //definierar hur cellvärden ska hämtas och läggas till i kolumner
                tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<JsonObject, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<JsonObject, String> cellData) {
                        String value = cellData.getValue().get(columnNameKey).asString();
                        return new SimpleStringProperty(value);
                    }
                });
                //lägger till kolumner i tableView
                jsonTable.getColumns().add(tc);
            }

            //skapar listan med alla json som sedan sätts in i TableView
            ObservableList<JsonObject> cells = FXCollections.observableArrayList();
            for (int i = 1; i < ja.size(); i++) {
                JsonObject jo = ja.get(i).asObject();
                cells.add(jo);
            }

            //sätter in raderna
            jsonTable.setItems(cells);
            return columnNames;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    }
