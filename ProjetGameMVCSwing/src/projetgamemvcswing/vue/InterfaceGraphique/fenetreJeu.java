package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Islem
 */
public class FenetreJeu extends JFrame {
    
    public FenetreJeu() {
        // Configuration de JFrame Dessin
        setTitle("Jeu");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/cont.png").getImage());

        // Créer un JPanel avec un fond blanc
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        // Ajouter le JPanel au contenu de la fenêtre
        getContentPane().add(panel);
        
        // Créer une instance de MenuBarJeu
        MenuBarJeu menuBarJeu = new MenuBarJeu(this);

        // Ajouter le menuBarJeu
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarJeu);

        // Créer une instance de BarreOutilsJeu
        BarreOutilsJeu barJeu = new BarreOutilsJeu(this);

        // Ajouter la barre d'outils personnalisée
        add(barJeu, BorderLayout.WEST);

        // Positionner la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
