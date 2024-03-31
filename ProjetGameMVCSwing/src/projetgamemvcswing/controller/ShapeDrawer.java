
package projetgamemvcswing.controller;

// Importation des bibliotheque
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * La classe ShapeDrawer utilise des methode pour dessine les différentes formes géométriques sur notre Panel de Dessin.
 */
public class ShapeDrawer {

    /**
     * Parcours la Array liste des figures enregistrer 
     * et Affiche les différentes figures géométriques sur cette array Liste .
     * 
     * @param g       L'objet Graphics pour dessiner.
     * @param figures La liste des figures à dessiner.
     */
    public void drawFigures(Graphics g, List<Figure> figures) {
        for (Figure figure : figures) {
            g.setColor(figure.getCouleur()); //cette fabuleuse ligne m'a fait perdre 15 minutes :D
            drawFigure(g, figure); // Utilise une méthode générique pour dessiner chaque figure
        }
    }
    
    /**
     * Permet de colorier une figure
     * @param g
     * @param figure 
     */
    public void drawFigure(Graphics g, Figure figure) {
        if (figure != null) {
            figure.dessiner((Graphics2D) g); // Appelle la méthode de dessin de la figure
        }
    }
}


