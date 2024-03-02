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
public class fenetreDessin extends JFrame {
    
    public fenetreDessin() {
        // Configuration de JFrame Dessin
        setTitle("Dessin");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/pal.png").getImage());

        // Créer un JPanel avec un fond blanc
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        // Ajouter le JPanel au contenu de la fenêtre
        getContentPane().add(panel);
        
        // Créer une instance de MenuBarDessin
        menuBarDessin menuBarDessin = new menuBarDessin(this);

        // Ajouter le menuBarDessin
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarDessin);

        // Créer une instance de BarreOutilsDessin
        barreOutilsDessin barDessin = new barreOutilsDessin(this);

        // Ajouter la barre d'outils personnalisée
        add(barDessin, BorderLayout.WEST);

        // Positionner la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
