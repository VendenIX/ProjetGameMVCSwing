package projetgamemvcswing.modele.factory;

import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import java.awt.Color;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 21907062@CAMPUS
 * 
 * Cette classe permet de séparer la création des instances de formes géométriques du reste,
 * rendant le code plus modulaire.
 * 
 */

public class FormesFactory {
    
    // Méthode pour créer un rectangle
    public static Rectangle createRectangle(double x, double y, double largeur, double hauteur, Color couleur) {
        return new Rectangle(x, y, largeur, hauteur, couleur);
    }
    
    // Méthode pour créer un cercle
    public static Cercle createCercle(Point centre, double rayon, Color couleur) {
        return new Cercle(centre, rayon, couleur);
    }
    
    // Méthode pour créer un point
    public static Point createPoint(double x, double y) {
        return new Point(x, y);
    }
    
    public static List<Figure> createFormesObstacles(PanelJeu panel, int niveauDifficulte) {
        List<Figure> obstacles = new ArrayList<>();
        Random random = new Random();   

        //3 formes par niveau de difficulté
        int nombreObstacles = niveauDifficulte * 3;

        for (int i = 0; i < nombreObstacles; i++) {
            //Récupération des dimensions de la surface de jeu
            double x = random.nextDouble() * 800;//panel.getWidth(); 
            double y = random.nextDouble() * 600;//panel.getHeight();
            
            // Couleur par défaut pour les obstacles
            Color couleur = Color.RED; 
            
            // Taille aléatoire entre 20 et 70
            double taille = random.nextDouble() * 50 + 20; 

            // Vérifier les collisions avec les formes existantes
            boolean collision = false;
            for (Figure forme : obstacles) {
                if (forme instanceof Rectangle) {
                    Rectangle obstacle = (Rectangle) forme;
                    if (x + taille > obstacle.getX() && x < obstacle.getX() + obstacle.getLargeur() &&
                        y + taille > obstacle.getY() && y < obstacle.getY() + obstacle.getHauteur()) {
                        collision = true;
                        break;
                    }
                } else if (forme instanceof Cercle) {
                    Cercle obstacle = (Cercle) forme;
                    if (Math.sqrt(Math.pow(x - obstacle.getCentre().getX(), 2) + Math.pow(y - obstacle.getCentre().getY(), 2)) < taille + obstacle.getRayon()) {
                        collision = true;
                        break;
                    }
                }
            }

            // Si collision détectée, ajuster la position
            while (collision) {
                x = random.nextDouble() * 800;//panel.getWidth();
                y = random.nextDouble() * 600;//panel.getHeight();
            }

            // Ajouter la forme obstacle à la liste
            if (random.nextBoolean()) {
                obstacles.add(createRectangle(x, y, taille, taille, couleur));
            } else {
                obstacles.add(createCercle(createPoint(x, y), taille / 2, couleur));
            }
        }

        return obstacles;
    }
}
