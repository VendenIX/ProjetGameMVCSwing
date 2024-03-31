package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe CreateLine gére l'état de la creation de Ligne
 * Elle implémente l'interface DessinState pour gérer la création de lignes.
 */
public class CreateLine implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        double x = e.getX();
        double y = e.getY();
        
        Ligne nouvelleLigne = new Ligne(new Point(x, y), new Point(x, y), Color.BLACK);

        nouvelleLigne.ajoutEcouteur(panelDessin);

        panelDessin.setFigureEnCoursDeDessin(nouvelleLigne);
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
        double mouseX = e.getX();
        double mouseY = e.getY();

        Ligne ligneEnCoursDeDessin = (Ligne) panelDessin.getFigureEnCoursDeDessin();
        if (ligneEnCoursDeDessin != null) {
            ligneEnCoursDeDessin.setXFin(mouseX);
            ligneEnCoursDeDessin.setYFin(mouseY);
            panelDessin.modelUpdated(this);
        }
    }


}
