
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Observer.EcouteurModele;

public class PanelScore extends JPanel implements EcouteurModele {
    
    private JLabel pourcentageAireLabel;
    private JLabel aireCouverteLabel;
    private GameScore gameScore;

    public PanelScore(GameScore gameScore) {
        this.gameScore = gameScore;
        this.gameScore.ajoutEcouteur(this);

        setLayout(new GridLayout(2, 1)); // Disposition en grille pour les labels

        pourcentageAireLabel = new JLabel("Taux d'aire rempli (%): 0%");
        aireCouverteLabel = new JLabel("Score : 0");

        add(pourcentageAireLabel);
        add(aireCouverteLabel);
    }

    @Override
    public void modelUpdated(Object source) {
        
        //c'est pour eviter un null pointer exception à l'initialisation
        if (source instanceof GameScore && pourcentageAireLabel != null && aireCouverteLabel != null) {
            GameScore updatedScore = (GameScore) source;
            double airePanel = getParent().getWidth() * getParent().getHeight();
            double pourcentageAireCouverte = updatedScore.calculerPourcentageAireCouverte(airePanel);
            pourcentageAireLabel.setText("Taux d'aire rempli (%): " + pourcentageAireCouverte + "%");
            aireCouverteLabel.setText("Score : " + updatedScore.getAireCouverte());
        }
    }

}
