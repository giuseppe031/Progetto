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
       try(CSVWriter writer = new CSVWriter(new FileWriter(file),CSVWriter.DEFAULT_SEPARATOR,
               CSVWriter.NO_QUOTE_CHARACTER,
               CSVWriter.DEFAULT_ESCAPE_CHARACTER,
               CSVWriter.DEFAULT_LINE_END)) {

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
               writer.writeNext(riga);
           }

       }catch(IOException e){
           System.out.println("Errore durante il salvataggio"+ e.getMessage());
       }
    }//salva

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
                Libreria.INSTANCE.aggiungiLibro(b.build());
            }
        }catch(IOException e1){
            System.out.println("Errore durante il caricamento"+ e1.getMessage());
        }
    }//carica

}//PersistenzaImpl
