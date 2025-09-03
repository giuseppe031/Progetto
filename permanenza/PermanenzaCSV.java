package permanenza;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;
import libreria.Libreria;
import libro.Libro;
import libro.StatoLettura;

public class PermanenzaCSV implements PermanenzaStrategy {

    @Override
    public void salva(File file) {
       try(CSVWriter writer = new CSVWriter(new FileWriter(file))) {
           List<Libro> libri = Libreria.INSTANCE.getLibri();
           for(Libro lib : libri) {
               String titolo = lib.getTitolo();
               String autore = lib.getAutore();
               String isbn = lib.getISBN();
               String genere = "";
               if(lib.getGenere()!=null){
                   genere = lib.getGenere();
               }
               String valutazione = "";
               if(lib.getValutazione()!=null){
                   valutazione = lib.getValutazione();
               }
               String stato = "";
               if(lib.getStato()!=null){
                   stato = lib.getStato().toString();
               }

               String[] riga = {titolo, autore, isbn, genere, valutazione, stato};
               //if(presente(file,riga[2])) throw new IllegalArgumentException("L'ISBN è già presente nel file");
               writer.writeNext(riga);
           }

       }catch(IOException e){
           System.out.println("Errore durante il salvataggio"+ e.getMessage());
       }
    }//salva

    public boolean presente(File file, String isbn){
        if(!file.exists()) return false;
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            String riga[];
            while((riga = reader.readNext())!=null){
                if(riga[2].equals(isbn)) return true;
            }
        }catch(Exception e3){
            System.out.println("Errore: "+ e3.getMessage());
        }
        return false;
    }//presente

    @Override
    public void carica(File file) throws IOException, CsvValidationException {
        if(!file.exists()){
            throw(new IOException("File non trovato"));
        }
        try(CSVReader reader = new CSVReader(new FileReader(file))){
            String[] riga;

            while((riga = reader.readNext())!=null){
                String titolo = riga[0];
                String autore = riga[1];
                String isbn = riga[2];
                String genere = riga[3];
                String valutazione = riga[4];
                String stato = riga[5];

                Libro.LibroBuilder b = new Libro.LibroBuilder(titolo,autore,isbn);
                b.setGenere(genere).setValutazione(valutazione).setStato(StatoLettura.valueOf(stato));
                Libro lib = b.build();
                if(!Libreria.INSTANCE.contiene(lib))
                    Libreria.INSTANCE.aggiungiLibro(lib);
            }
        }catch(IOException e1){
            System.out.println("Errore durante il caricamento"+ e1.getMessage());
        }
    }//carica

}//PersistenzaImpl
