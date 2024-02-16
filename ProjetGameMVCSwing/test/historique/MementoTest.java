package historique;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projetgamemvcswing.historique.Memento;

public class MementoTest {

    private Memento memento;
    private double x;
    private double y;
    private double[] proprietes;

    @Before
    public void setUp() {
        x = 1.0;
        y = 2.0;
        proprietes = new double[]{3.0, 4.0};
        memento = new Memento(x, y, proprietes);
    }

    @Test
    public void testGetX() {
        assertEquals("La valeur de X n'est pas correctement récupérée", x, memento.getX(), 0.0);
    }

    @Test
    public void testGetY() {
        assertEquals("La valeur de Y n'est pas correctement récupérée", y, memento.getY(), 0.0);
    }

    @Test
    public void testGetProprietes() {
        assertArrayEquals("Le tableau de propriétés n'est pas correctement récupéré", proprietes, memento.getProprietes(), 0.0);
    }

    @Test
    public void testProprietesImmutabilite() {
        double[] recuperes = memento.getProprietes();
        recuperes[0] = 10.0; // Tentative de modification du tableau récupéré
        // Vérification que le tableau original n'a pas été modifié
        assertNotEquals("Le tableau de propriétés doit être immuable", 10.0, memento.getProprietes()[0], 0.0);
    }
}
