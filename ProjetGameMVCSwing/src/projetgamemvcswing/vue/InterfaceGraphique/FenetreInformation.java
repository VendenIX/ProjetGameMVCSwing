package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Islem
 */
public class FenetreInformation extends JFrame {

    public FenetreInformation() {
        setTitle("À propos de l'application");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add components and information about your application
        JLabel titleLabel = new JLabel("Nom de l'application");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea informationArea = new JTextArea();
        informationArea.setEditable(false);
        informationArea.setLineWrap(true);
        informationArea.setWrapStyleWord(true);
        informationArea.setText("Informations sur l'application:\n"
                + "Version: 1.0\n"
                + "Auteur: Islem\n"
                + "Date de création: [Date]\n"
                + "Description: [Description de l'application]");

        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(informationArea), BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
        
        // Positionner la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }
}