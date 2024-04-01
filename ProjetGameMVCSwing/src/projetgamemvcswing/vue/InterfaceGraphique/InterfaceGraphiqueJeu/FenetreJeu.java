package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import projetgamemvcswing.controller.GameScore;


/**
 *
 * @author Islem
 */
public class FenetreJeu extends JFrame {
    
     // Variables de classe
    private PanelJeu panelJeu;
    private MenuBarJeu menuBarJeu;
    private BarreOutilsJeu barJeu;
    private PanelScore panelScore;
    private PanelTentative panelTentative;
    
    private final GameScore gameScore = new GameScore();
    
    
    public FenetreJeu() {
        // Configuration de JFrame Dessin
        setTitle("Jeu");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/cont.png").getImage());
        
        // Initialiser les composants de l'interface utilisateur
        initUIComponents(this, gameScore);

        // Créer une instance de MenuBarJeu
        menuBarJeu = new MenuBarJeu(this);

        // Ajouter le menuBarJeu
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarJeu);
        
        panelTentative = new PanelTentative();
        getContentPane().add(panelTentative, BorderLayout.NORTH);
        
        panelScore = new PanelScore(gameScore);
        getContentPane().add(panelScore, BorderLayout.SOUTH);

        // Créer une instance de BarreOutilsJeu
        barJeu = new BarreOutilsJeu(this, this.panelJeu);

        // Ajouter la barre d'outils personnalisée
        add(barJeu, BorderLayout.WEST);
        
        // Ajouter l'interface de dessin au centre
        getContentPane().add(panelJeu, BorderLayout.CENTER);

        // Positionner la fenêtre au centre de l'écran
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Initialise les composants de l'interface utilisateur.
     */
    private void initUIComponents(JFrame frame, GameScore gameScore) {
        // Créer une instance de l'interface de dessin
        panelJeu = new PanelJeu(frame, gameScore);
    }
    
}
