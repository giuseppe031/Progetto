package filtraggio;

import libro.Libro;

import java.util.LinkedList;
import java.util.List;

public class FiltraPerGenere implements FiltraStrategy{

    @Override
    public List<Libro> filtra(List<Libro> libri, Object arg) {
        if(arg==null) return libri;
        LinkedList<Libro> lib_filtr = new LinkedList<Libro>();
        for(Libro lib:libri){
            if(lib.getGenere().equals(arg))
                lib_filtr.add(lib);
        }
        return lib_filtr;
    }//filtra
}//FiltraPerGenere
