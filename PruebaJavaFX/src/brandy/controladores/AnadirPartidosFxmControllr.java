package brandy.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AnadirPartidosFxmControllr {

    @FXML
    private TextField tfLocal;

    @FXML
    private TextField tfVisitante;

    @FXML
    private TextField tfrLocal;

    @FXML
    private TextField tfrVisitante;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<?> division;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    void addPartido(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

}