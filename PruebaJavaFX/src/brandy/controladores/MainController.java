package brandy.controladores;


import brandy.Launcher;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
    private Button btcargar;

    @FXML
    private Button btGuardar;



    @FXML
    void cargar(ActionEvent event) {

        Stage stage = new Stage();
        stage.setTitle("Ejemplo FileChooser");
        FileChooser fileChooser = new FileChooser();
        //Este paso es opcional, para dejar al usuario solo seleccionar ciertos tipos de archivo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos .dat (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            //Hacer lo que queramos con el archivo. Normalmente leerlo y cargarlo.
            ArrayList<Partido> listaAuxLeer = new ArrayList<Partido>();

            FileInputStream fis = null;
            ObjectInputStream ois = null;

            try {

                fis = new FileInputStream(file.getAbsolutePath());
                ois = new ObjectInputStream(fis);

                listaAuxLeer = (ArrayList<Partido>) ois.readObject();
                Logica.getInstance().getListaPartidos().clear();
                Logica.getInstance().getListaPartidos().addAll(listaAuxLeer);

            }
            catch(Exception e){
                e.printStackTrace();
            }finally{


                try{
                    if( null != fis ){
                        fis.close();
                    }
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
        }





    @FXML
    void guardar(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Ejemplo FileChooser");
        FileChooser fileChooser = new FileChooser();
        //Este paso es opcional, para dejar al usuario solo seleccionar ciertos tipos de archivo
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos .dat (*.dat)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            //Hacer lo que queramos con el archivo.

            ArrayList<Partido> ficheroAuxGuardar = new ArrayList<Partido>();
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos= new FileOutputStream(file.getAbsolutePath());
                oos = new ObjectOutputStream(fos);
                ficheroAuxGuardar.addAll(Logica.getInstance().getListaPartidos());
                oos.writeObject(ficheroAuxGuardar);
                oos.flush();


            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{


                try{
                    if( null != fos ){
                        fos.close();
                    }
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    }


    @FXML
    void anadir(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/brandy/vistas/DialogoPartidoFxm.fxml")); // de esta manera
            Parent root = fxmlLoader.load();
            stage.setTitle("Alta Partido");
            stage.setScene(new Scene(root, 700, 400));
            stage.showAndWait();
            filtrar();

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
            stage.setScene(new Scene(root, 700, 400));
            stage.showAndWait();
            filtrar();
            stage.setOnCloseRequest(e -> cargarFichero());
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
    public  void cargarFichero() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos= new FileOutputStream("src\\bbdd.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject(Logica.getInstance().getListaPartidos());
            oos.flush();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{


            try{
                if( null != fos ){
                    fos.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    }

