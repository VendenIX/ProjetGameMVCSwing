package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Islem
 */
public class menuBarDessin extends JMenuBar {

    private final JFrame currentFrame;
    
    public menuBarDessin(JFrame frame) {
        
        currentFrame = frame;
                
        // Créer le menu Fichier
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem newItem = new JMenuItem("Nouveau");
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        JMenuItem exitItem = new JMenuItem("Sortir");
        
        // Ecouter pour l'item exitItem/Sortir
        exitItem.addActionListener((ActionEvent e) -> {
            // Methode pour fermer la fenetre current
            handleExitAction(currentFrame);
        });
        
        menuFichier.add(newItem);
        menuFichier.add(saveItem);
        menuFichier.add(exitItem);

        // Créer le menu Outils
        JMenu menuOutils = new JMenu("Outils");
        JMenuItem circleItem = new JMenuItem("Cercle");
        JMenuItem rectangleItem = new JMenuItem("Rectangle");
        JMenuItem moveItem = new JMenuItem("Déplacer");
        JMenuItem pencilItem = new JMenuItem("Crayon");
        JMenuItem paintItem = new JMenuItem("peindre");
        JMenuItem undoItem = new JMenuItem("Annuler");
        JMenuItem redoItem = new JMenuItem("Refaire");
        JMenuItem deleteItem = new JMenuItem("Supprimer");
        menuOutils.add(circleItem);
        menuOutils.add(rectangleItem);
        menuOutils.add(moveItem);
        menuOutils.add(pencilItem);
        menuOutils.add(paintItem);
        menuOutils.add(undoItem);
        menuOutils.add(redoItem);
        menuOutils.add(deleteItem);
        
        // Créer le menu mode
        JMenu menuMode = new JMenu("Mode");
        JMenuItem jeuItem = new JMenuItem("Mode Jeu");
        
        // Ecouter pour l'item jeuItem/ Changer le mode vers jeu
        jeuItem.addActionListener((ActionEvent e) -> {
            // Methode pour Changer le mode vers jeu
            handleJeuAction();
        });
        
        menuMode.add(jeuItem);
        
        // Créer le menu aide
        JMenu menuAide = new JMenu("Aide");
        JMenuItem infoItem = new JMenuItem("Information");
        JMenuItem contactItem = new JMenuItem("Contact");
        menuAide.add(infoItem);
        menuAide.add(contactItem);
        
        // Ecouter pour l'item infoItem/Afficher une petite fenetre Information
        infoItem.addActionListener((ActionEvent e) -> {
            // Methode pour afficher une petite fenetre Information
            handleInformationAction();
        });
        
        // Ecouter pour l'item contactItem/Afficher une petite fenetre Contact
        contactItem.addActionListener((ActionEvent e) -> {
            // Methode pour afficher une petite fenetre Contact
            handleContactAction();
        });

        // Ajouter les menus à la barre de menu
        add(menuFichier);
        add(menuOutils);
        add(menuMode);
        add(menuAide);
    }
    
    // Methode fermeture de la fenetre Dessin avec un message d'alerte
    private static void handleExitAction(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // Methode qui ouvre la fenetre Information
    private void handleInformationAction() {
        fenetreInformation fInformation = new fenetreInformation();
        fInformation.setVisible(true);
    }
    
    // Methode qui ouvre la fenetre Contact
    private void handleContactAction() {
        fenetreContact fcontact = new fenetreContact();
        fcontact.setVisible(true);
    }
    
    // Methode qui change le mode de dessin vers jeu
    private void handleJeuAction() {
        fenetreJeu fJeu = new fenetreJeu();
        fJeu.setVisible(true);
        
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
