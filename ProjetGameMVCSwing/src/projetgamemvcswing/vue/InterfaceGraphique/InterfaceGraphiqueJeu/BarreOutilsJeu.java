package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.FenetreDessin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import projetgamemvcswing.controller.State.JeuState.PlayCercle;
import projetgamemvcswing.controller.State.JeuState.PlayRectangle;
import projetgamemvcswing.vue.InterfaceGraphique.IconeNonRedimensionnable;

/**
 *
 * @author Islem
 */
public class BarreOutilsJeu extends JToolBar {
    
    private final JFrame currentFrame;
    
    private final JButton circleButton;
    private final JButton rectangleButton;
    private final JButton dessinModeButton;
    
    public BarreOutilsJeu(JFrame frame, PanelJeu panelJeu) {
        
        currentFrame = frame;
        
        // Définir l'orientation comme verticale
        setOrientation(JToolBar.VERTICAL);

        // Créer des boutons avec des icônes personnalisées
        circleButton = createToolbarButton("images/circle.png", "Dessin Cercle");
        
        circleButton.addActionListener((ActionEvent e) -> {
            panelJeu.setCurrentState(new PlayCercle());
        });
        
        rectangleButton = createToolbarButton("images/rectangle.png", "Dessin Rectangle");
        
        rectangleButton.addActionListener((ActionEvent e) -> {
            panelJeu.setCurrentState(new PlayRectangle());
        });
        
        dessinModeButton = createToolbarButton("images/pal_change.png", "Mode Dessin");
        
        // Ajouter des boutons avec des icônes personnalisées
        add(circleButton);
        add(rectangleButton);
        
        
        // Ajouter une separation
        addSeparator();
        add(Box.createHorizontalStrut(10));
        
        // ActionListener changement de mode vers Dessin
        dessinModeButton.addActionListener((ActionEvent e) -> {
            onDessinModeButtonClick();
        });
        
        // Ajouter un bouton pour changer le mode
        add(dessinModeButton);

        // Ajuster la taille préférée pour la rendre fine
        setPreferredSize(new Dimension(40, 400));
        setFloatable(false);
    }
    

    private JButton createToolbarButton(String iconPath, String description) {
        // Créer une ImageIcon à partir du chemin de fichier spécifié
        ImageIcon originalIcon = new ImageIcon(iconPath);

        // Créer une icône sans mise à l'échelle
        IconeNonRedimensionnable icon = new IconeNonRedimensionnable(originalIcon);
        
        // Créer un bouton avec l'icône personnalisée
        JButton button = new JButton(icon);

        // Définir des propriétés supplémentaires selon les besoins
        button.setToolTipText(description);

        // Supprimer la bordure par défaut
        button.setBorderPainted(false);

        // Créer une bordure en biseau pour un effet 3D
        Border bevelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()
        );

        // Définir la bordure personnalisée pour le bouton
        button.setBorder(bevelBorder);

        // Ajouter tout ActionListener ou autres configurations ici

        return button;
    }
    
    // ActionListener methode qui ouvre le Jframe de mode Jeu
    private void onDessinModeButtonClick() {
        FenetreDessin fDessin = new FenetreDessin();
        fDessin.setVisible(true);
        
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
