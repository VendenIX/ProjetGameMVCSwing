package projetgamemvcswing.geometry;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.historique.Memento;

public class RectangleTest {
    
    private Rectangle rectangle;
    
    @Before
    public void setUp() {
        rectangle = new Rectangle(0, 0, 10, 20);
    }

    @Test
    public void testToString() {
        String expected = "Rectangle{coinSupérieurGauche=(0.0, 0.0), largeur=10.0, hauteur=20.0}";
        assertEquals(expected, rectangle.toString());
    }

    @Test
    public void testGetHistorique() {
        assertEquals(1, rectangle.getHistorique().size());
    }

    @Test
    public void testGetSetX() {
        rectangle.setX(5);
        assertEquals(5.0, rectangle.getX(), 0.0);
    }

    @Test
    public void testGetSetY() {
        rectangle.setY(5);
        assertEquals(5.0, rectangle.getY(), 0.0);
    }

    @Test
    public void testGetSetLargeur() {
        rectangle.setLargeur(15);
        assertEquals(15.0, rectangle.getLargeur(), 0.0);
    }

    @Test
    public void testGetSetHauteur() {
        rectangle.setHauteur(25);
        assertEquals(25.0, rectangle.getHauteur(), 0.0);
    }

    @Test
    public void testTranslater() {
        rectangle.translater(10, 5);
        assertEquals(10.0, rectangle.getX(), 0.0);
        assertEquals(5.0, rectangle.getY(), 0.0);
    }

    @Test
    public void testSauvegarderEtat() {
        rectangle.translater(10, 5);
        assertEquals(2, rectangle.getHistorique().size());
    }

    @Test
    public void testRestaurerEtat() {
        rectangle.translater(10, 5);
        Memento etatInitial = rectangle.getHistorique().get(0);
        rectangle.restaurerEtat(etatInitial);
        assertEquals(0.0, rectangle.getX(), 0.0);
        assertEquals(0.0, rectangle.getY(), 0.0);
    }

    @Test
    public void testIntersecteAvecRectangle() {
        Rectangle autreRectangle = new Rectangle(5, 5, 5, 5);
        assertTrue(rectangle.intersecteAvec(autreRectangle));
    }

    @Test
    public void testIntersecteAvecCercle() {
        Point centre = new Point(5,5);
        Cercle cercle = new Cercle(centre, 5);
        assertTrue(rectangle.intersecteAvec(cercle));
    }
}
