package projetgamemvcswing.solveurs;

import projetgamemvcswing.modele.geometry.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import projetgamemvcswing.controller.GameScore;

public class RandomSolve {
    private List<Figure> figuresObstacles;
    private List<Figure> currentFigures;
    private GameScore gameScoreTemporaire; // Score d'apprentissage temporaire
    private GameScore gameScoreGlobal;
    private int nombreGenerations = 10000000;
    private double meilleurScore = 0;
    private List<Figure> meilleureSolution = new ArrayList<>();
    private Random random = new Random();
    private final double metriqueGrossissement = 1.1;
    private int panelWidth;
    private int panelHeight;

    public RandomSolve(List<Figure> figuresObstacles, GameScore gameScoreGlobal, List<Figure> currentFigures, int panelWidth, int panelHeight) {
        this.figuresObstacles = figuresObstacles;
        this.gameScoreGlobal = gameScoreGlobal;
        this.gameScoreTemporaire = new GameScore(); 
        this.currentFigures = new ArrayList<>(figuresObstacles); // Initialement égale aux obstacles
        this.currentFigures.addAll(currentFigures); // Ajouter les figures bleues posées
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

    }

    public List<Figure> getSoluce() {
        gameScoreTemporaire = new GameScore(); // Réinitialisation du score d'apprentissage temporaire pour chaque appel
        double meilleurScoreTemporaire = 0; // Meilleur score atteint durant l'apprentissage

        for (int i = 0; i < nombreGenerations; i++) {
            currentFigures.clear();
            currentFigures.addAll(figuresObstacles);
            List<Figure> tentativeSolution = new ArrayList<>(); // Conserver les obstacles
            gameScoreTemporaire.setAireCouverte(0); // Réinitialiser le score temporaire pour cette génération

            for (int j = 0; j < 4; j++) { // Limite fixe à 4 formes générées
                Figure figure = genererFigureAleatoireEtAugmenterTaille();
                if (figure != null) {
                    tentativeSolution.add(figure);
                    currentFigures.add(figure);
                    gameScoreTemporaire.addAireCouverte(figure.getSurface()); // Utiliser le score temporaire
                }
            }

            if (gameScoreTemporaire.getAireCouverte() > meilleurScoreTemporaire) {
                meilleurScoreTemporaire = gameScoreTemporaire.getAireCouverte();
                meilleureSolution = new ArrayList<>(tentativeSolution);
            }
        }

        // Mettre à jour le score global uniquement si le meilleur score temporaire dépasse le score global actuel
        if (meilleurScoreTemporaire > gameScoreGlobal.getAireCouverte()) {
            gameScoreGlobal.setAireCouverte(meilleurScoreTemporaire);
            // Assumer que l'on veut aussi mettre à jour les figures avec la meilleure solution trouvée
            // Cela peut nécessiter des ajustements selon la logique de votre application
        }

        return meilleureSolution;
    }


    
    private Figure genererFigureAleatoireEtAugmenterTaille() {
        int maxTentatives = 100;
        for (int i = 0; i < maxTentatives; i++) {
            double x = random.nextDouble() * this.panelWidth;
            double y = random.nextDouble() * this.panelHeight;
            Point point = new Point(x, y);

            if (estDansObstacle(point)) {
                continue;
            }

            if (random.nextBoolean()) {
                // Générer un rectangle
                Rectangle rectangle = new Rectangle(x, y, 10, 10, Color.BLUE); // Taille initiale minimale
                augmenterTailleRectangle(rectangle); // Augmenter la taille tout en vérifiant les collisions
                return rectangle;
            } else {
                // Générer un cercle
                Cercle cercle = new Cercle(new Point(x, y), 5, Color.BLUE); // Rayon initial minimal
                augmenterTailleCercle(cercle); // Augmenter le rayon tout en vérifiant les collisions
                return cercle;
            }
        }
        return null; // Aucune figure générée sans chevauchement après maxTentatives
    }

    private void augmenterTailleRectangle(Rectangle rectangle) {
        while (true) {
            Rectangle tempRectangle = new Rectangle(rectangle.getX(), rectangle.getY(),
                                                    rectangle.getLargeur() * metriqueGrossissement, rectangle.getHauteur() * metriqueGrossissement,
                                                    rectangle.getCouleur());
            if (verifierCollisions(tempRectangle)) {
                break; // Garder la dernière taille valide sans collision
            }
            rectangle.setLargeur(rectangle.getLargeur() * metriqueGrossissement);
            rectangle.setHauteur(rectangle.getHauteur() * metriqueGrossissement);
        }
    }

    private void augmenterTailleCercle(Cercle cercle) {
        while (true) {
            Cercle tempCercle = new Cercle(cercle.getCentre(), cercle.getRayon() * metriqueGrossissement, cercle.getCouleur());
            if (verifierCollisions(tempCercle)) {
                break; // Garder le dernier rayon valide sans collision
            }
            cercle.setRayon(cercle.getRayon() * metriqueGrossissement);
        }
    }

    private boolean verifierCollisions(Figure figure) {
        // Vérifier la collision avec d'autres figures
        for (Figure autre : currentFigures) {
            if (figure != autre && figure.intersecteAvec(autre)) {
                return true; // Collision détectée
            }
        }

        // Vérifier si la figure reste dans les limites du panneau
        if (figure instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) figure;
            if (rectangle.getX() < 0 || rectangle.getY() < 0 
                || rectangle.getX() + rectangle.getLargeur() > panelWidth 
                || rectangle.getY() + rectangle.getHauteur() > panelHeight) {
                return true; // La figure dépasse les limites du panneau
            }
        } else if (figure instanceof Cercle) {
            Cercle cercle = (Cercle) figure;
            if (cercle.getX() - cercle.getRayon() < 0 || cercle.getY() - cercle.getRayon() < 0
                || cercle.getX() + cercle.getRayon() > panelWidth 
                || cercle.getY() + cercle.getRayon() > panelHeight) {
                return true; // Le cercle dépasse les limites du panneau
            }
        }

        return false; // Aucune collision détectée et la figure est dans les limites
    }


    private boolean estDansObstacle(Point point) {
        for (Figure figure : currentFigures) {
            if (figure.intersecteAvec(new Cercle(point, 15, Color.RED))) {
                return true;
            }
        }
        return false;
    }
    
    public GameScore getScore() {
        return this.gameScoreGlobal;
    }
}
