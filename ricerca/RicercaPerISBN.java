package ricerca;

import libro.Libro;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class RicercaPerISBN implements RicercaStrategy{

    @Override
    public List<Libro> ricerca(List<Libro> libri, String ISBN) {
       if(ISBN == null) return libri;
       boolean trovato = false;
       List<Libro> trovati = new LinkedList<>();
       for(Libro lib : libri){
           if(lib.getISBN().equalsIgnoreCase(ISBN)){
               trovato=true;
               trovati.add(lib);
           }

       }
       if(!trovato) throw new NoSuchElementException("ISBN non trovato");
       return trovati;
    }//ricerca

}//RicercaPerISBN
