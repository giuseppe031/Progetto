package libreria;

import libro.Libro;
import libro.StatoLettura;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public enum Libreria {
    INSTANCE;

    private final LinkedList<Libro> libri = new LinkedList<>();

    public void aggiungiLibro (Libro libro) {
        if(libro == null) throw new IllegalArgumentException("Il libro non può essere null!");
        for (Libro lib : libri)
            if(lib.getISBN().equals(libro.getISBN()))
                throw new IllegalArgumentException("Non possono esistere due libri differenti con lo stesso ISBN!");
        libri.add(libro);
    }//aggiungiLibro

    public void rimuoviLibro (Libro libro) {
        boolean rimosso = false;
        ListIterator<Libro> li = libri.listIterator();
        while(li.hasNext()) {
            Libro lib = li.next();
            if(lib.getISBN().equals(libro.getISBN())) {
                li.remove();
                rimosso = true;
            }
        }
        if(!rimosso) throw new IllegalArgumentException("Il libro non è presente dalla libreria");
    }//rimuoviLibro

    public void modificaGenere (Libro libro, String nuovoGenere){
        if(nuovoGenere == null || nuovoGenere.trim().isEmpty()) throw new NoSuchElementException("Inserire un nuovo genere!");
        for(Libro lib : libri){
            if(lib.getISBN().equals(libro.getISBN())){
                lib.setGenere(nuovoGenere);
            }
        }
    }//modificaGenere

    public void modificaValutazione(Libro libro, String nuovaValutazione){
        if(nuovaValutazione==null || nuovaValutazione.trim().isEmpty()) throw new NoSuchElementException("Inserire una nuova valutazione!");
        for(Libro lib : libri){
            if(lib.getISBN().equals(libro.getISBN())){
                lib.setValutazione(nuovaValutazione);
            }
        }
    }//modificaValutazione

    public void modificaStatoLettura(Libro libro, StatoLettura nuovoStato){
        for(Libro lib : libri){
            if(lib.getISBN().equals(libro.getISBN())){
                lib.setStato(nuovoStato);
            }
        }
    }//modificaStatoLettura

    public void svuotaLibreria(){
        libri.clear();
    }//svuotaLibreria

    public List<Libro> getLibri(){
        return new LinkedList<Libro>(libri);
    }//getLibro
}//Libreria
