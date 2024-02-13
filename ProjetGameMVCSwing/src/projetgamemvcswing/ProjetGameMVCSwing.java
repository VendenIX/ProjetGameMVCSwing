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
        System.out.println("Hello world !");
        Figure f1 = new Cercle(1,2,3);
        System.out.println(f1);
        Figure f2 = new Rectangle(3,4,2,1);
        System.out.println(f2);
    }
    
}
