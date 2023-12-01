package com.gritacademy.avanceradjavaobjektifieraandreasjabrell;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    public static File file;
    public TableView<String[]> myTable = new TableView<String[]>();
    public ObservableList<String[]> tableViewData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        myTable.setEditable(true);
        stage.setScene(scene);
        stage.show();
    }

    public void CsvFile() { //metod för att få in CSV fil
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
}