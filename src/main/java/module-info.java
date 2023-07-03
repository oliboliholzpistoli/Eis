module com.eis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.desktop;


    opens com.eis to javafx.fxml;
    exports com.eis;
}