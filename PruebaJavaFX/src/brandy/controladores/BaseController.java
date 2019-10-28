package brandy.controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {

    protected Stage getStage(){
        return  null;
    }


    // para abrir un dialogo con showWhite se pasara con un bollean para escger si queremos usarlo
    protected void abriVentana(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/brandy/vistas/DialogoPartidoFxm.fxml")); // de esta manera
        Parent root = null;
        try {
            root = fxmlLoader.load();
            Stage stage= new Stage();
            stage.setTitle("Alta Partido");
            stage.setScene(new Scene(root, 1000, 600));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
