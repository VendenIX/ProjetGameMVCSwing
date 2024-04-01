package projetgamemvcswing.controller;

import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe utilisée pour la recherche des pixels intersectants et non-intersectants dans un PanelJeu.
 */
public class Intersection {

    /**
     * Recherche et retourne les pixels intersectants dans un PanelJeu.
     *
     * @param panelJeu Le PanelJeu dans lequel effectuer la recherche.
     * @return Un ensemble de points représentant les pixels intersectants.
     */
    public static Set<Point> findIntersectingPixels(PanelJeu panelJeu) {
        Set<Point> intersectingPixels = new HashSet<>();
        boolean[][] shapeMatrix = markOccupiedPixels(panelJeu);

        // Itérer sur la matrice des formes et trouver les pixels intersectants
        for (int x = 0; x < panelJeu.getWidth(); x++) {
            for (int y = 0; y < panelJeu.getHeight(); y++) {
                if (shapeMatrix[x][y]) {
                    intersectingPixels.add(new Point(x, y));
                }
            }
        }

        return intersectingPixels;
    }

    /**
     * Recherche et retourne les pixels non-intersectants dans un PanelJeu.
     *
     * @param panelJeu Le PanelJeu dans lequel effectuer la recherche.
     * @return Un ensemble de points représentant les pixels non-intersectants.
     */
    public static Set<Point> findNonIntersectingPixels(PanelJeu panelJeu) {
        Set<Point> nonIntersectingPixels = new HashSet<>();
        boolean[][] shapeMatrix = markOccupiedPixels(panelJeu);

        // Itérer sur la matrice des formes et trouver les pixels non-intersectants
        for (int x = 0; x < panelJeu.getWidth(); x++) {
            for (int y = 0; y < panelJeu.getHeight(); y++) {
                // Si le pixel n'est pas occupé par une forme, l'ajouter à l'ensemble
                if (!shapeMatrix[x][y]) {
                    nonIntersectingPixels.add(new Point(x, y));
                }
            }
        }

        return nonIntersectingPixels;
    }

    /**
     * Marque les pixels occupés par les formes dans un PanelJeu.
     *
     * @param panelJeu Le PanelJeu contenant les formes.
     * @return Une matrice booléenne représentant les pixels occupés.
     */
    private static boolean[][] markOccupiedPixels(PanelJeu panelJeu) {
        boolean[][] shapeMatrix = new boolean[panelJeu.getWidth()][panelJeu.getHeight()];
        List<Figure> shapes = panelJeu.getFigures();

        // Marquer les pixels occupés par les formes dans la matrice
        for (Figure shape : shapes) {
            for (int x = 0; x < panelJeu.getWidth(); x++) {
                for (int y = 0; y < panelJeu.getHeight(); y++) {
                    if (shape.contient(x, y)) {
                        shapeMatrix[x][y] = true;
                    }
                }
            }
        }
        return shapeMatrix;
    }
}
