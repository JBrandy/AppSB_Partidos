package brandy.logica;

import brandy.models.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import brandy.models.Partido;

import java.util.ArrayList;
import java.util.List;

public class Logica {
	private static Logica INSTANCE = null;
	private List<Partido> listapartidosTotal = new ArrayList<>();
	private ObservableList<Partido> listapartidos = FXCollections
			.observableArrayList(listapartidosTotal);



	private Logica() {
		listapartidos.add(new Partido("a","b",2,2, Division.PRIMERA,"2-2",null));
	}

	public static Logica getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Logica();
		}

		return INSTANCE;
	}

	public void addPartida(Partido partido) {
		this.listapartidosTotal.add(partido);
		this.listapartidos.add(partido);
	}

	public List<Partido> getListapartidos() {
		return this.listapartidos;
	}

	public List<Partido> listapartidosTotal() {
		return this.listapartidosTotal;
	}

	public void modificarPartido(Partido p, int indice) {
		getListapartidos().set(indice, p);
		getListapartidosTotal().set(indice,p);
	}

	private List<Partido> getListapartidosTotal() {
		// TODO Auto-generated method stub
		return this.listapartidosTotal();
	}

	public void setListapartidos(ObservableList<Partido> listapartidos) {
		this.listapartidos = listapartidos;
	}

}
