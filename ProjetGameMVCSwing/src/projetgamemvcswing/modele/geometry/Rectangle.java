package projetgamemvcswing.modele.geometry;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import projetgamemvcswing.adapter.DisplayableProperties;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;



/**
 * Classe représentant un rectangle dans un espace bidimensionnel. 
 * Elle fournit des fonctionnalités pour manipuler le rectangle, y compris le déplacer 
 * et vérifier les intersections avec d'autres figures géométriques.
 */
public class Rectangle extends AbstractModeleEcoutable implements Figure, DisplayableProperties {
    
    private double x, y; // Coordonnées du coin supérieur gauche
    private double largeur, hauteur; // Dimensions du rectangle
    private Color couleur;
    private Boolean needsBorder;
    
    /**
     * Constructeur pour initialiser un nouveau rectangle avec ses dimensions et position.
     * 
     * @param x La coordonnée x du coin supérieur gauche du rectangle.
     * @param y La coordonnée y du coin supérieur gauche du rectangle.
     * @param largeur La largeur du rectangle.
     * @param hauteur La hauteur du rectangle.
     * @param couleur du rectangle.
     */
    public Rectangle(double x, double y, double largeur, double hauteur, Color couleur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.couleur = couleur;
        this.needsBorder = true;
    }
    
    @Override
    public String toString() {
        return "Rectangle{coinSupérieurGauche=(" + x + ", " + y + "), largeur=" + largeur + ", hauteur=" + hauteur + "}";
    }
    
    // Getters et setters
    public double getX() { return x; }
    
    public void setX(double x) { 
        this.x = x; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public double getY() { return y; }
    
    public void setY(double y) { 
        this.y = y; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public double getLargeur() { return largeur; }
    
    public void setLargeur(double largeur) { 
        this.largeur = largeur; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public double getHauteur() { return hauteur; }
    
    public void setHauteur(double hauteur) { 
        this.hauteur = hauteur; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    public Color getCouleur() { return couleur; }
    
    public void setCouleur(Color couleur) { 
        this.couleur = couleur; 
        fireChange(); // Notifier les écouteurs du changement
    }
    
    /**
     * Translate le rectangle par les distances spécifiées sur les axes x et y.
     * 
     * @param dx La distance de translation sur l'axe des x.
     * @param dy La distance de translation sur l'axe des y.
     */
    @Override
    public void translater(double dx, double dy) {
        setX(this.x + dx);
        setY(this.y + dy);
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
    * Vérifie si le rectangle contient le point spécifié par les coordonnées (x, y).
    *
    * @param x La coordonnée x du point.
    * @param y La coordonnée y du point.
    * @return True si le rectangle contient le point, false sinon.
    */
    @Override
    public boolean contient(double x, double y) {
        // Vérifier si le point (x, y) est à l'intérieur des limites du rectangle
        return x >= this.x && x <= this.x + this.largeur &&
               y >= this.y && y <= this.y + this.hauteur;
    }

    public void deplacer(double xFinal, double yFinal) {
        this.x = xFinal;
        this.y = yFinal;
        fireChange();
    }

    @Override
    public Figure copie() {
        return new Rectangle(this.getX(), this.getY(), this.getLargeur(), this.getHauteur(), this.getCouleur());
    }

    
    @Override
    public String getName(){
        return "Rectangle";
    }
    
    @Override
    public double getSurface(){
        return this.hauteur*this.largeur;
    }
    
    @Override
    public void dessiner(Graphics2D g2d) {
        g2d.fillRect((int) x, (int) y, (int) largeur, (int) hauteur);
    }

    @Override
    public void dessinerBordure(Graphics2D g2d) {
        g2d.drawRect((int) x, (int) y, (int) largeur, (int) hauteur);
    }

    @Override
    public boolean needsBorder() {
        return this.needsBorder; //par defaut on part du principe que oui vu que la color default c'est blanc
    }
    
    @Override
    public void setNeedBorder(Boolean bool) {
        this.needsBorder = bool;
    }

    @Override
    public List<Object> getProperties() {
        return Arrays.asList(getHauteur(), getLargeur(), getSurface());
    }
    
}