package projetgamemvcswing.modele.geometry;

import java.awt.Color;
import java.awt.Graphics2D;

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
   
   //utilisation du pattern Prototype !
   /**
    * Fait tout simplement une copie.
    * @return 
    */
   public abstract Figure copie();
   
   String getName();
   
   double getSurface();
   
   void dessiner(Graphics2D g2d);
   
   void dessinerBordure(Graphics2D g2d);
   
   boolean needsBorder();
   
   void setNeedBorder(Boolean bool);
   
}
