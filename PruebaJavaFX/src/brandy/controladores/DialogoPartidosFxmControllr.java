package brandy.controladores;

import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
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
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class DialogoPartidosFxmControllr implements Initializable {

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
            Partido p = new Partido(tfLocal.getText(),tfVisitante.getText(),(Integer.parseInt(tfrLocal.getText())),(Integer.parseInt(tfrVisitante.getText())),cbDivision.getValue(), resultado,date3);
            Logica.getInstance().modificarPartido(p,indiceModificar);
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

    public void setPartidoModificar(Partido partido, int indice) {
        this.partidoModificar = partido;
        this.indiceModificar = indice;
        tfLocal.setText(partidoModificar.getEquipo_Local());
        tfVisitante.setText(partidoModificar.getEquipo_Visitante());
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
            partidoModificar.setDate(date3);
        }



    }
}