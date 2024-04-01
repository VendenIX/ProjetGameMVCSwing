package projetgamemvcswing.modele.geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import projetgamemvcswing.adapter.DisplayableProperties;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

/**
 * Représente un cercle dans un espace bidimensionnel. Cette classe permet de créer un cercle,
 * de le manipuler et d'interagir avec d'autres figures géométriques. Elle implémente l'interface Figure.
 */
public class Cercle extends AbstractModeleEcoutable implements Figure, DisplayableProperties {
    
    private Point centre; // Centre du cercle
    private double rayon; // Rayon du cercle
    private Color couleur; // Couleur du cercle
    private Boolean needsBorder;
    
    /**
     * Constructeur pour créer un cercle avec des coordonnées spécifiques et un rayon.
     * 
     * @param centre du cercle (x, y)
     * @param couleur
     * @param rayon Rayon du cercle.
     */
    public Cercle(Point centre, double rayon, Color couleur) {
        this.centre = centre;
        this.rayon = rayon;
        this.couleur = couleur;
        this.needsBorder = true;
    }
    
    /**
     * Retourne une représentation textuelle du cercle.
     * 
     * @return Une chaîne de caractères décrivant le cercle.
     */
    @Override
    public String toString() {
        return "Cercle{centre=" +this.centre.toString()+", rayon=" + rayon + "}";
    }
    
    

    // Getters et setters
    
    public Point getCentre() { return this.centre; }
    
    public double getX() { return this.centre.getX(); }
    
    public void setX(double x) { 
        this.centre.setX(x); 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public double getY() { return this.centre.getY(); }
    
    public void setY(double y) { 
        this.centre.setY(y); 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public double getRayon() { return rayon; }
    
    public void setRayon(double rayon) { 
        this.rayon = rayon; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    @Override
    public Color getCouleur() { return couleur; }
    
    @Override
    public void setCouleur(Color couleur) { 
        this.couleur = couleur; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    /**
     * Translade le cercle par les distances spécifiées.
     * 
     * @param dx Distance de translation sur l'axe des x.
     * @param dy Distance de translation sur l'axe des y.
     */
    @Override
    public void translater(double dx, double dy) {
        this.setX(this.centre.getX()+dx);
        this.setY(this.centre.getY()+dy);
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
            double distanceCentres = Math.sqrt(Math.pow(this.getX() - autreCercle.getX(), 2) + Math.pow(this.getY() - autreCercle.getY(), 2));
            return distanceCentres <= (this.rayon + autreCercle.rayon);
        } else if (autre instanceof Rectangle) {
            Rectangle rect = (Rectangle) autre;
            // Trouver le point le plus proche du cercle sur le rectangle
            double pointProcheX = Math.max(rect.getX(), Math.min(this.getX(), rect.getX() + rect.getLargeur()));
            double pointProcheY = Math.max(rect.getY(), Math.min(this.getY(), rect.getY() + rect.getHauteur()));

            // Calculer la distance du point le plus proche au centre du cercle
            double distanceX = this.getX() - pointProcheX;
            double distanceY = this.getY() - pointProcheY;

            // Calculer la distance au carré pour éviter la racine carrée (plus efficace)
            double distanceAuCarre = (distanceX * distanceX) + (distanceY * distanceY);

            // Vérifier si la distance est inférieure ou égale au carré du rayon du cercle
            return distanceAuCarre <= (this.rayon * this.rayon);
        }
        return false;
    }
    
    /**
     * Calcule la distance entre les coordonnées actuelles du centre du cercle
     * et les coordonnées spécifiées (draggedX, draggedY).
     * 
     * @param draggedX Coordonnée x du point spécifié.
     * @param draggedY Coordonnée y du point spécifié.
     * @return La distance euclidienne entre le centre du cercle et les coordonnées spécifiées.
     */
    public double distance(double draggedX, double draggedY ){
        return Math.sqrt(Math.pow(draggedX - this.getX(), 2) + Math.pow(draggedY - this.getY(), 2));
    }
    
    /**
    * Vérifie si le cercle contient le point spécifié par les coordonnées (x, y).
    *
    * @param x La coordonnée x du point.
    * @param y La coordonnée y du point.
    * @return True si le cercle contient le point, false sinon.
    */
    @Override
    public boolean contient(double x, double y) {
        // Calculer la distance entre le point (x, y) et le centre du cercle
        double distance = Math.sqrt(Math.pow(x - this.centre.getX(), 2) + Math.pow(y - this.centre.getY(), 2));
        
        // Vérifier si la distance est inférieure ou égale au rayon du cercle
        return distance <= this.rayon;
    }

    public void deplacer(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    
    @Override
    public Figure copie() {
        return new Cercle(this.getCentre(), this.getRayon(), this.getCouleur());
    }
    
    public String getName(){
        return "Cercle";
    }
    
    @Override
    public double getSurface(){
        return Math.PI * Math.pow(this.rayon, 2);
    }
    
    @Override
    public void dessiner(Graphics2D g2d) {
        g2d.fillOval((int) (centre.getX() - rayon), (int) (centre.getY() - rayon), (int) (2 * rayon), (int) (2 * rayon));
    }

    @Override
    public void dessinerBordure(Graphics2D g2d) {
        g2d.drawOval((int) (centre.getX() - rayon), (int) (centre.getY() - rayon), (int) (2 * rayon), (int) (2 * rayon));
    }

    @Override
    public boolean needsBorder() {
        return this.needsBorder; // Les cercles ont une bordure dessinée explicitement.
    }

    @Override
    public void setNeedBorder(Boolean bool) {
        this.needsBorder = bool;
    }

    @Override
    public List<Object> getProperties() {
        return Arrays.asList(getCentre(), getRayon(), getSurface());
    }
}