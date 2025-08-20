package GUI;

public class UtilizzaLibreria {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LibreriaGUI lib_gui = new LibreriaGUI();
                lib_gui.setVisible(true);
            }//run
        });
    }//main
}//UtilizzaLibreria
