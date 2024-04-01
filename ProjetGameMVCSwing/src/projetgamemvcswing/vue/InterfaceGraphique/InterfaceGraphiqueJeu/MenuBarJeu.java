package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.FenetreDessin;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import projetgamemvcswing.vue.InterfaceGraphique.FenetreContact;
import projetgamemvcswing.vue.InterfaceGraphique.FenetreInformation;

/**
 * Classe qui permet de créer un JMenuBar qui est un menu pour le jeu
 * @author romain
 */
public class MenuBarJeu extends JMenuBar {
    private final JFrame currentFrame;
    
    /**
     * Permet de creer une barre de menu pour le jeu
     * @param frame 
     */
    public MenuBarJeu(JFrame frame) {
        
        currentFrame = frame;
                
        // créer le menu Jeu
        JMenu menuJeu = new JMenu("Jeu");
        JMenuItem newgameItem = new JMenuItem("Nouvelle Partie");
        JMenuItem exitItem = new JMenuItem("Sortir");
        
        // ccouter pour l'item exitItem/Sortir
        exitItem.addActionListener((ActionEvent e) -> {
            // cethode pour fermer la fenetre current
            handleExitAction(currentFrame);
        });
        
        menuJeu.add(newgameItem);
        menuJeu.add(exitItem);

        // créer le menu Outils
        JMenu menuOutils = new JMenu("Outils");
        JMenuItem circleItem = new JMenuItem("Cercle");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        menuOutils.add(circleItem);
        menuOutils.add(rectangleItem);
        
        // créer le menu mode
        JMenu menuMode = new JMenu("Mode");
        JMenuItem dessinItem = new JMenuItem("Mode Dessin");
        
        // ecouter pour l'item dessinItem/ Changer le mode vers dessin
        dessinItem.addActionListener((ActionEvent e) -> {
            // methode pour Changer le mode vers dessin
            handleDessinAction();
        });
        
        menuMode.add(dessinItem);
        
        // créer le menu aide
        JMenu menuAide = new JMenu("Aide");
        JMenuItem infoItem = new JMenuItem("Information");
        JMenuItem contactItem = new JMenuItem("Contact");
        menuAide.add(infoItem);
        menuAide.add(contactItem);
        
        // ecouter pour l'item infoItem/Afficher une petite fenetre Information
        infoItem.addActionListener((ActionEvent e) -> {
            // Methode pour afficher une petite fenetre Information
            handleInformationAction();
        });
        
        // ecouter pour l'item contactItem/Afficher une petite fenetre Contact
        contactItem.addActionListener((ActionEvent e) -> {
            // methode pour afficher une petite fenetre Contact
            handleContactAction();
        });

        // ajouter les menus à la barre de menu
        add(menuJeu);
        add(menuOutils);
        add(menuMode);
        add(menuAide);
    }
    
    /**
     * Methode fermeture de la fenetre Dessin avec un message d'alerte
     * @param frame 
     */
    private static void handleExitAction(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    /**
     * Methode qui ouvre la fenetre Information
     */
    private void handleInformationAction() {
        FenetreInformation fInformation = new FenetreInformation();
        fInformation.setVisible(true);
    }
    
    /**
     * Methode qui ouvre la fenetre Contact
     */
    private void handleContactAction() {
        FenetreContact fcontact = new FenetreContact();
        fcontact.setVisible(true);
    }
    
    /**
     * Methode qui change le mode de dessin vers jeu
     */
    private void handleDessinAction() {
        FenetreDessin fDessin = new FenetreDessin();
        fDessin.setVisible(true);
        
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
