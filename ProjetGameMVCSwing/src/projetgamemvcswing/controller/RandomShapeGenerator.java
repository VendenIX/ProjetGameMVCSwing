package projetgamemvcswing.controller;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;

import java.awt.Color;
import java.util.Random;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * Classe utilisée pour générer des formes aléatoires dans un PanelJeu.
 */
public class RandomShapeGenerator {
    
    /**
     * Génère un certain nombre de formes aléatoires et les ajoute au PanelJeu spécifié.
     *
     * @param panelJeu        Le PanelJeu dans lequel ajouter les formes.
     * @param nombreDeFormes  Le nombre de formes à générer.
     */
    public void generateFormes(PanelJeu panelJeu, int nombreDeFormes) {
        Random random = new Random();

        // Obtenir les dimensions du panneau
        int largeurPanneau = panelJeu.getWidth();
        int hauteurPanneau = panelJeu.getHeight();

        // Définir la largeur et la hauteur maximales des formes pour s'assurer qu'elles rentrent dans le panneau
        int largeurMax = largeurPanneau - 200;
        int hauteurMax = hauteurPanneau - 200;

        // Définir la distance minimale entre les formes
        int distanceMinimale = 100;

        for (int i = 0; i < nombreDeFormes; i++) {
            // Générer des coordonnées aléatoires pour la forme
            int x = random.nextInt(largeurMax);
            int y = random.nextInt(hauteurMax);

            // Ajouter un décalage entre les formes pour les espacer
            if (i > 0) {
                x += distanceMinimale * Math.pow(-1, i); // Alterner entre décalage positif et négatif
                y += distanceMinimale * Math.pow(-1, i); // Alterner entre décalage positif et négatif
            }

            // Générer une couleur aléatoire
            Color couleur = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            // Choisir aléatoirement entre un rectangle et un cercle
            boolean estRectangle = random.nextBoolean();

            // Générer une taille aléatoire pour la forme
            int largeur = random.nextInt(350) + 120; // Largeur entre 50 et 250
            int hauteur = random.nextInt(350) + 120; // Hauteur entre 50 et 250

            // Créer un rectangle ou un cercle en fonction du choix aléatoire
            if (estRectangle) {
                Rectangle rectangle = new Rectangle(x, y, largeur, hauteur, couleur);

                // Ajouter le rectangle à la liste des formes du panneau
                panelJeu.getFigures().add(rectangle);
            } else {
                // Pour simplifier, créons un cercle
                int rayon = Math.min(largeur, hauteur) / 2;
                Cercle cercle = new Cercle(new Point(x, y), rayon, couleur);

                // Ajouter le cercle à la liste des formes du panneau
                panelJeu.getFigures().add(cercle);
            }
        }
    }
}
