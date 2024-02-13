package projetgamemvcswing.geometry;

/**
 * Représente un cercle dans un espace bidimensionnel.
 */
public class Cercle implements Figure {
    private double x, y; // Coordonnées du centre du cercle
    private double rayon; // Rayon du cercle

    public Cercle(double x, double y, double rayon) {
        this.x = x;
        this.y = y;
        this.rayon = rayon;
    }
    
    @Override
    public String toString() {
        return "Cercle{centre=(" + x + ", " + y + "), rayon=" + rayon + "}";
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
    }

    @Override
    public void inverser() {
        // Implémentation dépendante de la gestion des états antérieurs ?
    }
}