
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Observer.EcouteurModele;

public class PanelScore extends JTable implements EcouteurModele {
    
    private static final long serialVersionUID = 1L;
    private final GameScore gameScore;

    public PanelScore(GameScore gameScore) {
        this.gameScore = gameScore;
        this.gameScore.ajoutEcouteur(this);
        
        // Création du modèle de tableau avec les colonnes appropriées
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Aire occupée (%)");
        model.addColumn("Aire Couverte");
        
        // Initialisation avec des valeurs par défaut
        model.addRow(new Object[]{"0%", "0"});
        
        this.setModel(model);
        this.setRowHeight(20);
        
        // Empêcher l'édition des cellules
        DefaultTableModel tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false; // Rendre toutes les cellules non éditables
            }
        };
        
        this.setModel(tableModel);
    }

    @Override
    public void modelUpdated(Object source) {
        if (source instanceof GameScore) {
            GameScore updatedScore = (GameScore) source;
            // Supposons des dimensions fixes ou obtenues autrement
            double airePanel = 1100 * 750; // Remplacer par les dimensions réelles si disponibles
            double pourcentageAireCouverte = updatedScore.calculerPourcentageAireCouverte(airePanel);
            //this.getModel().setValueAt(String.format("%.2f%%", pourcentageAireCouverte), 0, 0);
            //this.getModel().setValueAt(String.format("%.2f", updatedScore.getAireCouverte()), 0, 1);
        }
    }


}