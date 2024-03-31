
package projetgamemvcswing.controller;

import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;
import java.awt.Color;
import java.util.Random;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.modele.geometry.Rectangle;


public class RandomShapeGenerator {
    
    public void generateFormes(PanelJeu panelJeu, int nombreDeFormes) {
        Random random = new Random();

        // Obtenir les dimensions du panneau
        int largeurPanneau = panelJeu.getWidth();
        int hauteurPanneau = panelJeu.getHeight();

        // Définir la largeur et la hauteur maximales des formes pour s'assurer qu'elles rentrent dans le panneau
        int largeurMax = largeurPanneau - 200; 
        int hauteurMax = hauteurPanneau - 200;

        for (int i = 0; i < nombreDeFormes; i++) {
            // Générer des coordonnées aléatoires pour la forme
            int x = random.nextInt(largeurMax);
            int y = random.nextInt(hauteurMax);

            // Générer une couleur aléatoire
            Color couleur = new Color(255,0, 0);

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
