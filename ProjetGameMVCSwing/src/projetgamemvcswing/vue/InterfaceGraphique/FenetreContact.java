package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

public class FenetreContact extends JFrame {

    /**
     * Constructeur de la fenêtre de contact.
     */
    public FenetreContact() {
        // Configuration de la fenêtre
        setTitle("Contact"); // Titre de la fenêtre
        setSize(400, 200); // Taille de la fenêtre
        setLocationRelativeTo(null); // Positionnement au centre de l'écran
        setResizable(false); // Interdiction de redimensionner la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Action à la fermeture de la fenêtre

        // Création des composants et ajout des informations de contact
        JLabel titleLabel = new JLabel("Contactez-nous");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20)); // Police du titre
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Alignement du titre au centre

        JTextArea contactInfoArea = new JTextArea();
        contactInfoArea.setEditable(false); // Empêche la modification du texte
        contactInfoArea.setLineWrap(true); // Activation du retour à la ligne automatique
        contactInfoArea.setWrapStyleWord(true); // Activation du retour à la ligne en fonction des mots
        contactInfoArea.setText("Coordonnées de contact:\n"
                + "MESSILI ISLEM: 22303045@etu.unicaen.fr\n"
                + "ANDRES Romain: 21904263@etu.unicaen.fr\n"
                + "LADUREE Luca: 21907062@etu.unicaen.fr\n"
                + "OROU-GUIDOU Amirath Fara: 22012235@etu.unicaen.fr\n"
                ); // Texte des informations de contact

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose()); // Action à effectuer lors du clic sur le bouton "Fermer"

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH); // Ajout du titre en haut
        panel.add(new JScrollPane(contactInfoArea), BorderLayout.CENTER); // Ajout des informations de contact au centre avec barre de défilement
        panel.add(closeButton, BorderLayout.SOUTH); // Ajout du bouton "Fermer" en bas

        add(panel); // Ajout du panneau à la fenêtre

        setLocationRelativeTo(null); // Positionnement de la fenêtre au centre de l'écran
        setVisible(true); // Rendre la fenêtre visible
    }
}
