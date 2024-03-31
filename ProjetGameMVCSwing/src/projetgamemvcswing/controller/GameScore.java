
package projetgamemvcswing.controller;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;



public class GameScore extends AbstractModeleEcoutable{
    
    // Aire totale couverte par les formes bleues
    private double aireCouverte = 0;
    
    // Getter et setter pour l'aire couverte
    public double getAireCouverte() {
        return aireCouverte;
    }
    
    public void addAireCouverte(double aire) {
        this.aireCouverte += aire;
        fireChange();
    }
    
    // Calcul du pourcentage de l'aire couverte
    public double calculerPourcentageAireCouverte(double airePanel) {
        return (aireCouverte / airePanel) * 100;
    }
}
