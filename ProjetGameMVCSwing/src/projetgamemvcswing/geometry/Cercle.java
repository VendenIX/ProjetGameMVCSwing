package projetgamemvcswing.geometry;

import historique.Memento;
import java.util.*;

/**
 * Représente un cercle dans un espace bidimensionnel. Cette classe permet de créer un cercle,
 * de le manipuler et d'interagir avec d'autres figures géométriques. Elle implémente l'interface Figure.
 */
public class Cercle implements Figure {
    private double x, y; // Coordonnées du centre du cercle
    private double rayon; // Rayon du cercle
    
    //Historique des états du cercle pour permettre la sauvegarde et la restauration d'état.
    private final List<Memento> historique = new ArrayList<>(); //l'historique est final car on ne va pas modifier une valeur dans l'historique

    
    /**
     * Constructeur pour créer un cercle avec des coordonnées spécifiques et un rayon.
     * 
     * @param x Coordonnée x du centre du cercle.
     * @param y Coordonnée y du centre du cercle.
     * @param rayon Rayon du cercle.
     */
    public Cercle(double x, double y, double rayon) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
        sauvegarderEtat(); // Sauvegarde l'état initial
    }
    
    /**
     * Retourne une représentation textuelle du cercle.
     * 
     * @return Une chaîne de caractères décrivant le cercle.
     */
    @Override
    public String toString() {
        return "Cercle{centre=(" + x + ", " + y + "), rayon=" + rayon + "}";
    }
    
    /**
     * Accède à l'historique des états du cercle.
     * 
     * @return La liste des mementos représentant l'historique du cercle.
     */
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
    
    /**
     * Translade le cercle par les distances spécifiées.
     * 
     * @param dx Distance de translation sur l'axe des x.
     * @param dy Distance de translation sur l'axe des y.
     */
    @Override
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
        sauvegarderEtat();
    }
    
    /**
     * Sauvegarde l'état actuel du cercle dans l'historique.
     */
    public void sauvegarderEtat() {
        this.historique.add(creerMemento());
    }

    /**
     * Détermine si le cercle intersecte avec une autre figure.
     * 
     * @param autre L'autre figure avec laquelle vérifier l'intersection.
     * @return true si le cercle et l'autre figure s'intersectent, false sinon.
     */
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
    
    /**
     * Crée un memento contenant l'état actuel du cercle.
     * 
     * @return Le memento de l'état actuel.
     */
    @Override
    public Memento creerMemento() {
        return new Memento(x, y, rayon);
    }
    
    /**
     * Restaure l'état du cercle à partir d'un memento.
     * 
     * @param memento Le memento à partir duquel restaurer l'état du cercle.
     */
    @Override
    public void restaurerEtat(Memento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
        this.rayon = memento.getProprietes()[0];
    }


}