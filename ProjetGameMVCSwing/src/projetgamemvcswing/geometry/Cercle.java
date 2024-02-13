package projetgamemvcswing.geometry;

import historique.Memento;
import java.util.*;

/**
 * Représente un cercle dans un espace bidimensionnel.
 */
public class Cercle implements Figure {
    private double x, y; // Coordonnées du centre du cercle
    private double rayon; // Rayon du cercle
    private final List<Memento> historique = new ArrayList<>(); //l'historique est final car on ne va pas modifier une valeur dans l'historique

    public Cercle(double x, double y, double rayon) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        sauvegarderEtat(); // Sauvegarde l'état initial
    }
    
    @Override
    public String toString() {
        return "Cercle{centre=(" + x + ", " + y + "), rayon=" + rayon + "}";
    }
    
    public List<Memento> getHistorique() {
        return historique;
    }

    // Getters et setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getRayon() { return rayon; }
    public void setRayon(double rayon) { this.rayon = rayon; }

    @Override
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
        sauvegarderEtat();
    }
    
    public void sauvegarderEtat() {
        this.historique.add(creerMemento());
    }


    @Override
    public boolean intersecteAvec(Figure autre) {
        if (autre instanceof Cercle) {
            Cercle autreCercle = (Cercle) autre;
            double distanceCentres = Math.sqrt(Math.pow(this.x - autreCercle.x, 2) + Math.pow(this.y - autreCercle.y, 2));
            return distanceCentres <= (this.rayon + autreCercle.rayon);
        } else if (autre instanceof Rectangle) {
            Rectangle rect = (Rectangle) autre;
            // Trouver le point le plus proche du cercle sur le rectangle
            double pointProcheX = Math.max(rect.getX(), Math.min(this.x, rect.getX() + rect.getLargeur()));
            double pointProcheY = Math.max(rect.getY(), Math.min(this.y, rect.getY() + rect.getHauteur()));

            // Calculer la distance du point le plus proche au centre du cercle
            double distanceX = this.x - pointProcheX;
            double distanceY = this.y - pointProcheY;

            // Calculer la distance au carré pour éviter la racine carrée (plus efficace)
            double distanceAuCarre = (distanceX * distanceX) + (distanceY * distanceY);

            // Vérifier si la distance est inférieure ou égale au carré du rayon du cercle
            return distanceAuCarre <= (this.rayon * this.rayon);
        }
        return false;
    }

    @Override
    public Memento creerMemento() {
        return new Memento(x, y, rayon);
    }

    @Override
    public void restaurerEtat(Memento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
        this.rayon = memento.getProprietes()[0];
    }


}