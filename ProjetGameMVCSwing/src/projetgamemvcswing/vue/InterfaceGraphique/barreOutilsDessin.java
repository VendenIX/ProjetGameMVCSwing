package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;

/**
 * La classe barreOutilsDessin représente une barre d'outils pour les dessins.
 * Elle étend JToolBar et fournit des boutons pour différentes actions.
 * 
 * @author Islem
 */
public class BarreOutilsDessin extends JToolBar {
    
    private final JFrame currentFrame;
    
    private final JButton circleButton;
    private final JButton rectangleButton;
    private final JButton moveButton;
    private final JButton undoButton;
    private final JButton redoButton;
    private final JButton deleteButton;
    private final JButton lineButton;
    private final JButton paintBucketButton;
    private final JButton jeuModeButton;
    

    /**
     * Constructeur de la classe barreOutilsDessin.
     * 
     * @param frame La JFrame associée à la barre d'outils.
     * @param interfacedessin L'InterfaceDessin associée à la barre d'outils.
     */
    public BarreOutilsDessin(JFrame frame, InterfaceDessin interfacedessin) {
                
        currentFrame = frame;
        
        // Définir l'orientation comme verticale
        setOrientation(JToolBar.VERTICAL);

        // Créer des boutons avec des icônes personnalisées
        
        // Initialisation bouton Circle
        circleButton = createToolbarButton("images/circle.png", "Dessin Cercle");
        
        // Lorsque le bouton de cercle est cliqué
        circleButton.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Cercle");
        });
        // Initialisation bouton Rectangle
        rectangleButton = createToolbarButton("images/rectangle.png", "Dessin Rectangle");
        
        // Lorsque le bouton de Rectangle est cliqué
        rectangleButton.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Rectangle");
        });
        
        // Initialisation bouton Ligne
        lineButton = createToolbarButton("images/line.png", "Ligne");
        
        // Lorsque le bouton de Ligne est cliqué
        lineButton.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Ligne");
        });
        
        // Initialisation bouton Deplacer
        moveButton = createToolbarButton("images/move-selection.png", "Déplacement de Formes");
        
        // Lorsque le bouton de Deplacer est cliqué
        moveButton.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Déplacer");
        });
        
        // Initialisation bouton Undo
        undoButton = createToolbarButton("images/undo.png", "Annuler");
        
        // Initialisation bouton Redo
        redoButton = createToolbarButton("images/redo.png", "Refaire");
        
        // Initialisation bouton Supprimer
        deleteButton = createToolbarButton("images/delete.png", "Supprimer");
        
        // Lorsque le bouton de Supprimer est cliqué
        deleteButton.addActionListener((ActionEvent e) -> {
            interfacedessin.setcurrentDrawState("Supprimer");
        });
        
        // Initialisation bouton Coloration
        paintBucketButton = createToolbarButton("images/paint-bucket.png", "Coloration");
        
        // Lorsque le bouton de Coloration est cliqué
        paintBucketButton.addActionListener((ActionEvent e) -> {
            
            // Affiche une boîte de dialogue de choix de couleur avec le composant parent "currentFrame",
            // un titre de dialogue "Choix de Couleur" et une couleur par défaut "currentFrame.getBackground()".
            Color selectedColor = JColorChooser.showDialog(
                currentFrame, // Composant parent
                "Choix de Couleur", // Titre du dialogue
                currentFrame.getBackground() // Couleur par défaut
            );
            
            interfacedessin.setcurrentDrawState("Coloration");
            interfacedessin.setSelectedColor(selectedColor);

        });
        
        // Initialisation bouton Changement de mode
        jeuModeButton = createToolbarButton("images/cont_change.png", "Mode Jeu");
        
        // Ajouter des boutons avec des icônes personnalisées a la JtoolBar
        add(circleButton);
        add(rectangleButton);
        add(lineButton);
        add(moveButton);
        add(undoButton);
        add(redoButton);
        add(deleteButton);
        add(paintBucketButton);
        
        
        // Ajouter une séparation
        addSeparator();
        add(Box.createHorizontalStrut(10));
        
        // ActionListener changement de mode vers Jeu
        jeuModeButton.addActionListener((ActionEvent e) -> {
            onJeuModeButtonClick();
        });
        
        // Ajouter un bouton pour changer le mode
        add(jeuModeButton);

        // Ajuster la taille préférée pour la rendre fine
        setPreferredSize(new Dimension(40, 400));
        setFloatable(false);
    }
    

    /**
     * Crée un bouton de la barre d'outils avec une icône et une description.
     * 
     * @param iconPath Le chemin de l'icône du bouton.
     * @param description La description du bouton pour l'infobulle.
     * @return Le bouton créé.
     */
    private JButton createToolbarButton(String iconPath, String description) {
        // Créer une ImageIcon à partir du chemin de fichier spécifié
        ImageIcon originalIcon = new ImageIcon(iconPath);

        // Créer une icône sans mise à l'échelle
        NoScalingIcon icon = new NoScalingIcon(originalIcon);
        
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
    
    /**
     * Méthode ActionListener qui ouvre le JFrame de mode Jeu.
     */
    private void onJeuModeButtonClick() {
        FenetreJeu fJeu = new FenetreJeu();
        fJeu.setVisible(true);
        
        if (currentFrame != null) {
            currentFrame.dispose();
        }
    }
    
}
