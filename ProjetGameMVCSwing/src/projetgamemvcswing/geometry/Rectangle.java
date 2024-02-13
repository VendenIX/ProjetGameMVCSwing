package projetgamemvcswing.geometry;

/**
 * Représente un rectangle dans un espace bidimensionnel.
 */
public class Rectangle implements Figure {
    private double x, y; // Coordonnées du coin supérieur gauche
    private double largeur, hauteur; // Dimensions du rectangle

    public Rectangle(double x, double y, double largeur, double hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }
    
    @Override
    public String toString() {
        return "Rectangle{coinSupérieurGauche=(" + x + ", " + y + "), largeur=" + largeur + ", hauteur=" + hauteur + "}";
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

    @Override
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void inverser() {
        // Implémentation dépendante de la gestion des états antérieurs
    }
}