package ricerca;

import libro.Libro;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class RicercaPerAutore implements RicercaStrategy{

    @Override
    public List<Libro> ricerca(List<Libro> libri, String autore) {
        if(autore == null) return libri;
        boolean trovato = false;
        List<Libro> trovati = new LinkedList<>();
        for(Libro lib : libri){
            if(lib.getAutore().equals(autore)){
                trovato = true;
                trovati.add(lib);
            }
        }
        if(!trovato) throw new NoSuchElementException("Autore non trovato");
        return trovati;
    }//ricerca
}//RicercaPerAutore
