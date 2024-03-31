package projetgamemvcswing.modele.factory;
import projetgamemvcswing.modele.geometry.Point;

/**
 *
 * @author 21907062@CAMPUS
 * 
 * Cette classe permet de séparer la création des instances de Point du reste
 * rendant le code plus modulaire 
 * 
 */

public class PointFactory {
    public static Point createPoint(double x, double y) {
        return new Point(x,y);
    }
}
