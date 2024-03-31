
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Observer.EcouteurModele;

public class PanelScore extends JTable implements EcouteurModele {
    
    private static final Color CUSTOM_GREY = new Color(242, 242, 242);
    
    private final DefaultTableModel model;
    private final GameScore gameScore;
    
    public PanelScore(GameScore gameScore) {
        // Create a DefaultTableModel with two columns and two rows
        model = new DefaultTableModel(new Object[][]{{"Score Maximum", "Score Jouer"}, {gameScore.getScoreMax(), gameScore.getScoreJeu()}}, new Object[]{"", ""});
        
        this.gameScore = gameScore;
        this.gameScore.ajoutEcouteur(this);
        
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
        if (source instanceof GameScore) {
            GameScore mongameScore = (GameScore) source;
            int rowCount = this.getModel().getRowCount();
            int columnCount = this.getModel().getColumnCount();
            if (rowCount >= 2 && columnCount >= 2) {
                this.getModel().setValueAt(mongameScore.getScoreMax(), 1, 0);
                this.getModel().setValueAt(mongameScore.getScoreJeu(), 1, 1);
            } else {
                // Handle insufficient rows/columns in the table model
                System.out.println("Insufficient rows or columns in the table model");
            }
        }
    }
    }
