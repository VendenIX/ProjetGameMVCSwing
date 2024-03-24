package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * La classe FenetreDessin représente la fenêtre principale de l'application de dessin.
 * Elle étend JFrame et contient l'interface utilisateur, la barre de menu et la barre d'outils.
 * 
 * @author Islem
 */
public class FenetreDessin extends JFrame {

    // Variables de classe
    private PanelDessin interfacedessin;
    private MenuBarDessin menuBarDessin;
    private BarreOutilsDessin barDessin;

    /**
     * Constructeur de la classe FenetreDessin.
     * Initialise les composants de l'interface utilisateur.
     */
    public FenetreDessin() {
        // Configuration de JFrame Dessin
        setTitle("Dessin");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Définir l'icône de la fenêtre
        setIconImage(new ImageIcon("images/pal.png").getImage());

        // Initialiser les composants de l'interface utilisateur
        initUIComponents();

        // Ajouter un Layout au contenu de la fenêtre
        getContentPane().setLayout(new BorderLayout());

        // Créer une instance de MenuBarDessin
        menuBarDessin = new MenuBarDessin(this, interfacedessin);

        // Ajouter le menuBarDessin
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarDessin);

        // Créer une instance de BarreOutilsDessin
        barDessin = new BarreOutilsDessin(this, interfacedessin);

        // Ajouter la barre d'outils personnalisée
        add(barDessin, BorderLayout.WEST);

        // Ajouter l'interface de dessin au centre
        getContentPane().add(interfacedessin, BorderLayout.CENTER);

        // Positionner la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initialise les composants de l'interface utilisateur.
     */
    private void initUIComponents() {
        // Créer une instance de l'interface de dessin
        interfacedessin = new PanelDessin();
    }

}

