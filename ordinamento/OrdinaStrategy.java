package ordinamento;

import libro.Libro;

import java.util.List;

public interface OrdinaStrategy {
    public  List<Libro> ordina(List<Libro> libri);
}//OrdinaStrategy
