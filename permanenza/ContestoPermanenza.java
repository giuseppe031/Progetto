package permanenza;

import java.io.File;
import java.io.IOException;

public class ContestoPermanenza {
    private PermanenzaStrategy strategy;
    public void setStrategy(PermanenzaStrategy strategy) {
        this.strategy = strategy;
    }//setStrategy
    public void eseguiSalvataggio(File file) throws IOException {
        strategy.salva(file);
    }//eseguiSalvataggio
    public void eseguiCaricamento(File file) throws IOException{
        strategy.carica(file);
    }//eseguiCaricamento
}//ContestoPermanenza
