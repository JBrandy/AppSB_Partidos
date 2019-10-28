package brandy.controladores;

import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class DialogoPartidosFxmControllr extends  BaseController implements Initializable {

    private Partido partidoModificar;
    private int indiceModificar;
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
    private ComboBox<Division> cbDivision;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbDivision.getItems().add(Division.PRIMERA);
        cbDivision.getItems().add(Division.SEGUNDA);
        cbDivision.getItems().add(Division.TERCERA);
        ValidationSupport vs = new ValidationSupport();
        vs.registerValidator(tfLocal, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tfVisitante, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tfrLocal, Validator.createEmptyValidator("el campo no puede estar vacio"));
        vs.registerValidator(tfrVisitante, Validator.createEmptyValidator("el campo no puede estar vacio"));

      /*  vs.invalidProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                btAceptar.setDisable(newValue);
            }
        });*/

        // usar uno u otro.
        btAceptar.disableProperty().bind(vs.invalidProperty());
       // cbDivision.getItems().addAll(Division.values()); Esta seria la manera correcta
    }


    @FXML
    void addPartido(ActionEvent event) {
        LocalDate localDate = (LocalDate) date.getValue();
        Date date3 = null;
        if(localDate!=null) {
            date3 = Date.from(localDate.atStartOfDay(
                    ZoneId.systemDefault()).toInstant());
        }
        int resultado_Lo = Integer.parseInt(tfrLocal.getText());
        int resultado_Vi = Integer.parseInt(tfrVisitante.getText());
        String sA = String.valueOf(resultado_Lo);
        String sB = String.valueOf(resultado_Vi);
        String resultado = sA + "-" + sB;
        if(partidoModificar!=null){
            partidoModificar.setEquipo_Local(tfLocal.getText());
            partidoModificar.setEquipo_Visitante(tfVisitante.getText());
            partidoModificar.setResultado_Vi(Integer.parseInt(tfrVisitante.getText()));
            partidoModificar.setResultado_Lo(Integer.parseInt(tfrLocal.getText()));
            partidoModificar.setDivision(cbDivision.getValue());
            partidoModificar.setResultado(resultado);
            partidoModificar.setDate(date3);

          //  Partido p = new Partido(tfLocal.getText(),tfVisitante.getText(),(Integer.parseInt(tfrLocal.getText())),(Integer.parseInt(tfrVisitante.getText())),cbDivision.getValue(), resultado,date3);
            Logica.getInstance().modificarPartido(partidoModificar);
        }
        else{
            Partido p = new Partido(tfLocal.getText(),tfVisitante.getText(),(Integer.parseInt(tfrLocal.getText())),(Integer.parseInt(tfrVisitante.getText())),cbDivision.getValue(), resultado,date3);
            Logica.getInstance().addPartida(p);
            Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            stage.close();
        }
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();

    }


    
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

    public void setPartidoModificar(Partido partido) {
        this.partidoModificar = partido;
        tfLocal.setText(partido.getEquipo_Local());
        tfVisitante.setText(partido.getEquipo_Visitante());
        tfrLocal.setText(String.valueOf(partido.getResultado_Lo()));
        tfrVisitante.setText(String.valueOf(partido.getResultado_Vi()));
        cbDivision.getSelectionModel().select(partido.getDivision());
        String sA = String.valueOf(tfLocal);
        String sB = String.valueOf(tfrVisitante);
        String resultado = sA + "-" + sB;
        partidoModificar.setResultado(resultado);
        LocalDate localDate = (LocalDate) date.getValue();
        if(localDate!=null) {
            Date date3 = Date.from(localDate.atStartOfDay(
                    ZoneId.systemDefault()).toInstant());
            partido.setDate(date3);
        }



    }

    public void addValidator() {
        final String pattern = "^(\\d)+(\\.?\\d*)?$";
        final int minimo = 0;

        tfrLocal.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.V) {// Esto es para impedir el Ctrl + V de texto en los campos numéricos
                String textToPaste = Clipboard.getSystemClipboard().getString();

                if (textToPaste != null && !textToPaste.isEmpty()) {
                    String newText = tfrLocal.getText() + textToPaste;

                    if (!newText.matches(pattern) || Double.parseDouble(newText) <= minimo) {
                        event.consume();
                    }
                } else {
                    event.consume();
                }
            }
        });

        tfrLocal.setOnKeyTyped(event -> {
            String newText = tfrLocal.getText() + event.getCharacter();
            if (!newText.matches(pattern) || Double.parseDouble(newText) <= minimo) {
                event.consume();
            }
        });
    }
}