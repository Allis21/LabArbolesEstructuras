module org.example.labarbolesestructuras {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens org.example.labarbolesestructuras to javafx.fxml;
    exports org.example.labarbolesestructuras;
}