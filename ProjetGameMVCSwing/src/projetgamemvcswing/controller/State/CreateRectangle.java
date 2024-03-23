package projetgamemvcswing.controller.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;

/**
 * La classe CreateRectangle gére l'état de la creation de Rectangle
 * Elle implémente l'interface DessinState pour gérer la création de rectangles.
 */
public class CreateRectangle implements DessinState {
    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
        // Créer un rectangle avec une couleur transparente
        panelDessin.setFigureEnCoursDeDessin(new Rectangle(x, y, 0, 0, new Color(0, 0, 0, 0)));
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        
        Figure rectangleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        // Ajouter le rectangle actuellement dessiné à la liste des figures du panneau
        panelDessin.getFigures().add(rectangleEnCoursDeDessin);
        
        // Réinitialiser la figure en cours de dessin à null
        panelDessin.setFigureEnCoursDeDessin(null);
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        
        double mouseX = e.getX();
        double mouseY = e.getY();
        
        Figure rectangleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        Rectangle rectangle = (Rectangle) rectangleEnCoursDeDessin;
        
        // Calculer la largeur et la hauteur du rectangle 
        // en fonction de la position actuelle de la souris
        double Largeur = Math.abs(mouseX - rectangle.getX());
        double Hauteur = Math.abs(mouseY - rectangle.getY());
        
        rectangle.setLargeur(Largeur);
        rectangle.setHauteur(Hauteur);
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        // Dessiner la forme (rectangle) sur le panneau
        g.setColor(Color.BLACK);
        
        Rectangle rectangle = (Rectangle) forme;
        
        int xInt = (int) Math.round(rectangle.getX());
        int yInt = (int) Math.round(rectangle.getY());
        
        int LargeurInt = (int) Math.round(rectangle.getLargeur());
        int HauteurtInt = (int) Math.round(rectangle.getHauteur());
        
        // Dessiner le rectangle avec les coordonnées calculées
        g.drawRect(xInt, yInt, LargeurInt, HauteurtInt);
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté 
    }
}
