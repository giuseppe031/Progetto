package test;

import libro.Libro;
import libro.StatoLettura;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestLibro {
    @Test
    public void test_builder(){ //come si può notare questo metodo prevede anche il test dei metodi getter
        Libro libro = new Libro.LibroBuilder("Saper vedere l'architettura","Bruno Zevi","245")
                .setGenere("Istruzione").setValutazione("5").setStato(StatoLettura.DA_LEGGERE).build();

        assertEquals("Saper vedere l'architettura",libro.getTitolo());
        assertEquals("Bruno Zevi",libro.getAutore());
        assertEquals("245",libro.getISBN());
        assertEquals("Istruzione",libro.getGenere());
        assertEquals("5",libro.getValutazione());
        assertEquals(StatoLettura.DA_LEGGERE, libro.getStato());

    }//testBuilder

    @Test
    public void test_costruzione(){
        Libro.LibroBuilder builder = new Libro.LibroBuilder("Il calcio","Giuseppe Fabiano","123");
        Libro l1 = builder.setGenere("Sportivo").setValutazione("1").setStato(StatoLettura.DA_LEGGERE).build();
        Libro l2 = builder.setGenere("Fantascienza").setValutazione("2").setStato(StatoLettura.LETTO).build();
        //testing modifiche:
        assertEquals("Sportivo",l1.getGenere());
        assertEquals("Fantascienza",l2.getGenere());
        //test attributi fondamentali
        assertEquals(l1.getTitolo(),l2.getTitolo());
        assertEquals(l1.getAutore(),l2.getAutore());
        assertEquals(l1.getISBN(),l2.getISBN());
    }//test_builder_separato

    @Test
    public void test_titolo_null(){
       assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder(null,"Manzoni","111").build();});
    }//test_titolo_null

    @Test
    public void test_autore_null(){
        assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder("I Promessi Sposi",null,"111").build();});
    }//test_autore_null

    @Test
    public void test_ISBN_null(){
        assertThrows(IllegalArgumentException.class,()->{new Libro.LibroBuilder("I Promessi Sposi","Manzoni",null).build();});
    }//test_ISBN_null


}//TestLibro
