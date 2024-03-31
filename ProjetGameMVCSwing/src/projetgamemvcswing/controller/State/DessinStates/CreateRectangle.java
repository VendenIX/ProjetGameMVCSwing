package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe CreateRectangle gére l'état de la creation de Rectangle
 * Elle implémente l'interface DessinState pour gérer la création de rectangles.
 */
public class CreateRectangle implements DessinState {
    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        double x = e.getX();
        double y = e.getY();
        
        panelDessin.setLastMouseX(x);
        panelDessin.setLastMouseY(y);
        
        Rectangle nouveauRectangle = new Rectangle(x, y, 0, 0, new Color(255, 255, 255));

        nouveauRectangle.ajoutEcouteur(panelDessin); 

        panelDessin.setFigureEnCoursDeDessin(nouveauRectangle);
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        
        Figure formeEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();

        if (formeEnCoursDeDessin != null) {

            handler.handle(new CreationForme(formeEnCoursDeDessin, container));

            panelDessin.setFigureEnCoursDeDessin(null);
        }

        
        System.out.println("Taille handler : " + handler.getStackSize());
        

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

}
