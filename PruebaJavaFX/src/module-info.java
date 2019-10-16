module PruebaJavaFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports brandy;
    exports brandy.logica;
    exports brandy.controladores;
    exports brandy.models;

    opens brandy.controladores to javafx.fxml;
}