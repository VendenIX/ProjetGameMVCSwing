
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import projetgamemvcswing.controller.Observer.EcouteurModele;

public class PanelTentative extends JTable implements EcouteurModele {
    private static final Color CUSTOM_GREY = new Color(242, 242, 242);
    
    private final DefaultTableModel model;
    
    public PanelTentative() {
        // Create a DefaultTableModel with two columns and two rows
        model = new DefaultTableModel(new Object[][]{{"Tentative Cercle", "Tentative Rectangle"}, {0, 0}}, new Object[]{"", ""});
        
        
        // Set the model for the table
        this.setModel(model);
        
        // Configure the table to be thin and long
        this.setRowHeight(20); // Set the row height
        this.getColumnModel().getColumn(0).setPreferredWidth(100); // Set width of column 0
        this.getColumnModel().getColumn(1).setPreferredWidth(100); // Set width of column 1
        
        // Set background color to grey
        this.setBackground(CUSTOM_GREY);
    }

    @Override
    public void modelUpdated(Object source) {
        
    }
}
