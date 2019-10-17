package brandy.controladores;


import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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


    @FXML
    void generar(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/brandy/vistas/DialogoPartidoFxm.fxml")); // de esta manera
            Parent root = fxmlLoader.load();
            stage.setTitle("Pantalla principal");
            stage.setScene(new Scene(root, 300, 300));
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
            stage.setTitle("Pantalla alta partido");
            stage.setScene(new Scene(root, 300, 300));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableViewPartidos.setItems((ObservableList<Partido>) Logica.getInstance().getListapartidos());



    }



    }

