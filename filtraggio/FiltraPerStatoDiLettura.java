package filtraggio;

import libro.Libro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FiltraPerStatoDiLettura implements FiltraStrategy{
    @Override
    public List<Libro> filtra(List<Libro> libri, Object arg) {
        boolean trovato = false;
        if(arg==null) return libri;
        List<Libro> lib_filt = new LinkedList<>();
        for(Libro lib : libri){
            if(lib.getStato().equals(arg)){
                lib_filt.add(lib);
                trovato = true;
            }

        }
        if(!trovato) throw new IllegalArgumentException("Stato di lettura non presente nella libreria!");
        return lib_filt;
    }//filtra
}//FiltraPerStatoDiLettura
