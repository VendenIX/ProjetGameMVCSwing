package projetgamemvcswing.modele.geometry;

import projetgamemvcswing.modele.historique.Memento;
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
     * Crée un memento contenant l'état actuel de la figure.
     *
     * @return Le memento de l'état actuel.
     */
    Memento creerMemento();

    /**
     * Restaure l'état de la figure à partir d'un memento donné.
     *
     * @param memento Le memento à partir duquel restaurer l'état de la figure.
     */
    void restaurerEtat(Memento memento);

    /**
     * Vérifie si la figure intersecte avec une autre figure.
     *
     * @param autre L'autre figure avec laquelle vérifier l'intersection.
     * @return true si les figures s'intersectent, false sinon.
     */
    boolean intersecteAvec(Figure autre);
    
    /**
    * Vérifie si la figure contient le point donné (x, y).
    *
    * @param x La coordonnée x du point.
    * @param y La coordonnée y du point.
    * @return Vrai si la figure contient le point, faux sinon.
    */
   boolean contient(double x, double y);
}
