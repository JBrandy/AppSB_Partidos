package brandy.controladores;


import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Partido> tableViewPartidos;

    @FXML
    private TableColumn<?, ?> equipo_Local;

    @FXML
    private TableColumn<?, ?> equipo_Visitante;

    @FXML
    private TableColumn<?, ?> resultado_Lo;

    @FXML
    private TableColumn<?, ?> resultado_Vi;

    @FXML
    private TableColumn<?, ?> division;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Button btAnadir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableViewPartidos.setItems((ObservableList<Partido>) Logica.getInstance().getListapartidos());



    }


    }

