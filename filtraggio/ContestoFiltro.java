package filtraggio;

import libro.Libro;

import java.util.List;

public class ContestoFiltro {
    private FiltraStrategy strategy;
    public void setFiltro(FiltraStrategy filtro) {
        this.strategy = filtro;
    }
    public List<Libro> eseguiFiltraggio (List<Libro> libri, Object arg) {
        if(arg == null)
            return libri;
        return strategy.filtra(libri,arg);
    }//eseguiFiltraggio
}//ContestoFiltro
