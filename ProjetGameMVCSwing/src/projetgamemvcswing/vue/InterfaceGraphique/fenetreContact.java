package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Islem
 */


public class FenetreContact extends JFrame {

    public FenetreContact() {
        setTitle("Contact");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components and contact information
        JLabel titleLabel = new JLabel("Contactez-nous");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea contactInfoArea = new JTextArea();
        contactInfoArea.setEditable(false);
        contactInfoArea.setLineWrap(true);
        contactInfoArea.setWrapStyleWord(true);
        contactInfoArea.setText("Coordonnées de contact:\n"
                + "Email: contact@exemple.com\n"
                + "Site Web: www.exemple.com\n"
                + "Réseaux sociaux:\n"
                + "- Facebook: www.facebook.com/exemple\n"
                + "- Twitter: www.twitter.com/exemple");

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(contactInfoArea), BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
        
        // Positionner la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
