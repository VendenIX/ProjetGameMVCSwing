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
   
   Color getCouleur(); // Obtenir la couleur de la figure
   
   void setCouleur(Color couleur); // Définir la couleur de la figure
   
   String getName();
   
   double getSurface();
   
}
