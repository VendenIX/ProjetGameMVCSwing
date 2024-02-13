package projetgamemvcswing;
import projetgamemvcswing.geometry.*;
/**
 *
 * @author romain
 */
public class ProjetGameMVCSwing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Yououh ça compile !"); //sinon allez modifier le jdk via files dans nbproject > project.properties jdksource 17 => 11 par exemple
        Figure f1 = new Cercle(1,2,3);
        System.out.println(f1);
        Figure f2 = new Rectangle(3,4,2,1);
        System.out.println(f2);
        
        // Création d'un cercle et d'un rectangle
        Cercle cercle = new Cercle(0, 0, 5); // Centre (0,0) et rayon 5
        Rectangle rectangle = new Rectangle(10, 10, 20, 15); // Coin supérieur gauche (10,10), largeur 20, hauteur 15

        // Affichage des états initiaux
        System.out.println("État initial du cercle: centre (" + cercle.getX() + "," + cercle.getY() + ") et rayon " + cercle.getRayon());
        System.out.println("État initial du rectangle: coin supérieur gauche (" + rectangle.getX() + "," + rectangle.getY() + "), largeur " + rectangle.getLargeur() + ", hauteur " + rectangle.getHauteur());

        // Translation du cercle et du rectangle
        cercle.translater(5, 5);
        rectangle.translater(-5, -5);

        // Affichage après translation
        System.out.println("État du cercle après translation: centre (" + cercle.getX() + "," + cercle.getY() + ") et rayon " + cercle.getRayon());
        System.out.println("État du rectangle après translation: coin supérieur gauche (" + rectangle.getX() + "," + rectangle.getY() + "), largeur " + rectangle.getLargeur() + ", hauteur " + rectangle.getHauteur());

    }
    
}
