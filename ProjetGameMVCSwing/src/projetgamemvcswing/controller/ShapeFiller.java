package projetgamemvcswing.controller;

// Importation des bibliotheque
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.List;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * La classe ShapeFiller contient des méthodes pour Dessiner/Afficher des formes
 * géométriques remplies avec un couleur choisi par l'utilisateur.
 */
public class ShapeFiller {

    /**
     * Parcours la Array liste des figures enregistrer
     *  et Dessine/Affiche les figures géométriques remplies avec un couleur choisi par l'utilisateur.
     *
     * @param g2d     L'objet Graphics2D pour dessiner.
     * @param figures La liste des figures à dessiner.
     */
    public void drawFilledFigures(Graphics2D g2d, List<Figure> figures) {
        for (Figure forme : figures) {
            if (forme instanceof Cercle) {
                // Afficher/Dessine un cercle rempli
                Cercle cercle = (Cercle) forme;
                
                // Recuperer la couleur choisi
                g2d.setColor(cercle.getCouleur());
                
                // Remplisage et dessin
                fillCircle(g2d, cercle);
            } else if (forme instanceof Rectangle) {
                // Afficher/Dessine un rectangle rempli
                Rectangle rectangle = (Rectangle) forme;
                
                // Recuperer la couleur choisi
                g2d.setColor(rectangle.getCouleur());
                
                // Remplisage et dessin
                fillRectangle(g2d, rectangle);
            } else if (forme instanceof Ligne) {
                // Afficher/Dessine une ligne remplie (non standard)
                Ligne ligne = (Ligne) forme;
                
                // Recuperer la couleur choisi
                g2d.setColor(ligne.getCouleur());
                
                // Remplisage et dessin
                fillLine(g2d, ligne);
            }
        }
    }

    /**
     * Dessine un cercle rempli sur le Graphics2D.
     *
     * @param g2d    L'objet Graphics2D pour dessiner.
     * @param cercle Le cercle à dessiner.
     */
    private void fillCircle(Graphics2D g2d, Cercle cercle) {
        
        int x = (int) Math.round(cercle.getX() - cercle.getRayon());
        int y = (int) Math.round(cercle.getY() - cercle.getRayon());
        int diameter = (int) Math.round(2 * cercle.getRayon());
        
        // Affichage de figure rempler
        g2d.fillOval(x, y, diameter, diameter);
    }

    /**
     * Dessine un rectangle rempli sur le Graphics2D.
     *
     * @param g2d       L'objet Graphics2D pour dessiner.
     * @param rectangle Le rectangle à dessiner.
     */
    private void fillRectangle(Graphics2D g2d, Rectangle rectangle) {
        int xInt = (int) Math.round(rectangle.getX());
        int yInt = (int) Math.round(rectangle.getY());
        int widthInt = (int) Math.round(rectangle.getLargeur());
        int heightInt = (int) Math.round(rectangle.getHauteur());

        // Affichage de figure rempler
        g2d.fillRect(xInt, yInt, widthInt, heightInt);
    }

    /**
     * Dessine une ligne remplie sur le Graphics2D.
     *
     * @param g2d  L'objet Graphics2D pour dessiner.
     * @param ligne La ligne à dessiner.
     */
    private void fillLine(Graphics2D g2d, Ligne ligne) {
        int x1 = (int) Math.round(ligne.getXDebut());
        int y1 = (int) Math.round(ligne.getYDebut());
        int x2 = (int) Math.round(ligne.getXFin());
        int y2 = (int) Math.round(ligne.getYFin());

        // Affichage de figure rempler
        g2d.fill(new Line2D.Double(x1, y1, x2, y2));
    }
}
