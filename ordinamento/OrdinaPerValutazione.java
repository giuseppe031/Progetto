package ordinamento;

import libro.Libro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class OrdinaPerValutazione implements OrdinaStrategy{

    @Override
    public List<Libro> ordina(List<Libro> libri) {
        List<Libro> copia = new LinkedList<>(libri);
        copia.sort(new MioComparator());
        return copia;
    }//ordina

    private static class MioComparator implements Comparator<Libro> {
        @Override
        public int compare(Libro l1, Libro l2) {
           return l1.getValutazione().compareTo(l2.getValutazione());
        }//compare
    }//MioComparator
}//OrdinaPerValutazione
