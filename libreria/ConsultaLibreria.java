package libreria;

import filtraggio.ContestoFiltro;
import filtraggio.FiltraPerGenere;
import filtraggio.FiltraPerStatoDiLettura;
import libro.Libro;
import libro.StatoLettura;
import ordinamento.ContestoOrdinamento;
import ordinamento.OrdinaPerTitolo;
import ordinamento.OrdinaPerValutazione;
import permanenza.ContestoPermanenza;
import permanenza.PermanenzaStrategy;
import permanenza.PermanenzaCSV;
import ricerca.ContestoRicerca;
import ricerca.RicercaPerAutore;
import ricerca.RicercaPerISBN;
import ricerca.RicercaPerTitolo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.util.List;

public class ConsultaLibreria {
    private final ContestoFiltro c_filtro = new ContestoFiltro();
    private final ContestoRicerca c_ricerca = new ContestoRicerca();
    private final ContestoOrdinamento c_ordinamento = new ContestoOrdinamento();
    private final ContestoPermanenza c_permanenza = new ContestoPermanenza();

    public List<Libro> filtraPerGenere(String genere){
        c_filtro.setFiltro(new FiltraPerGenere());
        return c_filtro.eseguiFiltraggio(Libreria.INSTANCE.getLibri(),genere);
    }//filtraPerGenere

    public List<Libro> filtraPerStatoDiLettura(StatoLettura stato){
        c_filtro.setFiltro(new FiltraPerStatoDiLettura());
        return c_filtro.eseguiFiltraggio(Libreria.INSTANCE.getLibri(),stato);
    }//filtraPerStatoDiLettura

    public List<Libro> ricercaPerAutore(String autore){
        c_ricerca.setRicerca(new RicercaPerAutore());
        return c_ricerca.eseguiRicerca(Libreria.INSTANCE.getLibri(),autore);
    }//ricercaPerAutore

    public List<Libro> ricercaPerISBN(String ISBN){
        c_ricerca.setRicerca(new RicercaPerISBN());
        return c_ricerca.eseguiRicerca(Libreria.INSTANCE.getLibri(),ISBN);
    }//RicercaPerISBN

    public List<Libro> ricercaPerTitolo(String titolo){
        c_ricerca.setRicerca(new RicercaPerTitolo());
        return c_ricerca.eseguiRicerca(Libreria.INSTANCE.getLibri(),titolo);
    }//RicercaPerTitolo

    public List<Libro> ordinaPerTitolo(){
        c_ordinamento.setOrdinamento(new OrdinaPerTitolo());
        return c_ordinamento.eseguiOrdinamento(Libreria.INSTANCE.getLibri());
    }//OrdinaPerTitolo

    public List<Libro> ordinaPerValutazione(){
        c_ordinamento.setOrdinamento(new OrdinaPerValutazione());
        return c_ordinamento.eseguiOrdinamento(Libreria.INSTANCE.getLibri());
    }//ordinaPerValutazione

    public void salva(File file) throws IOException{
        c_permanenza.eseguiSalvataggio(file);
    }//salva

    public void carica(File file) throws  IOException{
        c_permanenza.eseguiCaricamento(file);
    }//carica
    
}//ConsultaLibreria
