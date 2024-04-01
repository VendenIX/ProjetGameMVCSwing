package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre affichant les informations sur l'application.
 * Cette classe représente une fenêtre d'information contenant des détails sur l'application.
 * Elle affiche le nom de l'application, sa version, l'auteur, la date de création et une brève description.
 * La fenêtre se ferme lorsqu'on appuie sur le bouton "Fermer".
 * @author Islem
 */
public class FenetreInformation extends JFrame {

    /**
     * Constructeur de la fenêtre d'information.
     * Initialise et configure les composants de la fenêtre, ainsi que les informations sur l'application.
     */
    public FenetreInformation() {
        // Configuration de la fenêtre
        setTitle("À propos de l'application"); // Titre de la fenêtre
        setSize(400, 200); // Taille de la fenêtre
        setLocationRelativeTo(null); // Positionnement au centre de l'écran
        setResizable(false); // Interdiction de redimensionner la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Action à la fermeture de la fenêtre

        // Création des composants et ajout des informations sur l'application
        JLabel titleLabel = new JLabel("Dessin et Jeu de Formes");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20)); // Police du titre
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement du titre au centre

        JTextArea informationArea = new JTextArea();
        informationArea.setEditable(false); // Empêche la modification du texte
        informationArea.setLineWrap(true); // Activation du retour à la ligne automatique
        informationArea.setWrapStyleWord(true); // Activation du retour à la ligne en fonction des mots
        informationArea.setText("Informations sur l'application:\n"
                + "Version: 1.0\n"
                + "Auteur: GroupeEtudiants\n"
                + "Date de création: 01/04/2024\n"
                + "Description: Dessin et Jeu de Formes"); // Texte des informations sur l'application

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose()); // Action à effectuer lors du clic sur le bouton "Fermer"

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH); // Ajout du titre en haut
        panel.add(new JScrollPane(informationArea), BorderLayout.CENTER); // Ajout des informations sur l'application au centre avec barre de défilement
        panel.add(closeButton, BorderLayout.SOUTH); // Ajout du bouton "Fermer" en bas

        add(panel); // Ajout du panneau à la fenêtre

        setLocationRelativeTo(null); // Positionnement de la fenêtre au centre de l'écran
        setVisible(true); // Rendre la fenêtre visible
    }
}
