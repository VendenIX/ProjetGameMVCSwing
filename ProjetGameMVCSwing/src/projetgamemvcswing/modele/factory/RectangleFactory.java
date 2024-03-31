package projetgamemvcswing.modele.factory;
import projetgamemvcswing.modele.geometry.Rectangle;
import java.awt.Color;

/**
 *
 * @author 21907062@CAMPUS
 * Cette classe permet de séparer la création des instances de Rectangle du reste
 * rendant le code plus modulaire 
 */
public class RectangleFactory {
    
    public static Rectangle createRectangle(double x, double y, double largeur, double hauteur, Color couleur) {
        return new Rectangle(x,y,largeur,hauteur,couleur);
    }
}
