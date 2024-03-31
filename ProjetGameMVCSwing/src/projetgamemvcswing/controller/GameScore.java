
package projetgamemvcswing.controller;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;



public class GameScore extends AbstractModeleEcoutable{
    
    // Score du Jouer
    private double scoreJeu = 0;
    
    // Score Maximum
    private double scoreMax;
    
    
    public void setScoreMax(double scoreMax) {
        this.scoreMax = scoreMax;
        fireChange();
    }
    
    public double getScoreMax() {
        return this.scoreMax;
    }
    
    
    public double getScoreJeu() {
        return scoreJeu;
    }
    
    public void setScoreJeu(double scoreJeu) {
        this.scoreJeu += scoreJeu;
        fireChange();
    }
    
}
