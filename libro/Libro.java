package libro;

import java.util.NoSuchElementException;

public class Libro {

    private final String titolo;
    private final String autore;
    private final String ISBN;
    private  String genere;
    private  String valutazione;
    private  StatoLettura stato;

    public static class LibroBuilder{
       //parametri principali:
        private final String titolo;
        private final String autore;
        private final String ISBN;
        //parametri secondari
        private  String genere = "";
        private  String valutazione = "";
        private StatoLettura stato;

        public LibroBuilder(String titolo, String autore, String ISBN){
            if(titolo == null || titolo.isEmpty() || autore == null || autore.isEmpty() || ISBN == null || ISBN.isEmpty()) throw new IllegalArgumentException();
            this.titolo = titolo;
            this.autore = autore;
            this.ISBN = ISBN;
        }

        public LibroBuilder setGenere(String valore){
            if(valore == null) throw new NoSuchElementException();
            genere = valore;
            return this;
        }//setGenere

        public LibroBuilder setValutazione(String valore){
            if(valore == null) throw new NoSuchElementException();
            valutazione = valore;
            return this;
        }//setValutazione

        public LibroBuilder setStato(StatoLettura stato){
            this.stato = stato;
            return this;
        }//setStato

        public LibroBuilder setDaLeggere(){
            stato = StatoLettura.DA_LEGGERE;
            return this;
        }//setDaLeggere

        public LibroBuilder setInLettura(){
            stato = StatoLettura.IN_LETTURA;
            return this;
        }//setInLettura

        public LibroBuilder setLetto(){
            stato = StatoLettura.LETTO;
            return this;
        }//setLetto
        public Libro build(){
            return new Libro(this);
        }//build
    }//LibroBuilder

    private Libro (LibroBuilder builder){
        this.titolo = builder.titolo;
        this.autore = builder.autore;
        this.ISBN = builder.ISBN;
        this.genere = builder.genere;
        this.valutazione = builder.valutazione;
        this.stato = builder.stato;
    }

    public void setGenere(String valore){
        if(valore == null) throw new IllegalArgumentException();
        this.genere = valore;
    }//setGenere

    public void setValutazione(String valore){
        if(valore == null) throw new IllegalArgumentException();
        this.valutazione = valore;
    }//setValutazione

    public void setStato(StatoLettura stato){
        this.stato = stato;
    }//setStato

    public String getTitolo() {
        return titolo;
    }//getTitolo

    public String getAutore() {
        return autore;
    }//getAutore

    public String getISBN() {
        return ISBN;
    }//getISBN

    public String getGenere() {
        return genere;
    }//getGenere

    public String getValutazione() {
        return valutazione;
    }//getValutazione

    public StatoLettura getStato() {
        return stato;
    }//getStato
}//Libro
