package projetgamemvcswing.modele.factory;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import java.awt.Color;

/**
 *
 * @author 21907062@CAMPUS
 * 
 * Cette classe permet de séparer la création des instances de Cercle du reste
 * rendant le code plus modulaire 
 * 
 */
public class CercleFactory {
    
    public static Cercle createCercle(Point centre, double rayon, Color couleur) {
        return new Cercle(centre, rayon, couleur);
    }
}
