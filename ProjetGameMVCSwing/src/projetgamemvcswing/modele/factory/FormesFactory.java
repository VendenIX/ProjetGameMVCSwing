package projetgamemvcswing.modele.factory;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import java.awt.Color;

/**
 *
 * @author 21907062@CAMPUS
 * 
 * Cette classe permet de séparer la création des instances de formes géométriques du reste,
 * rendant le code plus modulaire.
 * 
 */

public class FormesFactory {
    
    // Méthode pour créer un rectangle
    public static Rectangle createRectangle(double x, double y, double largeur, double hauteur, Color couleur) {
        return new Rectangle(x, y, largeur, hauteur, couleur);
    }
    
    // Méthode pour créer un cercle
    public static Cercle createCercle(Point centre, double rayon, Color couleur) {
        return new Cercle(centre, rayon, couleur);
    }
    
    // Méthode pour créer un point
    public static Point createPoint(double x, double y) {
        return new Point(x, y);
    }
}
