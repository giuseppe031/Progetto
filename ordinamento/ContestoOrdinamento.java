package ordinamento;

import libreria.Libreria;
import libro.Libro;

import java.util.List;

public class ContestoOrdinamento {
    private OrdinaStrategy strategy;
    public void setOrdinamento(OrdinaStrategy strategy){
        this.strategy = strategy;
    }//setOrdinamento
    public List<Libro> eseguiOrdinamento(List<Libro> libri){
        return strategy.ordina(libri);
    }//eseguiOrdinamento
}//ContestoOrdinamento
