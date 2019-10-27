package brandy;

import brandy.logica.Logica;
import brandy.models.Partido;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Launcher extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vistas/mainWinfxm.fxml"));
        stage.setTitle("Pantalla principal");
        stage.setScene(new Scene(root, 1000, 750));
        stage.show();

    }

    public static void main(String args[])
    {
        leerFichero();

        launch(args);

        guardarFichero();
    }

    public static void guardarFichero() {
        ArrayList<Partido> ficheroAuxGuardar = new ArrayList<Partido>();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos= new FileOutputStream("src\\bbdd.dat");
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

    public static void leerFichero() {
        ArrayList<Partido> listaAuxLeer = new ArrayList<Partido>();

        FileInputStream fis = null;
        ObjectInputStream ois = null;


        try {

            fis = new FileInputStream("src\\bbdd.dat");
            ois = new ObjectInputStream(fis);

          listaAuxLeer = (ArrayList<Partido>) ois.readObject();
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