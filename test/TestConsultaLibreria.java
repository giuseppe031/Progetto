package test;

import libreria.ConsultaLibreria;
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

public class TestConsultaLibreria {
    private final ConsultaLibreria consulta_lib = new ConsultaLibreria();

    @BeforeEach
    public void svuota(){
        Libreria.INSTANCE.svuotaLibreria();
    }//svuota

    @Test
    public void testFiltraPerGenere() {
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l4 = new Libro.LibroBuilder("Titolo4","Autore4","44")
                .setGenere("Genere4").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        Libreria.INSTANCE.aggiungiLibro(l4);
        List<Libro> filtrati = consulta_lib.filtraPerGenere("Genere1");
        assertEquals(2,filtrati.size());
        assertEquals("Titolo1", filtrati.get(0).getTitolo());
        assertEquals("Titolo3", filtrati.get(1).getTitolo());

    }//testFiltraPerGenere

    @Test
    public void test_genere_null(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l4 = new Libro.LibroBuilder("Titolo4","Autore4","44")
                .setGenere("Genere4").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        Libreria.INSTANCE.aggiungiLibro(l4);
        List<Libro> filtrati = consulta_lib.filtraPerGenere(null);
        assertEquals(4,filtrati.size());
    }//test_genere_null

    @Test
    public void test_filtra_per_stato_di_lettura(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l4 = new Libro.LibroBuilder("Titolo4","Autore4","44")
                .setGenere("Genere4").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        Libreria.INSTANCE.aggiungiLibro(l4);
        List<Libro> filtrati = consulta_lib.filtraPerStatoDiLettura(StatoLettura.LETTO);
        assertEquals(3,filtrati.size());
        assertEquals("Titolo1", filtrati.get(0).getTitolo());
        assertEquals("Titolo3", filtrati.get(1).getTitolo());
    }//test_Filtra_per_stato_di_lettura

    @Test
    public void stato_lettura_null(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l4 = new Libro.LibroBuilder("Titolo4","Autore4","44")
                .setGenere("Genere4").setValutazione("2").setStato(StatoLettura.LETTO).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        Libreria.INSTANCE.aggiungiLibro(l4);
        List<Libro> filtrati = consulta_lib.filtraPerStatoDiLettura(null);
        assertEquals(4,filtrati.size());
        assertEquals("Titolo1", filtrati.get(0).getTitolo());
    }//stato_lettura_null

    @Test
    public void test_ricerca_per_autore(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore1","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        List<Libro> trovati = consulta_lib.ricercaPerAutore("Autore1");
        assertEquals(2,trovati.size());
        assertEquals("Titolo3",trovati.get(1).getTitolo());
    }//ricerca_per_autore

    @Test
    public void test_autore_non_presente(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        assertThrows(NoSuchElementException.class, () -> consulta_lib.ricercaPerAutore("Autore4"));
    }//test_autore_non_presente

    @Test
    public void test_ricerca_per_titolo(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore1","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        List<Libro> trovati = consulta_lib.ricercaPerTitolo("Titolo1");
        assertEquals(1,trovati.size());
        assertEquals("Titolo1",trovati.get(0).getTitolo());
    }//test_ricerca_per_titolo

    @Test
    public void test_titolo_non_presente(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        assertThrows(NoSuchElementException.class,()->consulta_lib.ricercaPerTitolo("Titolo4"));
    }//test_titolo_non_presente

    @Test
    public void test_ricerca_per_ISBN(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore1","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        List<Libro> trovati = consulta_lib.ricercaPerISBN("11");
        assertEquals(1,trovati.size());
        assertEquals("11",trovati.get(0).getISBN());

    }//test_ricerca_per_ISBN

    @Test
    public void test_ISBN_non_presente(){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","11")
                .setGenere("Genere1").setValutazione("1").setStato(StatoLettura.LETTO).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","22")
                .setGenere("Genere2").setValutazione("2").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","33")
                .setGenere("Genere3").setValutazione("1").setStato(StatoLettura.IN_LETTURA).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        assertThrows(NoSuchElementException.class,()-> consulta_lib.ricercaPerISBN("44"));
    }//test_ISBN_non_presente

    @Test
    public void test_ordina_per_titolo(){

    }//test_ordina_per_titolo

    @Test
    public void test_ordina_per_valutazione(){

    }//test_ordina_per_valutazione

}//TestFiltraPerGenere
