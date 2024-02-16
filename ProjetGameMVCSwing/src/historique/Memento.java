package historique;

/**
 * Classe Memento utilisée pour sauvegarder l'état d'une figure géométrique.
 * Elle stocke les coordonnées (x, y) ainsi que d'autres propriétés spécifiques
 * à la figure, telles que le rayon pour un cercle ou la largeur et la hauteur pour un rectangle.
 */
public class Memento {
    private double x, y; // Coordonnées du point ou de la figure
    private double[] proprietes; // Autres propriétés spécifiques à la figure

    /**
     * Constructeur de la classe Memento.
     * 
     * @param x La coordonnée x de la figure.
     * @param y La coordonnée y de la figure.
     * @param proprietes Les autres propriétés spécifiques à la figure, comme le rayon, la largeur, la hauteur, etc.
     */
    public Memento(double x, double y, double... proprietes) {
        this.x = x;
        this.y = y;
        this.proprietes = proprietes;
    }

    /**
     * Récupère la coordonnée x de la figure.
     * 
     * @return La coordonnée x de la figure.
     */
    public double getX() { return x; }
    
    /**
     * Récupère la coordonnée y de la figure.
     * 
     * @return La coordonnée y de la figure.
     */
    public double getY() { return y; }
    
    /**
     * Récupère une copie des propriétés spécifiques à la figure.
     * Cette méthode assure l'immutabilité des données en retournant une copie du tableau des propriétés.
     * 
     * @return Une copie du tableau des propriétés spécifiques à la figure.
     */
    public double[] getProprietes() { 
        // Retourner une copie du tableau pour préserver l'immutabilité
        return proprietes.clone();
    }
}