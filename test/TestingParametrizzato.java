package test;

import libreria.ConsultaLibreria;
import libreria.Libreria;
import libro.Libro;
import libro.StatoLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestingParametrizzato {
    ConsultaLibreria c_lib = new ConsultaLibreria();

    @BeforeEach
    public void svuota(){
        Libreria.INSTANCE.svuotaLibreria();
    }//svuota

   @ParameterizedTest
    @CsvSource({
            "Titolo1, Autore1, 111",
            "Titolo2, Autore2, 222",
            "Titolo3, Autore3, 333"
    })

    public void aggiungi_libro_ok(String titolo, String autore, String ISBN){
        Libro l = new Libro.LibroBuilder(titolo,autore,ISBN).build();
        Libreria.INSTANCE.aggiungiLibro(l);
        assertEquals(1,Libreria.INSTANCE.getLibri().size());
        assertEquals(titolo,Libreria.INSTANCE.getLibri().get(0).getTitolo());
        assertEquals(autore,Libreria.INSTANCE.getLibri().get(0).getAutore());
        assertEquals(ISBN,Libreria.INSTANCE.getLibri().get(0).getISBN());
   }//aggiungi_libro_ok

    @ParameterizedTest
    @NullAndEmptySource
    public void aggiungi_libro_titolo_null(String titolo){
        assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder(titolo,"Autore","111").build();});
    }//aggiungi_libro_non_ok

    @ParameterizedTest
    @NullAndEmptySource
    public void aggiungi_libro_autore_null(String autore){
        assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder("Titolo",autore,"111").build();});
    }//aggiungi_autore_non_ok

    @ParameterizedTest
    @NullAndEmptySource
    public void aggiungi_libro_isbn_null(String isbn){
        assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder("Titolo","Autore",isbn).build();});
    }//aggiungi_isbn_non_ok

    @ParameterizedTest
    @ValueSource(strings = {"A1", "B2","C3"})
    public void aggiungi_isbn_dublicato(String isbn){
        Libro l1 = new Libro.LibroBuilder("Titolo","Autore",isbn).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libro l2 = new Libro.LibroBuilder("Titolo1","Autore1",isbn).build();
        assertThrows(IllegalArgumentException.class,()->Libreria.INSTANCE.aggiungiLibro(l2));
    }//aggiungi_isbn_dublicato

    @ParameterizedTest
    @ValueSource(
        strings = {"Horror","Fantasy","Romanzo"}
    )
    public void modifica_genere_libro(String nuovoGenere){
        Libro.LibroBuilder lb = new Libro.LibroBuilder("Titolo","Autore","111");
        Libro libro = lb.setGenere("Vecchio").setStato(StatoLettura.LETTO).setValutazione("1").build();
        Libreria.INSTANCE.aggiungiLibro(libro);
        Libreria.INSTANCE.modificaGenere(libro,nuovoGenere);
        assertTrue(Libreria.INSTANCE.getLibri().stream().anyMatch(b -> b.getISBN().equals("111") && nuovoGenere.equals(b.getGenere())));
    }//modificaLibro

    @ParameterizedTest
    @ValueSource(
            strings = {"1","3","4"}
    )
    public void modifica_valutazione_libro(String nuovaValutazione){
        Libro.LibroBuilder lb = new Libro.LibroBuilder("Titolo","Autore","111");
        Libro libro = lb.setGenere("genere").setStato(StatoLettura.LETTO).setValutazione("1").build();
        Libreria.INSTANCE.aggiungiLibro(libro);
        Libreria.INSTANCE.modificaValutazione(libro,nuovaValutazione);
        assertTrue(Libreria.INSTANCE.getLibri().stream().anyMatch(b -> b.getISBN().equals("111") && nuovaValutazione.equals(b.getValutazione())));
    }//modificaLibro

    @ParameterizedTest
    @EnumSource(StatoLettura.class)
    public void filtra_per_stato(StatoLettura stato){
        Libro l1 = new Libro.LibroBuilder("Titolo1","Autore1","111").setStato(stato).build();
        Libro l2 = new Libro.LibroBuilder("Titolo2","Autore2","222").setStato(StatoLettura.LETTO).build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore3","333").setStato((StatoLettura.IN_LETTURA)).build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);
        List<Libro> filtrati = c_lib.filtraPerStatoDiLettura(stato);
        assertTrue(filtrati.stream().anyMatch(b -> b.getISBN().equals("111")));
        assertTrue(filtrati.stream().allMatch(b -> stato.equals(b.getStato())));
    }//filtra_per_stato

    @ParameterizedTest
    @ValueSource (
            strings = {"Autore1","Autore2"}
    )
    public void ricerca_per_autore(String autore){
        Libro l1 =  new Libro.LibroBuilder("Titolo1","Autore1","111").build();
        Libro l2 = new Libro.LibroBuilder("Titolo2",autore,"222").build();
        Libro l3 = new Libro.LibroBuilder("Titolo3","Autore2","333").build();
        Libreria.INSTANCE.aggiungiLibro(l1);
        Libreria.INSTANCE.aggiungiLibro(l2);
        Libreria.INSTANCE.aggiungiLibro(l3);

        List<Libro> trovati = c_lib.ricercaPerAutore(autore);
        assertEquals(trovati.size(),2);
        assertTrue(trovati.stream().anyMatch(b -> b.getISBN().equals("222")));
        assertTrue(trovati.stream().allMatch(b -> autore.equals(b.getAutore())));
    }//ricerca_per_autore

}//TestingParametrizzato
