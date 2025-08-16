package filtraggio;

import libro.Libro;

import java.util.List;

public class ContestoFiltro {
    private FiltraStrategy filtro;
    public void setFiltro(FiltraStrategy filtro) {
        this.filtro = filtro;
    }
    public List<Libro> eseguiFiltraggio (List<Libro> libri, Object arg) {
        if(arg == null)
            return libri;
        return filtro.filtra(libri,arg);
    }//eseguiFiltraggio
}//ContestoFiltro
