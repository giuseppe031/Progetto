package permanenza;

import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;

public interface PermanenzaStrategy {
    public void salva(File file) throws IOException;
    public void carica (File file) throws IOException, CsvValidationException;
}//Persistenza
