package brandy.controladores;

import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DialogoPartidosFxmControllr {

    private Partido p;
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
    private ComboBox<Division> division;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    void addPartido(ActionEvent event) {
    if(p!=null){
            //meter aqui el modificar
    }
    else{
        LocalDate localDate = (LocalDate) date.getValue();
        Date date3 = Date.from(localDate.atStartOfDay(
                ZoneId.systemDefault()).toInstant());

        int resultado_Lo = Integer.parseInt(tfrLocal.getText());
        int resultado_Vi = Integer.parseInt(tfrVisitante.getText());

        String sA = String.valueOf(resultado_Lo);
        String sB = String.valueOf(resultado_Vi);
        String resultado = sA + "-" + sB;
        Partido p = new Partido(tfLocal.getText(),tfVisitante.getText(),(Integer.parseInt(tfrLocal.getText())),(Integer.parseInt(tfrVisitante.getText())),division.getValue(), resultado,date3);
        Logica.getInstance().addPartida(p);
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();

    }



    }

    public void setPartidoModificar(Partido p, int indice ){
        this.p=p;


    }



    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.close();
    }

}