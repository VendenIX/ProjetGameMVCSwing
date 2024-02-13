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

}