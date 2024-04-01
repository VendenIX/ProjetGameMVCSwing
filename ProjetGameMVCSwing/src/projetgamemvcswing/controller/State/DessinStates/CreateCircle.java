
package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe CreateCircle gére l'état de la creation de Cercle
 * Elle implémente l'interface DessinState pour gérer la création de cercles.
 */
public class CreateCircle implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        double x = e.getX();
        double y = e.getY();
        
        Cercle cercle = new Cercle(new Point(x, y), 0, new Color(255, 255, 255));
        cercle.ajoutEcouteur(panelDessin); 
        panelDessin.setFigureEnCoursDeDessin(cercle);
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        
        Figure formeEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
    
        if (formeEnCoursDeDessin != null) {
            
            handler.handle(new CreationForme(formeEnCoursDeDessin, container));
            panelDessin.setFigureEnCoursDeDessin(null);
        }
        
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        
        Figure cercleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        Cercle cercle = (Cercle) cercleEnCoursDeDessin;
        double mouseX = e.getX();
        double mouseY = e.getY();
        
        // calculer la distance entre le centre du cercle et le point actuel de la souris
        double radius = cercle.distance(mouseX, mouseY);
        cercle.setRayon(radius);
    }
    
}

