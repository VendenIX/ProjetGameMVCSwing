
package projetgamemvcswing.controller;

// Importation des bibliotheque
import java.awt.Color;
import java.awt.Graphics;
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
        // Boucler sur la liste des figures et utiliser differentes methodes pour les Afficher
        for (Figure forme : figures) {
            if (forme instanceof Cercle) {
                // Afficher la figure si c'est un cercle
                drawCircle(g, (Cercle) forme);
            } else if (forme instanceof Rectangle) {
                // Afficher la figure si c'est un rectangle
                drawRectangle(g, (Rectangle) forme);
            } else if (forme instanceof Ligne) {
                // Afficher la figure si c'est unze ligne
                drawLine(g, (Ligne) forme);
            }
        }
    }

    /**
     * Methode qui utilise g.drawOval pour Afficher / Dessiner un cercle.
     *
     * @param g      L'objet Graphics pour dessiner.
     * @param cercle Le cercle à dessiner.
     */
    private void drawCircle(Graphics g, Cercle cercle) {
        // Couleur de cercle
        g.setColor(Color.BLACK);
        
        // Recuperation Centre de cercle
        int x = (int) Math.round(cercle.getX() - cercle.getRayon());
        int y = (int) Math.round(cercle.getY() - cercle.getRayon());
        
        // Recuperation Rayon de Cercle
        int diametre = (int) Math.round(2 * cercle.getRayon());
        
        // Affichage de cercle
        g.drawOval(x, y, diametre, diametre);
    }

    /**
     * Methode qui utilise g.drawRect pour Afficher / Dessiner un rectangle.
     *
     * @param g          L'objet Graphics pour dessiner.
     * @param rectangle  Le rectangle à dessiner.
     */
    private void drawRectangle(Graphics g, Rectangle rectangle) {
        // Couleur de rectangle
        g.setColor(Color.BLACK);

        // Recuperation Point de Depart de rectangle 
        int xInt = (int) Math.round(rectangle.getX());
        int yInt = (int) Math.round(rectangle.getY());
        
        // Recuperation Largeur/Hauteur de rectangle 
        int largeurInt = (int) Math.round(rectangle.getLargeur());
        int hauteurInt = (int) Math.round(rectangle.getHauteur());
        
        // Affichage de rectangle
        g.drawRect(xInt, yInt, largeurInt, hauteurInt);
    }

    /**
     * Dessine une ligne sur le composant graphique Graphics.
     *
     * @param g     L'objet Graphics pour dessiner.
     * @param ligne La ligne à dessiner.
     */
    private void drawLine(Graphics g, Ligne ligne) {
        // Recuperation Point de debut de la ligne 
        int x1 = (int) Math.round(ligne.getXDebut());
        int y1 = (int) Math.round(ligne.getYDebut());
        
        // Recuperation Point de fin de la ligne
        int x2 = (int) Math.round(ligne.getXFin());
        int y2 = (int) Math.round(ligne.getYFin());

        // Affichage de Ligne
        g.drawLine(x1, y1, x2, y2);
    }
}


