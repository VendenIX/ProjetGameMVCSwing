package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * La classe MenuBarDessin représente la barre de menu de l'application de dessin.
 * Elle étend JMenuBar et contient les menus Fichier, Outils, Mode et Aide.
 * 
 */
public class MenuBarDessin extends JMenuBar {

    // Variables de la Jframe encours
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
        
        // Créer l'item Nouveau
        JMenuItem newItem = new JMenuItem("Nouveau");
        
        // Ecouter pour l'item newItem/Nouveau
        newItem.addActionListener((ActionEvent e) -> {
            // Crée une nouvelle page de dessin
            interfacedessin.CreeNouvelInterfaceDessin();
        });
        
        // Créer l'item Enregistrer
        JMenuItem saveItem = new JMenuItem("Enregistrer");
        
        // Ecouter pour l'item saveItem/Enregistrer
        saveItem.addActionListener((ActionEvent e) -> {
            // Enregistrer le dessin comme une image JPG
            interfacedessin.SauvegarderImageCommeJPG();
        });
        
        // Créer l'item Sortir
        JMenuItem exitItem = new JMenuItem("Sortir");

        // Ecouter pour l'item exitItem/Sortir
        exitItem.addActionListener((ActionEvent e) -> {
            // Méthode pour fermer la fenêtre courante
            handleExitAction(currentFrame);
        });
        
        // Ajouter les items dans le menu Fichier
        menuFichier.add(newItem);
        menuFichier.add(saveItem);
        menuFichier.add(exitItem);

        // Créer le menu Outils
        JMenu menuOutils = new JMenu("Outils");
        
        // Créer l'item Cercle
        JMenuItem circleItem = new JMenuItem("Cercle");

        circleItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Cercle");
        });

        // Créer l'item Rectangle
        JMenuItem rectangleItem = new JMenuItem("Rectangle");

        // Ecouter pour l'item rectangleItem/Rectangle
        rectangleItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Rectangle");
        });

        // Créer l'item Déplacer
        JMenuItem moveItem = new JMenuItem("Déplacer");
        
        // Ecouter pour l'item moveItem/Déplacer
        moveItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Déplacer");
        });
        
        // Créer l'item Ligne
        JMenuItem lineItem = new JMenuItem("Ligne");
        
        // Ecouter pour l'item lineItem/Ligne
        lineItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Ligne");
        });
        
        // Créer l'item Coloration
        JMenuItem paintItem = new JMenuItem("Coloration");
        
        // Ecouter pour l'item lineItem/Ligne
        paintItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Coloration");
        });
        
        // Créer l'item Annuler
        JMenuItem undoItem = new JMenuItem("Annuler");
        
        // Créer l'item Refaire
        JMenuItem redoItem = new JMenuItem("Refaire");
        
        // Créer l'item Supprimer
        JMenuItem deleteItem = new JMenuItem("Supprimer");
        
        // Ecouter pour l'item deleteItem/Supprimer
        deleteItem.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Supprimer");
        });
        
        // Ajouter les items dans le menu Outils
        menuOutils.add(circleItem);
        menuOutils.add(rectangleItem);
        menuOutils.add(moveItem);
        menuOutils.add(lineItem);
        menuOutils.add(paintItem);
        menuOutils.add(undoItem);
        menuOutils.add(redoItem);
        menuOutils.add(deleteItem);

        // Créer le menu Mode
        JMenu menuMode = new JMenu("Mode");
        
        // Créer l'item jeuItem
        JMenuItem jeuItem = new JMenuItem("Mode Jeu");

        // Ecouter pour l'item jeuItem/ Changer le mode vers jeu
        jeuItem.addActionListener((ActionEvent e) -> {
            // Méthode pour changer le mode vers jeu
            handleJeuAction();
        });

        // Ajouter les items dans le menu Mode
        menuMode.add(jeuItem);

        // Créer le menu Aide
        JMenu menuAide = new JMenu("Aide");
        
        // Créer l'item infoItem
        JMenuItem infoItem = new JMenuItem("Information");
        
        // Créer l'item contactItem
        JMenuItem contactItem = new JMenuItem("Contact");
        menuAide.add(infoItem);
        menuAide.add(contactItem);

        // Ecouter pour l'item infoItem/Afficher une petite fenêtre Information
        infoItem.addActionListener((ActionEvent e) -> {
            // Méthode pour afficher une petite fenêtre Information
            handleInformationAction();
        });

        // Ecouter pour l'item contactItem/Afficher une petite fenêtre Contact
        contactItem.addActionListener((ActionEvent e) -> {
            // Méthode pour afficher une petite fenêtre Contact
            handleContactAction();
        });

        // Ajouter les menus à la barre de menu
        add(menuFichier);
        add(menuOutils);
        add(menuMode);
        add(menuAide);
    }

    // Méthode de fermeture de la fenêtre Dessin avec un message d'alerte
    private static void handleExitAction(JFrame frame) {
        int option = JOptionPane.showConfirmDialog(frame, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Méthode qui ouvre la fenêtre Information
    private void handleInformationAction() {
        FenetreInformation fInformation = new FenetreInformation();
        fInformation.setVisible(true);
    }

    // Méthode qui ouvre la fenêtre Contact
    private void handleContactAction() {
        FenetreContact fcontact = new FenetreContact();
        fcontact.setVisible(true);
    }

    // Méthode qui change le mode de dessin vers jeu
    private void handleJeuAction() {
        FenetreJeu fJeu = new FenetreJeu();
        fJeu.setVisible(true);

        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}