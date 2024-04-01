package projetgamemvcswing.controller;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

public class GameScore extends AbstractModeleEcoutable {
    
    // Aire totale couverte par les formes bleues
    private double aireCouverte = 0;
    // Aire totale des formes rouges
    private double aireFormesRouges = 0;

    // Getter et setter pour l'aire couverte
    public double getAireCouverte() {
        return aireCouverte;
    }
    
    public void setAireCouverte(double nouvelleAire) {
        this.aireCouverte = nouvelleAire;
    }
    
    public void addAireCouverte(double aire) {
        this.aireCouverte += aire;
        fireChange();
    }
    
    // Getter et setter pour l'aire des formes rouges
    public double getAireFormesRouges() {
        return aireFormesRouges;
    }
    
    public void setAireFormesRouges(double aire) {
        this.aireFormesRouges = aire;
        fireChange();
    }

    // Calcul du pourcentage d'aire couverte par rapport à l'aire restante
    // après avoir soustrait l'aire des formes rouges de l'aire totale du JPanel
    public double calculerPourcentageAireCouverte(double airePanel) {
        double aireRestante = airePanel - aireFormesRouges;
        return (aireCouverte / aireRestante) * 100;
    }
}
