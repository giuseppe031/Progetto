package test;

import libreria.Libreria;
import libro.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestingParametrizzato {
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

}//TestingParametrizzato
