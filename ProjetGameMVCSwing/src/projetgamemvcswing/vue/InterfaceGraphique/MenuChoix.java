package projetgamemvcswing.vue.InterfaceGraphique;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.FenetreJeu;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.FenetreDessin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Fenêtre affichant le menu de choix entre le mode Dessin et le mode Jeu.
 * Cette classe représente une fenêtre qui affiche un menu permettant à l'utilisateur de choisir
 * entre le mode Dessin et le mode Jeu. Chaque mode est accompagné d'une icône et d'un titre.
 * L'utilisateur peut cliquer sur les boutons pour accéder au mode correspondant.
 */
public class MenuChoix extends JFrame {

    private final Image backgroundImage;

    /**
     * Constructeur de la fenêtre du menu de choix.
     * Initialise et configure les composants de la fenêtre, y compris le fond d'écran et les boutons de choix.
     */
    public MenuChoix() {
        // Configuration de la fenêtre principale
        setTitle("Dessin et Jeux de Formes"); // Titre de la fenêtre
        setSize(600, 450); // Taille de la fenêtre
        setLocationRelativeTo(null); // Positionnement au centre de l'écran
        setResizable(false); // Interdiction de redimensionner la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Action à la fermeture de la fenêtre
        setIconImage(new ImageIcon("images/app_image.png").getImage()); // Icône de l'application

        // Chargement de l'image de fond
        backgroundImage = new ImageIcon("images/colorful_oil.jpg").getImage();

        // Création d'un JPanel personnalisé avec un fond d'image
        JPanel panelMenuChoix = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Importation et redimensionnement des icônes pour les boutons
        Icon dessinIcon = new ImageIcon(new ImageIcon("images/pal.png")
                .getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH));

        Icon jeuIcon = new ImageIcon(new ImageIcon("images/cont.png")
                .getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH));

        // Création d'une boîte verticale pour le label et les boutons
        Box mainVerticalBox = Box.createVerticalBox();
        mainVerticalBox.add(Box.createVerticalGlue()); // Espace vertical pour le centrage

        // Création et configuration du label principal
        JLabel label = new JLabel("Veuillez Choisir Un Mode");
        label.setFont(new Font("Lucida Calligraphy", Font.BOLD, 22)); // Police du label
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrage horizontal
        mainVerticalBox.add(label); // Ajout du label

        mainVerticalBox.add(Box.createRigidArea(new Dimension(0, 50))); // Espace vertical

        // Création d'une boîte horizontale pour les boutons
        Box mainHorizontalBox = Box.createHorizontalBox();

        // Création des boutons avec l'effet de zoom au survol
        JButton dessinButton = createZoomButton(dessinIcon);
        JButton jeuButton = createZoomButton(jeuIcon);

        // Création et configuration des labels pour les boutons
        JLabel dessinButtonTitre = new JLabel("Mode Dessin");
        dessinButtonTitre.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));

        JLabel jeuButtonTitre = new JLabel(" Mode Jeu");
        jeuButtonTitre.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));

        // Ajout des écouteurs d'événements pour les boutons
        dessinButton.addActionListener((ActionEvent e) -> onDessinButtonClick());
        jeuButton.addActionListener((ActionEvent e) -> onJeuButtonClick());

        // Ajout des boutons et de leurs labels à la boîte horizontale
        mainHorizontalBox.add(createVerticalBoxWithComponents(dessinButton, dessinButtonTitre));
        mainHorizontalBox.add(Box.createRigidArea(new Dimension(120, 0))); // Espace horizontal
        mainHorizontalBox.add(createVerticalBoxWithComponents(jeuButton, jeuButtonTitre));

        // Ajout de la boîte horizontale à la boîte verticale principale
        mainVerticalBox.add(mainHorizontalBox);

        mainVerticalBox.add(Box.createVerticalGlue()); // Espace vertical pour le centrage

        // Ajout de la boîte verticale principale au JPanel
        panelMenuChoix.add(mainVerticalBox);

        // Ajout du JPanel à la JFrame principale
        add(panelMenuChoix);

        // Affichage de la JFrame principale
        setVisible(true);
    }

    /**
     * Crée un bouton avec l'effet de zoom au survol.
     * @param icon Icône du bouton.
     * @return Bouton avec l'effet de zoom au survol.
     */
    private JButton createZoomButton(Icon icon) {
        JButton button = new JButton(icon); // Création du bouton avec l'icône

        // Configuration pour rendre le bouton invisible et appliquer l'effet de zoom au survol
        button.setText(null);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);

        // Ajout des écouteurs de la souris pour l'effet de zoom
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                scaleButton(button, 1.1f); // Zoom lorsque la souris survole le bouton
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                scaleButton(button, 1.0f); // Retour à la taille normale lorsque la souris quitte le bouton
            }
        });

        return button;
    }

    /**
     * Applique l'effet de zoom sur un bouton.
     * @param button Bouton à zoomer.
     * @param scale Facteur d'échelle pour le zoom.
     */
    private void scaleButton(JButton button, float scale) {
        // Obtenir les dimensions originales du bouton
        Dimension originalSize = button.getPreferredSize();
        int width = originalSize.width;
        int height = originalSize.height;

        // Calcul des dimensions après mise à l'échelle
        int scaledWidth = (int) (width * scale);
        int scaledHeight = (int) (height * scale);

        // Mise à l'échelle de l'icône du bouton
        Image scaledImage = ((ImageIcon) button.getIcon()).getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        button.setIcon(scaledIcon);

        // Rétablissement des dimensions originales après mise à l'échelle
        button.setPreferredSize(originalSize);
        button.setSize(originalSize);
    }

    /**
     * Crée une boîte verticale avec un composant.
     * @param component Composant à ajouter à la boîte verticale.
     * @param titre Label associé au composant.
     * @return Boîte verticale contenant le composant et le titre.
     */
    private Box createVerticalBoxWithComponents(Component component, JLabel titre) {
        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(Box.createVerticalGlue()); // Espace pour le centrage vertical
        verticalBox.add(component); // Ajout du composant
        verticalBox.add(Box.createRigidArea(new Dimension(0, 20))); // Espace vertical
        verticalBox.add(titre); // Ajout du titre
        return verticalBox;
    }

    /**
     * Ouvre la fenêtre du mode Dessin lorsqu'on clique sur le bouton correspondant.
     */
    private void onDessinButtonClick() {
        FenetreDessin fDessin = new FenetreDessin();
        fDessin.setVisible(true);
        dispose(); // Ferme la fenêtre de menu de choix
    }

    /**
     * Ouvre la fenêtre du mode Jeu lorsqu'on clique sur le bouton correspondant.
     */
    private void onJeuButtonClick() {
        FenetreJeu fJeu = new FenetreJeu();
        fJeu.setVisible(true);
        dispose(); // Ferme la fenêtre de menu de choix
    }
}
