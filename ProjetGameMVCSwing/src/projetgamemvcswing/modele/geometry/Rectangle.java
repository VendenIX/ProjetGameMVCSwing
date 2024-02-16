package projetgamemvcswing.modele.geometry;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.historique.Memento;
import java.util.*;

/**
 * Classe représentant un rectangle dans un espace bidimensionnel. 
 * Elle fournit des fonctionnalités pour manipuler le rectangle, y compris le déplacer 
 * et vérifier les intersections avec d'autres figures géométriques.
 */
public class Rectangle implements Figure {
    private double x, y; // Coordonnées du coin supérieur gauche
    private double largeur, hauteur; // Dimensions du rectangle
    
    //Historique des états du rectangle pour permettre la sauvegarde et la restauration d'état.
    private final List<Memento> historique = new ArrayList<>();
    
    /**
     * Constructeur pour initialiser un nouveau rectangle avec ses dimensions et position.
     * 
     * @param x La coordonnée x du coin supérieur gauche du rectangle.
     * @param y La coordonnée y du coin supérieur gauche du rectangle.
     * @param largeur La largeur du rectangle.
     * @param hauteur La hauteur du rectangle.
     */
    public Rectangle(double x, double y, double largeur, double hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        sauvegarderEtat(); // Sauvegarde l'état initial
    }
    
    @Override
    public String toString() {
        return "Rectangle{coinSupérieurGauche=(" + x + ", " + y + "), largeur=" + largeur + ", hauteur=" + hauteur + "}";
    }
    
    /**
     * Accède à l'historique des états du rectangle.
     * 
     * @return La liste des mementos représentant l'historique du rectangle.
     */
    public List<Memento> getHistorique() {
        return historique;
    }

    // Getters et setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getLargeur() { return largeur; }
    public void setLargeur(double largeur) { this.largeur = largeur; }
    public double getHauteur() { return hauteur; }
    public void setHauteur(double hauteur) { this.hauteur = hauteur; }
    
    /**
     * Sauvegarde l'état actuel du rectangle dans l'historique.
     */
    public void sauvegarderEtat() {
        historique.add(creerMemento());
    }

    /**
     * Translate le rectangle par les distances spécifiées sur les axes x et y.
     * 
     * @param dx La distance de translation sur l'axe des x.
     * @param dy La distance de translation sur l'axe des y.
     */
    @Override
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
        sauvegarderEtat();
    }
    
    /**
     * Restaure l'état du rectangle à partir d'un memento.
     * 
     * @param memento Le memento à partir duquel restaurer l'état du rectangle.
     */
    @Override
    public void restaurerEtat(Memento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
        this.largeur = memento.getProprietes()[0];
        this.hauteur = memento.getProprietes()[1];
    }
    
    /**
    * Détermine si la figure courante intersecte avec une autre figure donnée.
    * 
    * @param autre La figure avec laquelle vérifier l'intersection. Ce paramètre
    *              peut être une instance de n'importe quelle classe qui implémente
    *              l'interface Figure, comme un Cercle, un Rectangle, etc.
    * @return vrai (true) si les deux figures se croisent ou ont une intersection,
    *         faux (false) dans le cas contraire.
    */
    @Override
    public boolean intersecteAvec(Figure autre) {
        if (autre instanceof Rectangle) {
            Rectangle autreRectangle = (Rectangle) autre;
            // Vérifie si l'un des rectangles est à gauche de l'autre
            boolean aGauche = this.x + this.largeur < autreRectangle.x;
            // Vérifie si l'un des rectangles est à droite de l'autre
            boolean aDroite = this.x > autreRectangle.x + autreRectangle.largeur;
            // Vérifie si l'un des rectangles est au-dessus de l'autre
            boolean auDessus = this.y + this.hauteur < autreRectangle.y;
            // Vérifie si l'un des rectangles est en dessous de l'autre
            boolean enDessous = this.y > autreRectangle.y + autreRectangle.hauteur;

            // S'il n'y a aucune de ces conditions vraies, alors il y a intersection
            return !(aGauche || aDroite || auDessus || enDessous);
            
        } else if (autre instanceof Cercle) {
            Cercle cercle = (Cercle) autre;
            // Trouver le point le plus proche du cercle sur le rectangle
            double pointProcheX = Math.max(this.x, Math.min(cercle.getX(), this.x + this.largeur));
            double pointProcheY = Math.max(this.y, Math.min(cercle.getY(), this.y + this.hauteur));

            // Calculer la distance du point le plus proche au centre du cercle
            double distanceX = cercle.getX() - pointProcheX;
            double distanceY = cercle.getY() - pointProcheY;

            // Calculer la distance au carré pour éviter la racine carrée
            double distanceAuCarre = (distanceX * distanceX) + (distanceY * distanceY);

            // Vérifier si la distance est inférieure ou égale au carré du rayon du cercle
            return distanceAuCarre <= (cercle.getRayon() * cercle.getRayon());
        }
        return false;
    }
    
    /**
     * Crée un memento contenant l'état actuel du rectangle.
     * 
     * @return Le memento de l'état actuel.
     */
    @Override
    public Memento creerMemento() {
        return new Memento(x, y, largeur, hauteur);
    }

}