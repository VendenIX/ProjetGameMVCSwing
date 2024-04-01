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
 * classe qui permet de créer une JToolBar de barre d'outil pour le jeu
 * @author romain
 */
public class BarreOutilsJeu extends JToolBar {
    
    private final JFrame currentFrame;
    
    private final JButton circleButton;
    private final JButton rectangleButton;
    private final JButton dessinModeButton;
    
    /**
     * Créer une barre d'outil de jeu qui prend en entrée une jframe et une instance de PanelJeu
     * @param frame
     * @param panelJeu 
     */
    public BarreOutilsJeu(JFrame frame, PanelJeu panelJeu) {
        
        currentFrame = frame;
        
        // définir l'orientation comme verticale
        setOrientation(JToolBar.VERTICAL);

        // créer des boutons avec des icônes personnalisées
        circleButton = createToolbarButton("images/circle.png", "Dessin Cercle");
        
        circleButton.addActionListener((ActionEvent e) -> {
            panelJeu.setCurrentState(new PlayCercle());
        });
        
        rectangleButton = createToolbarButton("images/rectangle.png", "Dessin Rectangle");
        
        rectangleButton.addActionListener((ActionEvent e) -> {
            panelJeu.setCurrentState(new PlayRectangle());
        });
        
        dessinModeButton = createToolbarButton("images/pal_change.png", "Mode Dessin");
        
        // ajouter des boutons avec des icônes personnalisées
        add(circleButton);
        add(rectangleButton);
        
        
        // ajouter une separation
        addSeparator();
        add(Box.createHorizontalStrut(10));
        
        // actionListener changement de mode vers Dessin
        dessinModeButton.addActionListener((ActionEvent e) -> {
            onDessinModeButtonClick();
        });
        
        // ajouter un bouton pour changer le mode
        add(dessinModeButton);

        // ajuster la taille préférée pour la rendre fine
        setPreferredSize(new Dimension(40, 400));
        setFloatable(false);
    }
    
    /**
     * creer une tool bar pour les bouttons
     * @param iconPath
     * @param description
     * @return 
     */
    private JButton createToolbarButton(String iconPath, String description) {
        // créer une ImageIcon à partir du chemin de fichier spécifié
        ImageIcon originalIcon = new ImageIcon(iconPath);

        // créer une icône sans mise à l'échelle
        IconeNonRedimensionnable icon = new IconeNonRedimensionnable(originalIcon);
        
        // créer un bouton avec l'icône personnalisée
        JButton button = new JButton(icon);

        // définir des propriétés supplémentaires selon les besoins
        button.setToolTipText(description);

        // supprimer la bordure par défaut
        button.setBorderPainted(false);

        // créer une bordure en biseau pour un effet 3D
        Border bevelBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()
        );

        // définir la bordure personnalisée pour le bouton
        button.setBorder(bevelBorder);

        // ajouter tout ActionListener ou autres configurations ici

        return button;
    }
    
    // actionListener methode qui ouvre le Jframe de mode Jeu
    private void onDessinModeButtonClick() {
        FenetreDessin fDessin = new FenetreDessin();
        fDessin.setVisible(true);
        
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
}
