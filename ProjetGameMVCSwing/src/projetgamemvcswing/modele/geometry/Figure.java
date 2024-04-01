package projetgamemvcswing.modele.geometry;

import java.awt.Color;

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
    
    /**
    * Vérifie si la figure contient le point donné (x, y).
    *
    * @param x La coordonnée x du point.
    * @param y La coordonnée y du point.
    * @return Vrai si la figure contient le point, faux sinon.
    */
   boolean contient(double x, double y);
   
   /**
    * Obtient la couleur de la figure.
    *
    * @return La couleur de la figure.
    */
    Color getCouleur();

    /**
     * Définit la couleur de la figure.
     *
     * @param couleur La nouvelle couleur de la figure.
     */
    void setCouleur(Color couleur);

    /**
     * Fait une copie de la figure.
     *
     * @return Une nouvelle instance de Figure identique à celle-ci.
     */
    public abstract Figure copie();

    /**
     * Obtient le nom de la figure.
     *
     * @return Le nom de la figure.
     */
    String getName();

    /**
     * Calcule la surface de la figure.
     *
     * @return La surface de la figure.
     */
    double getSurface();
   
}
