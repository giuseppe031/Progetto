package filtraggio;

import libro.Libro;

import java.util.LinkedList;
import java.util.List;

public class FiltraPerGenere implements FiltraStrategy{

    @Override
    public List<Libro> filtra(List<Libro> libri, Object arg) {
        if(arg==null) return libri;
        boolean trovato = false;
        LinkedList<Libro> lib_filtr = new LinkedList<Libro>();
        for(Libro lib:libri){
            if(lib.getGenere().equalsIgnoreCase((String)arg)){
                lib_filtr.add(lib);
                trovato = true;
            }
        }
        if(!trovato) throw new IllegalArgumentException("Genere non presente nella libreria!");
        return lib_filtr;
    }//filtra
}//FiltraPerGenere
