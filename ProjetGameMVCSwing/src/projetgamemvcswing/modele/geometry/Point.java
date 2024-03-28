/*
 * Cette classe représente un point dans un espace bidimensionnel, défini par ses coordonnées (x, y).
 */
package projetgamemvcswing.modele.geometry;

/**
 *
 * @author 21907062
 */

/**
 * La classe Point représente un point dans un espace bidimensionnel, défini par ses coordonnées (x, y).
 * Elle offre des méthodes pour accéder et modifier les coordonnées du point.
 */
public class Point {
    
    private double x; // Coordonnée x du point
    private double y; // Coordonnée y du point
    
    /**
     * Constructeur pour initialiser un point avec les coordonnées spécifiées.
     * @param x Coordonnée x du point
     * @param y Coordonnée y du point
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Retourne la coordonnée x du point.
     * @return La coordonnée x du point
     */
    public double getX(){
        return this.x;
    }
    
    /**
     * Retourne la coordonnée y du point.
     * @return La coordonnée y du point
     */
    public double getY(){
        return this.y;
    }
    
    /**
     * Modifie la coordonnée x du point.
     * @param x La nouvelle coordonnée x du point
     */
    public void setX(double x){
        this.x = x;
    }
    
    /**
     * Modifie la coordonnée y du point.
     * @param y La nouvelle coordonnée y du point
     */
    public void setY(double y){
        this.y = y;
    }
    
    /**
     * Retourne une représentation sous forme de chaîne de caractères du point
     * au format "(x, y)".
     * @return Une chaîne de caractères représentant le point
     */
    
    @Override
    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
    
    
}
