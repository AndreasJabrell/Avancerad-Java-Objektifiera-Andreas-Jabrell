package com.gritacademy.avanceradjavaobjektifieraandreasjabrell;
import javafx.stage.FileChooser;
import java.io.File;

public class Filehandler extends HelloApplication {

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

}
