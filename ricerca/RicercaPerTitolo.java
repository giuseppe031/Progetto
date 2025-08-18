package ricerca;

import libro.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RicercaPerTitolo implements RicercaStrategy{

    @Override
    public List<Libro> ricerca(List<Libro> libri, String titolo) {
       if(titolo == null) return libri;
       boolean trovato = false;
       List<Libro> trovati = new ArrayList<Libro>();
       for(Libro lib : libri) {
           if(lib.getTitolo().equals(titolo)){
               trovati.add(lib);
               trovato = true;
           }
       }
       if(!trovato) throw new NoSuchElementException("Titolo non trovato");
       return trovati;
    }//ricerca

}//RicercaPerTitolo
