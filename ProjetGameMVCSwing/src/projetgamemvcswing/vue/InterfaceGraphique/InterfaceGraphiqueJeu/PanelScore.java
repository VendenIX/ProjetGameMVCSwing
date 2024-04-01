
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Observer.EcouteurModele;

/**
 * Classe qui sert a creer un panel qui affiche le score
 * @author romain
 */
public class PanelScore extends JPanel implements EcouteurModele {
    
    private JLabel joueurLabel;
    private JLabel pourcentageAireJoueurLabel;
    private JLabel aireCouverteJoueurLabel;
    
    private JLabel ordinateurLabel;
    private JLabel pourcentageAireOrdinateurLabel;
    private JLabel aireCouverteOrdinateurLabel;
    
    private GameScore gameScoreJoueur;
    private GameScore gameScoreOrdinateur;
    
    /**
     * Créer un panelscore
     * @param gameScoreJoueur
     * @param gameScoreOrdinateur 
     */
    public PanelScore(GameScore gameScoreJoueur, GameScore gameScoreOrdinateur) {
        this.gameScoreJoueur = gameScoreJoueur;
        this.gameScoreOrdinateur = gameScoreOrdinateur;
        this.gameScoreJoueur.ajoutEcouteur(this);
        this.gameScoreOrdinateur.ajoutEcouteur(this);

        setLayout(new GridLayout(3, 2)); // disposition en grille pour les labels avec deux colonnes
        setBorder(new EmptyBorder(10, 10, 10, 10)); // une marge pour l'esthétique

        joueurLabel = new JLabel("Joueur");
        joueurLabel.setFont(new Font("Serif", Font.BOLD, 16));

        ordinateurLabel = new JLabel("Ordinateur", JLabel.RIGHT);
        ordinateurLabel.setFont(new Font("Serif", Font.BOLD, 16));

        pourcentageAireJoueurLabel = new JLabel("Taux d'aire rempli (%): 0%");
        aireCouverteJoueurLabel = new JLabel("Score : 0");

        pourcentageAireOrdinateurLabel = new JLabel("Taux d'aire rempli (%):--%", JLabel.RIGHT);
        aireCouverteOrdinateurLabel = new JLabel("Score : --", JLabel.RIGHT);

        add(joueurLabel);
        add(ordinateurLabel);
        add(pourcentageAireJoueurLabel);
        add(pourcentageAireOrdinateurLabel);
        add(aireCouverteJoueurLabel);
        add(aireCouverteOrdinateurLabel);
    }
    
    /**
     * Met a jour le model
     * @param source 
     */
    @Override
    public void modelUpdated(Object source) {
        if (source instanceof GameScore 
            && pourcentageAireJoueurLabel != null && aireCouverteJoueurLabel != null 
            && pourcentageAireOrdinateurLabel != null && aireCouverteOrdinateurLabel != null) {
            double airePanel = getParent().getWidth() * getParent().getHeight();
            if (source == gameScoreJoueur) {
                double pourcentageAireCouverte = gameScoreJoueur.calculerPourcentageAireCouverte(airePanel);
                pourcentageAireJoueurLabel.setText("Taux d'aire rempli (%): " + String.format("%.2f", pourcentageAireCouverte) + "%");
                aireCouverteJoueurLabel.setText("Score : " + String.format("%.2f", gameScoreJoueur.getAireCouverte()));
            } else if (source == gameScoreOrdinateur) {
                double pourcentageAireCouverte = gameScoreOrdinateur.calculerPourcentageAireCouverte(airePanel);
                pourcentageAireOrdinateurLabel.setText("Taux d'aire rempli (%): " + String.format("%.2f", pourcentageAireCouverte) + "%");
                aireCouverteOrdinateurLabel.setText("Score : " + String.format("%.2f", gameScoreOrdinateur.getAireCouverte()));
            }
        }
    }


}
