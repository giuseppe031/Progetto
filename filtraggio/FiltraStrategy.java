package filtraggio;

import libro.Libro;

import java.util.List;

public interface FiltraStrategy {
    public List<Libro> filtra(List<Libro> libri,Object arg);
}//FiltraStrategy
