package brandy.controladores;


import brandy.filtro.Filtros;
import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Filtros filtroDivi;

    @FXML
    private TableView<Partido> tableViewPartidos;

    @FXML
    private ComboBox<Division> cbDivisionMain;

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

    @FXML
    private Button btModifcar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btQuitarFiltro;


    @FXML
    void anadir(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/brandy/vistas/DialogoPartidoFxm.fxml")); // de esta manera
            Parent root = fxmlLoader.load();
            stage.setTitle("Alta Partido");
            stage.setScene(new Scene(root, 1000, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void modificar(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/brandy/vistas/DialogoPartidoFxm.fxml")); // de esta manera
            Parent root = fxmlLoader.load();
            DialogoPartidosFxmControllr controllr = fxmlLoader.getController();
            Partido partido = tableViewPartidos.getSelectionModel().getSelectedItem();
            controllr.setPartidoModificar(partido);
            stage.setTitle("Pantalla Modificar partido");
            stage.setScene(new Scene(root, 1000, 750));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void eliminar(ActionEvent event) {
        tableViewPartidos.getSelectionModel().getSelectedIndices();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Esta a punto de borrar un registro");
        alert.setHeaderText("En caso de que borro este registro no se recuperara");
        alert.setContentText("�Seguro que quiere borrar el registro?");

        alert.showAndWait();
        if ((alert.getResult() == ButtonType.OK)) {

            Logica.getInstance()
                    .getListaPartidos()
                    .remove(tableViewPartidos.getSelectionModel().getSelectedItem());

        }

        else {
        }

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewPartidos.setItems((ObservableList<Partido>) Logica.getInstance().getListaPartidos());

        cbDivisionMain.getItems().addAll(Division.values());
        filtroDivi = new Filtros((ObservableList<Partido>) Logica.getInstance().getListaPartidos());

       // cbDivisionMain.p
        cbDivisionMain.valueProperty().addListener(new ChangeListener<Division>() {
            @Override
            public void changed(ObservableValue<? extends Division> observableValue, Division division, Division t1) {
                tableViewPartidos.setItems(filtroDivi.filtrar(t1));

            }
        });

    }

    private void filtrar()
    {
        tableViewPartidos.setItems(filtroDivi.filtrar(cbDivisionMain.getValue()));
    }


    @FXML
    void quitarFiltro(ActionEvent event) {
        tableViewPartidos.setItems((ObservableList<Partido>) Logica.getInstance().getListaPartidos());

    }

    }

