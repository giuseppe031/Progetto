package GUI;

import libro.Libro;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LibreriaAdapter extends AbstractTableModel {

    private final String[] colonne = {"Titolo","Autore","ISBN","Genere","Valutazione","Stato lettura"};
    private final List<Libro> libri;

    public LibreriaAdapter(List<Libro> libri) {
        this.libri = libri;
    }


    @Override
    public int getRowCount() {
        return libri.size();
    }//getRowCount

    @Override
    public int getColumnCount() {
        return colonne.length;
    }//getColumnCount

    @Override
    public String getColumnName(int col) {
        return colonne[col];
    }//getColumnName

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Libro lib = libri.get(rowIndex);
        switch (columnIndex) {
            case 0: return lib.getTitolo();
            case 1: return lib.getAutore();
            case 2: return lib.getISBN();
            case 3: return lib.getGenere();
            case 4: return lib.getValutazione();
            case 5: return lib.getStato();
            default: return null;
        }
    }//getValueAt

    public Libro getLibro(int row) {
        return libri.get(row);
    }//getLibro

}//LibroAdapter
