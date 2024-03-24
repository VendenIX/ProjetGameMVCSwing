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
        
        panelDessin.setLastMouseX(x);
        panelDessin.setLastMouseY(y);
        
        Rectangle nouveauRectangle = new Rectangle(x, y, 0, 0, new Color(0, 0, 0, 0));

        nouveauRectangle.ajoutEcouteur(panelDessin); 

        panelDessin.setFigureEnCoursDeDessin(nouveauRectangle);
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
        double lastMouseX = panelDessin.getLastMouseX();
        double lastMouseY = panelDessin.getLastMouseY();
        
        Figure rectangleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        if (rectangleEnCoursDeDessin instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) rectangleEnCoursDeDessin;

            // Utilisez la logique que vous avez fournie pour ajuster le rectangle
            double newWidth = Math.abs(mouseX - lastMouseX);
            double newHeight = Math.abs(mouseY - lastMouseY);
            double newX = Math.min(mouseX, lastMouseX);
            double newY = Math.min(mouseY, lastMouseY);

            if (mouseX < lastMouseX) {
                newX = mouseX;
            }
            if (mouseY < lastMouseY) {
                newY = mouseY;
            }

            rectangle.setX(newX);
            rectangle.setY(newY);
            rectangle.setLargeur(newWidth);
            rectangle.setHauteur(newHeight);
        }
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