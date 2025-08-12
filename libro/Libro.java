package libro;

public class Libro {

    private final String titolo;
    private final String autore;
    private final String ISBN;
    private final String genere;
    private final String valutazione;
    private enum StatoLettura{
        DA_LEGGERE, IN_LETTURA, LETTO
    }
    private final StatoLettura stato;

    public static class LibroBuilder{
       //parametri principali:
        private final String titolo;
        private final String autore;
        private final String ISBN;
        //parametri secondari
        private  String genere = " ";
        private  String valutazione = " ";
        private StatoLettura stato;

        public LibroBuilder(String titolo, String autore, String ISBN){
            this.titolo = titolo;
            this.autore = autore;
            this.ISBN = ISBN;
        }

        public LibroBuilder setGenere(String valore){
            genere = valore;
            return this;
        }

        public LibroBuilder setValutazione(String valore){
            valutazione = valore;
            return this;
        }

        public LibroBuilder setDaLeggere(){
            stato = StatoLettura.DA_LEGGERE;
            return this;
        }

        public LibroBuilder setInLettura(){
            stato = StatoLettura.IN_LETTURA;
            return this;
        }

        public LibroBuilder setLetto(){
            stato = StatoLettura.LETTO;
            return this;
        }
        public Libro build(){
            return new Libro(this);
        }
    }//LibroBuilder

    public Libro (LibroBuilder builder){
        this.titolo = builder.titolo;
        this.autore = builder.autore;
        this.ISBN = builder.ISBN;
        this.genere = builder.genere;
        this.valutazione = builder.valutazione;
        this.stato = builder.stato;
    }

}//Libro
