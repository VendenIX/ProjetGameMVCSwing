package projetgamemvcswing.geometry;

import projetgamemvcswing.historique.Memento;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CercleTest {
    
    private Cercle cercle;
    
    @Before
    public void setUp() {
        cercle = new Cercle(0, 0, 5);
    }

    @Test
    public void testToString() {
        String expected = "Cercle{centre=(0.0, 0.0), rayon=5.0}";
        assertEquals("La représentation en chaîne de caractères du cercle est incorrecte", expected, cercle.toString());
    }

    @Test
    public void testGetHistorique() {
        // Après la création, l'historique doit avoir 1 état (état initial)
        assertEquals("L'historique doit contenir 1 état après la création du cercle", 1, cercle.getHistorique().size());
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
    public void testSauvegarderEtat() {
        cercle.translater(10, 5); // Cela devrait sauvegarder un nouvel état
        assertEquals("L'historique doit contenir 2 états après une translation", 2, cercle.getHistorique().size());
    }

    @Test
    public void testIntersecteAvec() {
        Cercle autreCercle = new Cercle(5, 0, 5);
        assertTrue("Les cercles devraient s'intersecter", cercle.intersecteAvec(autreCercle));

        Cercle cercleEloigne = new Cercle(20, 20, 2);
        assertFalse("Les cercles ne devraient pas s'intersecter", cercle.intersecteAvec(cercleEloigne));
    }

    @Test
    public void testRestaurerEtat() {
        cercle.translater(10, 5);
        Memento etatInitial = cercle.getHistorique().get(0);
        cercle.restaurerEtat(etatInitial);
        assertEquals("La restauration d'état ne fonctionne pas pour x", 0.0, cercle.getX(), 0.0);
        assertEquals("La restauration d'état ne fonctionne pas pour y", 0.0, cercle.getY(), 0.0);
    }
}
