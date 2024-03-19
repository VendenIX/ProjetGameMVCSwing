package projetgamemvcswing.modele.geometry;

/**
 * Interface définissant les opérations de base d'une figure géométrique.
 */
public interface Figure {
    /**
     * Translater la figure par un déplacement dx et dy.
     *
     * @param dx Déplacement horizontal.
     * @param dy Déplacement vertical.
     */
    void translater(double dx, double dy);

    /**
     * Vérifie si la figure intersecte avec une autre figure.
     *
     * @param autre L'autre figure avec laquelle vérifier l'intersection.
     * @return true si les figures s'intersectent, false sinon.
     */
    boolean intersecteAvec(Figure autre);
}
