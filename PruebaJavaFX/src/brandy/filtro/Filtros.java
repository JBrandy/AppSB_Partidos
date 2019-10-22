package brandy.filtro;
import brandy.logica.Logica;
import brandy.models.Division;
import brandy.models.Partido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Filtros {
    private ObservableList<Partido> listaPartidos;
    private ObservableList<Partido> listaFiltrada;

    public Filtros(ObservableList<Partido> listaPersonas) {
        this.listaPartidos = listaPartidos;
        listaFiltrada = FXCollections.observableArrayList();
    }

    public ObservableList<Partido> filtrar(Division division)
    {
        if (division!=null && !"".equals(division))
        {
            //Necesitamos filtrar
            listaFiltrada.clear();
            for (Partido partido : Logica.getInstance().getListaPartidos())
            {
                if (partido.getDivision().equals(division))
                    listaFiltrada.add(partido);
            }
            return listaFiltrada;
        }
        else
        {
            //Tenemos que mostrar todos los registros
            return listaPartidos;
        }

    }
}