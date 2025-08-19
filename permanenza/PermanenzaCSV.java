package permanenza;

import libreria.Libreria;
import libro.Libro;
import libro.StatoLettura;

import java.io.*;

public class PermanenzaCSV implements PermanenzaStrategy {

    @Override
    public void salva(File file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(Libro lib: Libreria.INSTANCE.getLibri()){
                String stato=lib.getStato().toString();
                writer.write(
                        lib.getTitolo()+","+ lib.getAutore()+","+ lib.getISBN()+","+lib.getGenere()+","+ lib.getValutazione()+","+stato
                );
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Errore durante il salvataggio.");
        }
    }//salva

    @Override
    public void carica(File file) {
        if(!file.exists()) throw new IllegalArgumentException("File non trovato");
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null){
                if(line.trim().length()==0)
                    continue;
                String[] campi = line.split(",");
                StatoLettura stato = StatoLettura.valueOf(campi[5]);
                Libro libro = new Libro.LibroBuilder(campi[0],campi[1],campi[2]).setGenere(campi[3]).setValutazione(campi[4]).setStato(stato).build();
                try{
                    Libreria.INSTANCE.aggiungiLibro(libro);
                }catch(IllegalArgumentException e){
                    System.out.println("Il libro è già presente nella libreria.");
                }
            }
            br.close();
            fr.close();
        }catch(IOException e){
            System.out.println("Errore durante il caricamento.");
        }
    }//carica

}//PersistenzaImpl
