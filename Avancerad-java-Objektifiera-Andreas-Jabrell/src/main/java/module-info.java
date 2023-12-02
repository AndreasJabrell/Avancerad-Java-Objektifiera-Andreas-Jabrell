module com.gritacademy.avanceradjavaobjektifieraandreasjabrell {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires minimal.json;


    opens com.gritacademy.avanceradjavaobjektifieraandreasjabrell to javafx.fxml;
    exports com.gritacademy.avanceradjavaobjektifieraandreasjabrell;
}