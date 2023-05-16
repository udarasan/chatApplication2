module com.example.chatapplication2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatapplication2 to javafx.fxml;
    exports com.example.chatapplication2;
}