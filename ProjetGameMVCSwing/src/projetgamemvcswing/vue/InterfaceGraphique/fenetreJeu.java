package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Islem
 */
public class fenetreJeu extends JFrame {
    
    public fenetreJeu() {
        setTitle("Jeu");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel with a white background
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        // Add the JPanel to the content pane
        getContentPane().add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
