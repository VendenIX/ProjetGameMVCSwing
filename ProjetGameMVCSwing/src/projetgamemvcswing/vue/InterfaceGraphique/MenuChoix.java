package projetgamemvcswing.vue.InterfaceGraphique;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.FenetreJeu;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.FenetreDessin;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import projetgamemvcswing.strategy.EasyModeSolver;
import projetgamemvcswing.strategy.MediumModeSolver;
import projetgamemvcswing.strategy.SolverStrategy;

/**
 *
 * @author Islem
 */

public class MenuChoix extends JFrame {
    
    private static MenuChoix instance;
    private Image backgroundImage;
    private JComboBox<String> levelSelector;
    
    public MenuChoix() {
        // Configuration de le JFrame principal
        setTitle("Dessin et Jeux de Formes");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // icon application
        setIconImage(new ImageIcon("images/app_image.png").getImage());

        // Charger l'image du fond
        backgroundImage = new ImageIcon("images/colorful_oil.jpg").getImage();

        // Création d'un JPanel
        JPanel panelMenuChoix = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50)) {
            @Override
            // Changement du fond vers une image
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        // import et redimensionnement des icones
        Icon dessinIcon = new ImageIcon(new ImageIcon("images/pal.png")
                .getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH));
        
        Icon jeuIcon = new ImageIcon(new ImageIcon("images/cont.png")
                .getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH));


        // Création d'une Box/Container verticale pour Label + 2 boutons
        Box mainVerticalBox = Box.createVerticalBox();
        // Ajout d'une zone rigide pour le centrage vertical.
        mainVerticalBox.add(Box.createVerticalGlue());
        
        // Création d'une Box/Container verticale pour chaque bouton et son label
        Box buttonDessinVerticalBox = Box.createVerticalBox();
        buttonDessinVerticalBox.add(Box.createVerticalGlue());
        
        Box buttonJeuVerticalBox = Box.createVerticalBox();
        buttonJeuVerticalBox.add(Box.createVerticalGlue());
        
        // Ajout du Label principal
        JLabel label = new JLabel("Veuillez Choisir Un Mode");
        label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 22));

        // Centrage du label horizontalement
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainVerticalBox.add(label);

        // Ajout d'une zone rigide pour l'espacement vertical
        mainVerticalBox.add(Box.createRigidArea(new java.awt.Dimension(0, 50)));

        // Création d'une Box pour l'alignement horizontal des boutons
        Box mainHorizontalBox = Box.createHorizontalBox();
        
        // JButton avec zoom effect on hover
        JButton dessinButton = createZoomButton(dessinIcon);
        JButton jeuButton = createZoomButton(jeuIcon);
        
        // Ajout des labels pour les boutons
        JLabel dessinButtonTitre = new JLabel("Mode Dessin");
        dessinButtonTitre.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        
        JLabel jeuButtonTitre = new JLabel(" Mode Jeu");
        jeuButtonTitre.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        
        // Ajout ActionListener pour gérer les clics sur les boutons
        dessinButton.addActionListener((ActionEvent e) -> onDessinButtonClick());
        jeuButton.addActionListener((ActionEvent e) -> onJeuButtonClick());

        // Ajout des boutons à au conteneur boutons après le conteneur principal
        buttonDessinVerticalBox.add(dessinButton);
        buttonDessinVerticalBox.add(Box.createRigidArea(new java.awt.Dimension(0, 20)));        
        buttonDessinVerticalBox.add(dessinButtonTitre);
        mainHorizontalBox.add(buttonDessinVerticalBox);
        
        mainHorizontalBox.add(Box.createRigidArea(new java.awt.Dimension(120, 0)));
        
        buttonJeuVerticalBox.add(jeuButton);
        buttonJeuVerticalBox.add(Box.createRigidArea(new java.awt.Dimension(0, 20)));
        buttonJeuVerticalBox.add(jeuButtonTitre);
        mainHorizontalBox.add(buttonJeuVerticalBox);

        // Ajout de la boîte horizontale à la boîte verticale
        mainVerticalBox.add(mainHorizontalBox);

        // Ajout d'une zone rigide pour le centrage vertical
        mainVerticalBox.add(Box.createVerticalGlue());

        
        // Ajout du sélecteur de niveau
        String[] levels = {"Facile", "Moyen"};
        levelSelector = new JComboBox<>(levels);
        levelSelector.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 18));
        levelSelector.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainVerticalBox.add(levelSelector);
        
        
        // Ajout de la boîte verticale au panneau
        panelMenuChoix.add(mainVerticalBox);

        // Ajout du JPanel principal au JFrame principal
        add(panelMenuChoix);
        
        // Affichage du JFrame
        setVisible(true);
    }
    
    /**
     * Méthode statique pour obtenir l'instance SINGLETON
     * @return 
     */
    public static MenuChoix getInstance() {
        if (instance == null) {
            instance = new MenuChoix();
        }
        return instance;
    }
    
    private JButton createZoomButton(Icon icon) {
        // Creation d'un button
        JButton button = new JButton(icon);
        
        // Rendre le buttons Invisible
        button.setText(null);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        
        // Effet hover en entrer et sortie de la sourie
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scaleButton(button, 1.1f);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scaleButton(button, 1.0f);
            }
        });

        return button;
    }

    private void scaleButton(JButton button, float scale) {
        
    // Obtenir les dimensions originales avant mise à l'échelle
    Dimension originalSize = button.getPreferredSize();

    // Extraire la largeur et la hauteur originales
    int width = originalSize.width;
    int height = originalSize.height;

    // Calculer les dimensions après mise à l'échelle
    int scaledWidth = (int) (width * scale);
    int scaledHeight = (int) (height * scale);

    // Obtenir l'icône d'origine du bouton
    ImageIcon originalIcon = (ImageIcon) button.getIcon();

    // Mettre à l'échelle l'image de l'icône
    Image scaledImage = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

    // Créer une nouvelle icône mise à l'échelle
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    // Appliquer la nouvelle icône mise à l'échelle au bouton
    button.setIcon(scaledIcon);

    // Rétablir les dimensions originales pour une utilisation ultérieure
    button.setPreferredSize(originalSize);
    button.setSize(originalSize);
    
    }
    
    // Methode qui ouvre la fenetre Dessin
    private void onDessinButtonClick() {
        FenetreDessin fDessin = new FenetreDessin();
        fDessin.setVisible(true);
        dispose();
    }

    // Methode qui ouvre la fenetre Jeu
    private void onJeuButtonClick() {
        SolverStrategy strategy;
        if (levelSelector.getSelectedItem().equals("Facile")) {
            strategy = new EasyModeSolver();
        } else {
            strategy = new MediumModeSolver();
        }
        FenetreJeu fJeu = new FenetreJeu(strategy);
        fJeu.setVisible(true);
        dispose();
    }
}
