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

    /**
     * Parcours la Array liste des figures enregistrer
     *  et Dessine/Affiche les figures géométriques remplies avec un couleur choisi par l'utilisateur.
     *
     * @param g2d     L'objet Graphics2D pour dessiner.
     * @param figures La liste des figures à dessiner.
     */
    private void drawAndFillFigure(Graphics2D g2d, Figure forme) {
        if (forme == null) return; // Vérifier si la forme est non nulle

        // Vérifier la couleur de la forme et ajuster si nécessaire
        if (forme.getCouleur().equals(Color.WHITE)) {
            g2d.setColor(Color.BLACK); // Définir la couleur de la bordure pour les formes blanches
        } else {
            g2d.setColor(forme.getCouleur()); // Utiliser la couleur de la forme
        }

        // Dessiner la forme selon son type
        forme.dessiner(g2d);

        // S'il est nécessaire de dessiner la bordure spécifiquement (pour les formes non remplissables comme les lignes), le faire ici
        if (forme.needsBorder()) {
            g2d.setColor(Color.BLACK); // Pour la bordure
            forme.dessinerBordure(g2d);
        }
    }
    
    public void drawFilledFigure(Graphics2D g2d, Figure figure) {
        // Définir la couleur de remplissage de la figure
        g2d.setColor(figure.getCouleur());
        
        // Dessiner la figure remplie
        figure.dessiner(g2d);
        
        // Vérifier si une bordure doit être dessinée
        if (figure.needsBorder()) {
            g2d.setColor(Color.BLACK); // Définir la couleur de la bordure
            figure.dessinerBordure(g2d); // Dessiner la bordure
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
            drawFilledFigure(g2d, figure); // Utilise la méthode existante pour dessiner chaque figure
        }
    }


}
