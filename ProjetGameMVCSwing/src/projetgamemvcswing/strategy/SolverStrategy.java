package projetgamemvcswing.strategy;

import java.util.List;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.solveurs.RandomSolve;

public interface SolverStrategy {
    List<Figure> solve(List<Figure> figuresObstacles, GameScore gameScoreGlobal, List<Figure> currentFigures, int panelWidth, int panelHeight);
    RandomSolve getSolver();

}
