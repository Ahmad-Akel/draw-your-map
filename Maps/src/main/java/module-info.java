module com.example.maps {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maps to javafx.fxml;
    exports com.example.maps;
}