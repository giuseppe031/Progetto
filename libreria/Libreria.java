package libreria;

import libro.Libro;
import libro.StatoLettura;

import java.util.LinkedList;
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
        for(Libro lib : libri){
            if(lib.getISBN().equals(libro.getISBN())){
                rimosso = true;
                libri.remove(lib);
            }
        }
        if(!rimosso) throw new NoSuchElementException("Il libro non è presente dalla libreria");
    }//rimuoviLibro

    public void modificaGenere (Libro libro, String nuovoGenere){
        if(nuovoGenere == null) throw new NoSuchElementException("Inserire un nuovo genere!");
        if (libro.getGenere().equals(nuovoGenere)) throw new IllegalArgumentException("Inserisci un genere differente da quello attuale!");
        ListIterator<Libro> lit = libri.listIterator();
        while(lit.hasNext()){
            Libro lib = lit.next();
            if(lib.getISBN().equals(libro.getISBN())){
                Libro libroMod = new Libro.LibroBuilder(libro.getTitolo(),libro.getAutore(),libro.getISBN()).setGenere(nuovoGenere)
                        .setValutazione(libro.getValutazione()).setStato(libro.getStato()).build();
                lit.set(libroMod);
                break;
            }
        }
    }//modificaGenere

    public void modificaValutazione(Libro libro, String nuovaValutazione){
        if(nuovaValutazione==null) throw new NoSuchElementException("Inserire una nuova valutazione!");
        if(libro.getValutazione().equals(nuovaValutazione)) throw new NoSuchElementException("Inserisci una valutazione differente da quella attuale!");
        ListIterator<Libro> lit = libri.listIterator();
        while(lit.hasNext()){
            Libro lib = lit.next();
            if(lib.getISBN().equals(libro.getISBN())){
                Libro libroMod = new Libro.LibroBuilder(libro.getTitolo(), libro.getAutore(), libro.getISBN())
                        .setGenere(libro.getGenere()).setValutazione(nuovaValutazione).setStato(libro.getStato()).build();
                lit.set(libroMod);
                break;
            }
        }
    }//modificaValutazione

    public void modificaStatoLettura(Libro libro, StatoLettura nuovoStato){
        if(libro.getStato().equals(nuovoStato)) throw new IllegalArgumentException("Inserire uno stato diverso da quello attuale!");
        ListIterator<Libro> lit = libri.listIterator();
        while(lit.hasNext()){
            Libro lib = lit.next();
            if(lib.getISBN().equals(libro.getISBN())){
                Libro libroMod = new Libro.LibroBuilder(libro.getTitolo(),libro.getTitolo(),libro.getISBN())
                        .setGenere(libro.getGenere()).setValutazione(libro.getValutazione()).setStato(nuovoStato).build();
                lit.set(libroMod);
                break;
            }
        }
    }//modificaStatoLettura

}//Libreria
