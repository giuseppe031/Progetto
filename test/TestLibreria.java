package test;

import libreria.Libreria;
import libro.Libro;
import libro.StatoLettura;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLibreria {

    @BeforeEach
    public void svuota(){
        Libreria.INSTANCE.svuotaLibreria();
    }//svuota


    @Test
    public void test_aggiungi_libro(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","1")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","2")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","3")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        List<Libro> libreria = Libreria.INSTANCE.getLibri();
        assertEquals(3,libreria.size());
    }//test_aggiungi_libro

    @Test
    public void test_aggiungi_libro_null(){
        assertThrows(IllegalArgumentException.class, ()->Libreria.INSTANCE.aggiungiLibro(null));
    }//test_aggiungi_libro_null

    @Test
    public void test_aggiungi_ISBN_duplicato(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","1345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","1345")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        assertThrows(IllegalArgumentException.class, ()-> Libreria.INSTANCE.aggiungiLibro(l2));
    }//test_aggiungi_ISBN_duplicato

    @Test
    public void test_rimuovi_libro(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","678910")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.rimuoviLibro(l1);
        List<Libro> libreria = Libreria.INSTANCE.getLibri();
        assertEquals(1,libreria.size());
    }//test_rimuovi_libro

    @Test
    public void test_rimuovi_libro_assente(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","678910")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        List<Libro> libreria = Libreria.INSTANCE.getLibri();
        assertThrows(IllegalArgumentException.class, ()-> Libreria.INSTANCE.rimuoviLibro(l2));
    }//test_rimuovi_libro_assente

    @Test
    public void test_modifica_genere(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.modificaGenere(l1,"NuovoGenere");
        assertEquals("NuovoGenere",l1.getGenere());
    }//test_modifica_genere

    @Test
    public void test_genere_non_modificato(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        assertThrows(IllegalArgumentException.class,()-> Libreria.INSTANCE.modificaGenere(l1,"Genere1"));
    }//test_genere_non_modificato

    @Test
    public void test_modica_valutazione(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.modificaValutazione(l1,"2");
        assertEquals("2",l1.getValutazione());
    }//test_modifica_valutazione

    @Test
    public void test_valutazione_non_modificata(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        assertThrows(IllegalArgumentException.class,()-> Libreria.INSTANCE.modificaValutazione(l1,"1"));
    }//test_valutazione_non_modificata

    @Test
    public void test_modifica_stato_lettura() {
        Libro l1 = new Libro.LibroBuilder("Titolo1", "Autore1", "12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.modificaStatoLettura(l1, StatoLettura.IN_LETTURA);
        assertEquals(StatoLettura.IN_LETTURA,l1.getStato());
    }

    @Test
    public void test_stato_lettura_non_modificato(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","12345")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        assertThrows(IllegalArgumentException.class,()->Libreria.INSTANCE.modificaStatoLettura(l1,StatoLettura.LETTO));
    }//test_stato_lettura_non_modificato


}//TestLibreria
