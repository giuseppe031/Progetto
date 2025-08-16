package libreria;

import filtraggio.ContestoFiltro;
import filtraggio.FiltraPerGenere;
import filtraggio.FiltraPerStatoDiLettura;
import libro.Libro;
import libro.StatoLettura;

import java.util.Enumeration;
import java.util.List;

public class ConsultaLibreria {
    private final ContestoFiltro c_filtro = new ContestoFiltro();

    public List<Libro> filtraPerGenere(String genere){
        c_filtro.setFiltro(new FiltraPerGenere());
        return c_filtro.eseguiFiltraggio(Libreria.INSTANCE.getLibri(),genere);
    }

    public List<Libro> filtraPerStatoDiLettura(StatoLettura stato){
        c_filtro.setFiltro(new FiltraPerStatoDiLettura());
        return c_filtro.eseguiFiltraggio(Libreria.INSTANCE.getLibri(),stato);
    }//filtraPerStatoDiLettura
}//ConsultaLibreria
