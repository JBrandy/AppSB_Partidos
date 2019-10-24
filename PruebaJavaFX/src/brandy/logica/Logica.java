package brandy.logica;

import brandy.models.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import brandy.models.Partido;

import java.util.List;

public class Logica {
	private static Logica INSTANCE = null;
	private ObservableList<Partido> listaPartidos = FXCollections
			.observableArrayList();


	private Logica() {
		listaPartidos.add(new Partido("a","b",2,2, Division.PRIMERA,"2-2",null));

	}
// acuerdate de quitar el-1 en el modoficar partido


	public static Logica getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Logica();
		}

		return INSTANCE;
	}

	public void addPartida(Partido partido) {
		this.listaPartidos.add(partido);
	}

	public List<Partido> getListaPartidos() {
		return this.listaPartidos;
	}


	public void modificarPartido(Partido p) {
		int indice = listaPartidos.indexOf(p);
		listaPartidos.set(indice, p);
	}



	public void setListaPartidos(ObservableList<Partido> listaPartidos) {
		this.listaPartidos = listaPartidos;
	}

}
