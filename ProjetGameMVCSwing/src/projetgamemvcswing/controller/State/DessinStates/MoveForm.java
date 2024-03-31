package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.DeplacementForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe MoveForm implémente l'interface DessinState
 * pour gérer le déplacement des formes dans le panneau de dessin.
 */
public class MoveForm implements DessinState {
    
    private double initialX, initialY; 

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
        
        
        // Parcours la liste des figures
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(initialX, initialY)) {
                // def la figure sélectionnée comme celle à déplacer
                panelDessin.setFigureEnCoursDeTranslation(f);                    
                return;
            }
        } 
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        double finalX = e.getX();
        double finalY = e.getY();
        double dx = finalX - initialX;
        double dy = finalY - initialY;

        Figure figureEnCoursDeTranslation = panelDessin.getFigureEnCoursDeTranslation();
        if (figureEnCoursDeTranslation != null) {
            DeplacementForme commandeDeplacement = new DeplacementForme(figureEnCoursDeTranslation, dx, dy);
            handler.handle(commandeDeplacement);
            panelDessin.setFigureEnCoursDeTranslation(null);
            panelDessin.resetTempTranslation(); // réinitialiser le déplacement temporaire
        }
    }
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        double currentX = e.getX();
        double currentY = e.getY();
        double tempDx = currentX - initialX;
        double tempDy = currentY - initialY;
        
        // update du déplacement temporaire dans PanelDessin pour l'affichage
        panelDessin.setTempTranslation(tempDx, tempDy);
        
        panelDessin.modelUpdated(this);
    }


}
