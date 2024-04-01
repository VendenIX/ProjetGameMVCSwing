package projetgamemvcswing.controller;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

/**
 * Classe représentant les scores du jeu.
 * Étend la classe AbstractModeleEcoutable pour implémenter la gestion d'observateur.
 */
public class GameScore extends AbstractModeleEcoutable {
    
    // Score du joueur
    private double scoreJeu = 0;
    
    // Score maximum
    private double scoreMax;
    
    /**
     * Définit le score maximum.
     * 
     * @param scoreMax Le nouveau score maximum.
     */
    public void setScoreMax(double scoreMax) {
        this.scoreMax = scoreMax;
        fireChange(); // Déclenche la notification du changement aux observateurs
    }
    
    /**
     * Obtient le score maximum.
     * 
     * @return Le score maximum.
     */
    public double getScoreMax() {
        return 100;//this.scoreMax;
    }
    
    /**
     * Obtient le score du joueur.
     * 
     * @return Le score du joueur.
     */
    public double getScoreJeu() {
        return (scoreJeu / this.scoreMax) * 100;
    }
    
    /**
     * Définit le score du joueur.
     * 
     * @param scoreJeu Le nouveau score du joueur.
     */
    public void setScoreJeu(double scoreJeu) {
        this.scoreJeu += scoreJeu;
        fireChange(); // Déclenche la notification du changement aux observateurs
    }
    
}
