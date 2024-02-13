package projetgamemvcswing.geometry;

/**
 * Interface définissant les opérations de base d'une figure géométrique.
 */
public interface Figure {
    // Méthodes pour la translation de la figure
    void translater(double dx, double dy);
    
    // Méthode pour inverser la dernière opération (non implémentée ici, à gérer au niveau de l'application)
    void inverser();
    
    boolean intersecteAvec(Figure autre);

}
