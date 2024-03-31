
package projetgamemvcswing.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class Intersection {
        
    public static Set<Point> findIntersectingPixels(PanelJeu panelJeu) {
        Set<Point> intersectingPixels = new HashSet<>();
        boolean[][] shapeMatrix = new boolean[panelJeu.getWidth()][panelJeu.getHeight()];

        List<Figure> shapes = panelJeu.getFigures();
        
        // Iterate over each shape and mark the pixels it occupies in the shapeMatrix
        for (Figure shape : shapes) {
            for (int x = 0; x < panelJeu.getWidth(); x++) {
                for (int y = 0; y < panelJeu.getHeight(); y++) {
                    
                    if (shape.contient(x,y)) {
                        shapeMatrix[x][y] = true;
                    }
                }
            }
        }

        // Iterate over the shapeMatrix and find intersecting pixels
        for (int x = 0; x < panelJeu.getWidth(); x++) {
            for (int y = 0; y < panelJeu.getHeight(); y++) {
                if (shapeMatrix[x][y]) {
                    intersectingPixels.add(new Point(x, y));
                }
            }
        }

        return intersectingPixels;
    }
   
    public static Set<Point> findNonIntersectingPixels(PanelJeu panelJeu) {
        Set<Point> nonIntersectingPixels = new HashSet<>();
        boolean[][] shapeMatrix = new boolean[panelJeu.getWidth()][panelJeu.getHeight()];

        List<Figure> shapes = panelJeu.getFigures();

        // Mark the pixels occupied by shapes in the shapeMatrix
        for (Figure shape : shapes) {
            for (int x = 0; x < panelJeu.getWidth(); x++) {
                for (int y = 0; y < panelJeu.getHeight(); y++) {
                    if (shape.contient(x, y)) {
                        shapeMatrix[x][y] = true;
                    }
                }
            }
        }

        // Iterate over the shapeMatrix and find non-intersecting pixels
        for (int x = 0; x < panelJeu.getWidth(); x++) {
            for (int y = 0; y < panelJeu.getHeight(); y++) {
                // If the pixel is not occupied by any shape, add it to the set
                if (!shapeMatrix[x][y]) {
                    nonIntersectingPixels.add(new Point(x, y));
                }
            }
        }

        return nonIntersectingPixels;
    }
}
