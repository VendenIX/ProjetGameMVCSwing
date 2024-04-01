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
    private GameScore gameScore;
    private int nombreGenerations = 100000;
    private double meilleurScore = 0;
    private List<Figure> meilleureSolution = new ArrayList<>();
    private Random random = new Random();

    private int panelWidth;
    private int panelHeight;

    public RandomSolve(List<Figure> figuresObstacles, List<Figure> currentFigures, int panelWidth, int panelHeight) {
        this.figuresObstacles = figuresObstacles;
        this.currentFigures = new ArrayList<>(figuresObstacles); // Initialement égale aux obstacles
        this.currentFigures.addAll(currentFigures); // Ajouter les figures bleues posées
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.gameScore = new GameScore();
    }

    public List<Figure> getSoluce() {
        for (int i = 0; i < nombreGenerations; i++) {
            
            currentFigures.clear();
            currentFigures.addAll(figuresObstacles);
            List<Figure> tentativeSolution = new ArrayList<>(); // Conserver les obstacles
            gameScore.setAireCouverte(0); // Réinitialiser pour chaque génération

            for (int j = 0; j < 4; j++) { // Limite fixe à 4 formes générées
                Figure figure = genererFigureAleatoireEtAugmenterTaille();
                if (figure != null) {
                    
                    tentativeSolution.add(figure);
                    currentFigures.add(figure);
                    gameScore.addAireCouverte(figure.getSurface());
                }
            }

            if (gameScore.getAireCouverte() > meilleurScore) {
                meilleurScore = gameScore.getAireCouverte();
                meilleureSolution = new ArrayList<>(tentativeSolution);
            }
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
                                                    rectangle.getLargeur() * 2, rectangle.getHauteur() * 2,
                                                    rectangle.getCouleur());
            if (verifierCollisions(tempRectangle)) {
                break; // Garder la dernière taille valide sans collision
            }
            rectangle.setLargeur(rectangle.getLargeur() * 2);
            rectangle.setHauteur(rectangle.getHauteur() * 2);
        }
    }

    private void augmenterTailleCercle(Cercle cercle) {
        while (true) {
            Cercle tempCercle = new Cercle(cercle.getCentre(), cercle.getRayon() * 2, cercle.getCouleur());
            if (verifierCollisions(tempCercle)) {
                break; // Garder le dernier rayon valide sans collision
            }
            cercle.setRayon(cercle.getRayon() * 2);
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
}
