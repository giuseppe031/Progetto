package ordinamento;

import libro.Libro;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class OrdinaPerTitolo implements OrdinaStrategy {

    @Override
    public List<Libro> ordina(List<Libro> libri) {
      List<Libro> copia = new LinkedList(libri);
      copia.sort(new MioComparator());
      return copia;
    }//ordina

    private static class MioComparator implements Comparator<Libro> {
        @Override
        public int compare(Libro l1, Libro l2) {
            return l1.getTitolo().compareToIgnoreCase(l2.getTitolo());
        }//compare
    }//MioComparator
}//OrdinaPerTitolo
