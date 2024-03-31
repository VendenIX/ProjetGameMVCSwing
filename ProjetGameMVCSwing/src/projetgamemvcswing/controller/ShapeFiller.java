package projetgamemvcswing.controller;

// Importation des bibliotheque
import java.awt.Color;
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

    public void drawFilledFigure(Graphics2D g2d, Figure figure) {
        // Définir la couleur de remplissage de la figure
        g2d.setColor(figure.getCouleur());
        
        // Dessiner la figure remplie
        figure.dessiner(g2d);

        // verif si une bordure doit être dessinée
        if (figure.needsBorder()) {
            g2d.setColor(Color.BLACK); // def la couleur de la bordure
            figure.dessinerBordure(g2d); // sessiner la bordure
        }
    }
    
    /**
     * Dessine et remplit une liste de figures avec leurs couleurs respectives,
     * et dessine une bordure autour si nécessaire.
     *
     * @param g2d     L'objet Graphics2D pour le dessin.
     * @param figures La liste des figures à dessiner et à remplir.
     */
    public void drawFilledFigures(Graphics2D g2d, List<Figure> figures) {
        for (Figure figure : figures) {
            g2d.setColor(figure.getCouleur());
            drawFilledFigure(g2d, figure); // Utilise la méthode existante pour dessiner chaque figure
        }
    }


}
