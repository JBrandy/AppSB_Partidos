module PruebaJavaFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports brandy;
    exports brandy.logica;
    exports brandy.controladores;
    exports brandy.models;

    opens brandy.controladores to javafx.fxml;
}