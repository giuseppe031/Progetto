package ricerca;

import libro.Libro;

import java.util.List;

public class ContestoRicerca {
    private RicercaStrategy strategy;
    public void setRicerca(RicercaStrategy strategy) {
        this.strategy = strategy;
    }//setRicerca
    public List<Libro> eseguiRicerca(List<Libro> libri,String arg) {
        if(arg==null)
            return libri;
        return strategy.ricerca(libri,arg);
    }//eseguiRicerca
}//ContestoRicerca
