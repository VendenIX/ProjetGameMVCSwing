package projetgamemvcswing.geometry;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;

public class CercleTest {
    
    private Cercle cercle;
    private Point centre;
    
    @Before
    public void setUp() {
        centre = new Point(0,0);
        cercle = new Cercle(centre, 5, Color.WHITE);
    }

    @Test
    public void testToString() {
        String expected = "Cercle{centre=(0.0, 0.0), rayon=5.0}";
        assertEquals("La représentation en chaîne de caractères du cercle est incorrecte", expected, cercle.toString());
    }

    @Test
    public void testGetXGetY() {
        assertEquals("La valeur de x est incorrecte", 0.0, cercle.getX(), 0.0);
        assertEquals("La valeur de y est incorrecte", 0.0, cercle.getY(), 0.0);
    }

    @Test
    public void testSetXSetY() {
        cercle.setX(10);
        cercle.setY(15);
        assertEquals("La méthode setX ne fonctionne pas comme prévu", 10.0, cercle.getX(), 0.0);
        assertEquals("La méthode setY ne fonctionne pas comme prévu", 15.0, cercle.getY(), 0.0);
    }

    @Test
    public void testGetSetRayon() {
        cercle.setRayon(10);
        assertEquals("La méthode getRayon ou setRayon ne fonctionne pas comme prévu", 10.0, cercle.getRayon(), 0.0);
    }

    @Test
    public void testTranslater() {
        cercle.translater(10, 5);
        assertEquals("La méthode translater ne met pas à jour x correctement", 10.0, cercle.getX(), 0.0);
        assertEquals("La méthode translater ne met pas à jour y correctement", 5.0, cercle.getY(), 0.0);
    }

    @Test
    public void testIntersecteAvec() {
        Point autreCentre = new Point(5,0);
        Cercle autreCercle = new Cercle(autreCentre, 5,Color.WHITE);
        assertTrue("Les cercles devraient s'intersecter", cercle.intersecteAvec(autreCercle));
        
        Point centreEloigne = new Point(20,20);
        Cercle cercleEloigne = new Cercle(centreEloigne, 2, Color.WHITE);
        assertFalse("Les cercles ne devraient pas s'intersecter", cercle.intersecteAvec(cercleEloigne));
    }

}
