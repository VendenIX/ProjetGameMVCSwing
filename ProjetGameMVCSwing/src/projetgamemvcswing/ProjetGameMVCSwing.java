package projetgamemvcswing;
import historique.Memento;
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
        
        // TEST DU PACKAGE GEOMETRY DES COLLISIONS :
        
        // Test 1: Intersection entre deux cercles
        Cercle cercle1 = new Cercle(0, 0, 5);
        Cercle cercle2 = new Cercle(3, 4, 5);
        System.out.println("Intersection entre deux cercles (attendu: true) : " + cercle1.intersecteAvec(cercle2));

        // Test 2: Pas d'intersection entre deux cercles
        Cercle cercle3 = new Cercle(10, 10, 2);
        System.out.println("Pas d'intersection entre deux cercles (attendu: false) : " + cercle1.intersecteAvec(cercle3));

        // Test 3: Intersection entre deux rectangles
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(5, 5, 10, 10);
        System.out.println("Intersection entre deux rectangles (attendu: true) : " + rect1.intersecteAvec(rect2));

        // Test 4: Pas d'intersection entre deux rectangles
        Rectangle rect3 = new Rectangle(20, 20, 10, 10);
        System.out.println("Pas d'intersection entre deux rectangles (attendu: false) : " + rect1.intersecteAvec(rect3));

        // Test 5: Intersection entre un cercle et un rectangle
        Cercle cercle4 = new Cercle(15, 15, 5);
        System.out.println("Intersection entre un cercle et un rectangle (attendu: true) : " + cercle4.intersecteAvec(rect2));

        // Test 6: Pas d'intersection entre un cercle et un rectangle
        Cercle cercle5 = new Cercle(1, 1, 1);
        Rectangle rect4 = new Rectangle(5, 5, 2, 2);
        System.out.println("Pas d'intersection entre un cercle et un rectangle (attendu: false) : " + cercle5.intersecteAvec(rect4));
        System.out.println("\n");
        System.out.println("\n");
        
        cercle = new Cercle(0, 0, 5);
        System.out.println("État initial du cercle: " + cercle);

        // Translater le cercle deux fois
        cercle.translater(10, 0);
        System.out.println("Après la première translation: " + cercle);
        cercle.translater(10, 0);
        System.out.println("Après la deuxième translation: " + cercle);

        // Restaurer l'état précédent (avant la dernière translation)
        Memento etatPrecedent = cercle.getHistorique().get(cercle.getHistorique().size() - 2);
        cercle.restaurerEtat(etatPrecedent);
        System.out.println("Après restauration à l'état précédent: " + cercle);
    }
    
}
