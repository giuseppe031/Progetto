package ricerca;

import libro.Libro;

import java.util.List;

public interface RicercaStrategy {
    public List<Libro> ricerca (List<Libro> libri, String par);
}//RicercaStrategy
