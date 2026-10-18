module org.example.guessthenumbergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.guessthenumbergui to javafx.fxml;
    opens org.example.guessthenumbergui.controller to javafx.fxml;
    exports org.example.guessthenumbergui;
    exports org.example.guessthenumbergui.controller;

}