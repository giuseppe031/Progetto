package GUI;

import libreria.ConsultaLibreria;
import libreria.Libreria;
import libro.Libro;
import libro.StatoLettura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.NoSuchElementException;

public class LibreriaGUI extends JFrame {
    private final ConsultaLibreria consulta_lib = new ConsultaLibreria();
    private final JTable tabella;
    private LibreriaAdapter modello;

    public LibreriaGUI(){
        super("Libreria");
        List<Libro> libri = Libreria.INSTANCE.getLibri();
        modello = new LibreriaAdapter(libri);
        tabella = new JTable(modello);
        JScrollPane scroll = new JScrollPane(tabella);

        JButton bottAggiungi = new JButton("Aggiungi libro");
        JButton bottRimuovi = new JButton("Rimuovi libro");
        JButton bottModGen = new JButton("Modifica genere");
        JButton bottModVal = new JButton("Modifica valutazione");
        JButton bottModStato = new JButton("Modifica stato");

        JPanel azioni = new JPanel();

        azioni.add(bottAggiungi);
        azioni.add(bottRimuovi);
        azioni.add(bottModGen);
        azioni.add(bottModVal);
        azioni.add(bottModStato);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(azioni, BorderLayout.SOUTH);

        bottAggiungi.addActionListener(e->aggiungi(e));
        bottRimuovi.addActionListener(e->rimuovi(e));
        bottModGen.addActionListener(e->modGenere(e));
        bottModVal.addActionListener(e->modValutazione(e));
        bottModStato.addActionListener(e->modStatoLett(e));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,500);
        setLocationRelativeTo(null);
    }//costruttore

    private void ricarica(){
        modello = new LibreriaAdapter(Libreria.INSTANCE.getLibri());
        tabella.setModel(modello);
        tabella.revalidate();
        tabella.repaint();
    }//ricarica

    private void aggiungi(ActionEvent e){
        JTextField campoTitolo = new JTextField();
        JTextField campoAutore = new JTextField();
        JTextField campoISBN = new JTextField();
        JTextField campoGenere = new JTextField();

        String[] possibiliValut = {"1","2","3","4","5"};
        JComboBox<String> comboValutazione = new JComboBox<>(possibiliValut);

        JComboBox<StatoLettura> comboStatoLett = new JComboBox<>(StatoLettura.values());

        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Titolo:"));
        panel.add(campoTitolo);
        panel.add(new JLabel("Autore:"));
        panel.add(campoAutore);
        panel.add(new JLabel("ISBN:"));
        panel.add(campoISBN);
        panel.add(new JLabel("Genere:"));
        panel.add(campoGenere);
        panel.add(new JLabel("Valutazione:"));
        panel.add(comboValutazione);
        panel.add(new JLabel("Stato lettura:"));
        panel.add(comboStatoLett);

        int ris = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Aggiungi libro",
                JOptionPane.OK_CANCEL_OPTION);

        if (ris == JOptionPane.OK_OPTION){
            String titolo = campoTitolo.getText().trim();
            String autore = campoAutore.getText().trim();
            String isbn = campoISBN.getText().trim();
            String genere = campoGenere.getText().trim();
            String valutazione = (String) comboValutazione.getSelectedItem();
            StatoLettura stato = (StatoLettura) comboStatoLett.getSelectedItem();

            if(titolo.isEmpty() || autore.isEmpty() || isbn.isEmpty()){
                JOptionPane.showMessageDialog(this, "Titolo, Autore e ISBN sono obbligatori");
                return;
            }
            try{
                Libro.LibroBuilder builder = new Libro.LibroBuilder(titolo, autore, isbn);
                if(!genere.isEmpty())
                    builder.setGenere(genere);
                builder.setValutazione(valutazione);
                builder.setStato(stato);
                Libreria.INSTANCE.aggiungiLibro(builder.build());
                ricarica();
            }catch (IllegalArgumentException e2){
                JOptionPane.showMessageDialog(this, "Errore: " + e2.getMessage());
            }
        }
    }//aggiungi

    private void rimuovi(ActionEvent e){
        if (tabella.getRowCount()==0) {
            JOptionPane.showMessageDialog(this, "La libreria è vuota, inserisci dei libri prima di rimuovere.");
            return;
        }
        int riga = tabella.getSelectedRow();
        if(riga < 0){
            JOptionPane.showMessageDialog(this, "Seleziona un libro da rimuovere");
            return;
        }
        Libro daRimuovere = modello.getLibro(riga);
        try{
            Libreria.INSTANCE.rimuoviLibro(daRimuovere);
            ricarica();
        }catch(IllegalArgumentException e3){
            JOptionPane.showMessageDialog(this,"Errore: " + e3.getMessage());
        }
    }//rimuovi

    private void modGenere(ActionEvent e){
        if(tabella.getRowCount()==0){
            JOptionPane.showMessageDialog(this,"La libreria è vuota, inserisci dei libri prima di modificare il genere.");
            return;
        }
        int riga = tabella.getSelectedRow();
        if(riga < 0){
            JOptionPane.showMessageDialog(this,"Seleziona un libro da modificare");
            return;
        }
        Libro daModificare = modello.getLibro(riga);
        JTextField campoNuovoGen = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Nuovo genere:"));
        panel.add(campoNuovoGen);

        int ris = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Modifica genere",
                JOptionPane.OK_CANCEL_OPTION);

        if (ris == JOptionPane.OK_OPTION){
            try{
                String nuovoGen = campoNuovoGen.getText().trim();
                Libreria.INSTANCE.modificaGenere(daModificare,nuovoGen);
                ricarica();
            }catch (NoSuchElementException e4){
                JOptionPane.showMessageDialog(this,"Errore: "+e4.getMessage());
                return;
            }
        }
    }//modGenere

    private void modValutazione(ActionEvent e){

    }//modValutazione

    private void modStatoLett(ActionEvent e){

    }//modStatoLett


}//LibreriaGUI
