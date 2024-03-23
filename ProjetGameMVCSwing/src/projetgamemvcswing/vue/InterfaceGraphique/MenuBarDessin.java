package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * La classe MenuBarDessin représente la barre de menu de l'application de dessin.
 * Elle étend JMenuBar et contient les menus Fichier, Outils, Mode et Aide.
 * 
 * @author Islem
 */
public class MenuBarDessin extends JMenuBar {

    // Variables de classe
    private final JFrame currentFrame;

    /**
     * Constructeur de la classe MenuBarDessin.
     * Initialise les composants de la barre de menu.
     * 
     * @param frame La fenêtre principale
     * @param interfacedessin L'interface de dessin associée
     */
    public MenuBarDessin(JFrame frame, InterfaceDessin interfacedessin) {
        currentFrame = frame;

        // Créer le menu Fichier
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem newItem = new JMenuItem("Nouveau");
        
        newItem.addActionListener((ActionEvent e) -> {
            // Cree une nouvel page de dessin
            interfacedessin.CreeNouvelInterfaceDessin();
        });
        
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        
        saveItem.addActionListener((ActionEvent e) -> {
            // Enregistrer le dessin comme une image JPG
            interfacedessin.SauvegarderImageCommeJPG();
        });
        
        
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

        circleItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Cercle");
        });

        JMenuItem rectangleItem = new JMenuItem("Rectangle");

        rectangleItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Rectangle");
        });

        JMenuItem moveItem = new JMenuItem("Déplacer");
        
        moveItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Déplacer");
        });
        
        JMenuItem lineItem = new JMenuItem("Ligne");
        
        lineItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Ligne");
        });
        
        JMenuItem paintItem = new JMenuItem("Coloration");
        
        paintItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Coloration");
        });
        
        JMenuItem undoItem = new JMenuItem("Annuler");
        JMenuItem redoItem = new JMenuItem("Refaire");
        JMenuItem deleteItem = new JMenuItem("Supprimer");
        
        deleteItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Supprimer");
        });
        
        menuOutils.add(circleItem);
        menuOutils.add(rectangleItem);
        menuOutils.add(moveItem);
        menuOutils.add(lineItem);
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
        FenetreInformation fInformation = new FenetreInformation();
        fInformation.setVisible(true);
    }

    // Methode qui ouvre la fenetre Contact
    private void handleContactAction() {
        FenetreContact fcontact = new FenetreContact();
        fcontact.setVisible(true);
    }

    // Methode qui change le mode de dessin vers jeu
    private void handleJeuAction() {
        FenetreJeu fJeu = new FenetreJeu();
        fJeu.setVisible(true);

        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}

