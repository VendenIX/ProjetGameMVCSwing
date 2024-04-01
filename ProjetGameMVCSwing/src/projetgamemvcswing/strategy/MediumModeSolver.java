package projetgamemvcswing.strategy;

import java.util.ArrayList;
import java.util.List;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.solveurs.RandomSolve;

public class MediumModeSolver implements SolverStrategy {
    
    private RandomSolve solver;
    
    @Override
    public List<Figure> solve(List<Figure> figuresObstacles, GameScore gameScoreGlobal, List<Figure> currentFigures, int panelWidth, int panelHeight) {
        // Implémentez votre logique de résolution pour le mode moyen ici
        
        this.solver = new RandomSolve(figuresObstacles, gameScoreGlobal, currentFigures, panelWidth, panelWidth, 1000000, 1.1);
        return solver.getSoluce();
    }

    @Override
    public RandomSolve getSolver() {
        return solver; // Retourne le solveur
    }
}
