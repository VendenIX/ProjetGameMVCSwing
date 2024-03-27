package projetgamemvcswing.controller.State;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;

/**
 * La classe MoveForm implémente l'interface DessinState
 * pour gérer le déplacement des formes dans le panneau de dessin.
 */
public class MoveForm implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        
        // Sauvegarde de la position précédente de la souris
        panelDessin.setLastMouseX(x); 
        panelDessin.setLastMouseY(y);
        
        // Parcours la liste des figures
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(x, y)) {
                // Définir la figure sélectionnée comme celle à déplacer
                panelDessin.setFigureEnCoursDeTranslation(f);                    
                return;
            }
        } 
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        // Remettre la figure en cours de déplacement à null
        panelDessin.setFigureEnCoursDeTranslation(null);
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        
        // Calculer les déplacements en x et y
        double dx = mouseX - panelDessin.getLastMouseX();
        double dy = mouseY - panelDessin.getLastMouseY();

        // Mettre à jour la dernière position de la souris
        panelDessin.setLastMouseX(mouseX); 
        panelDessin.setLastMouseY(mouseY);

        // Déplacer la figure en cours de déplacement seulent son instance/type
        if (panelDessin.getFigureEnCoursDeTranslation() instanceof Cercle) {
            ((Cercle) panelDessin.getFigureEnCoursDeTranslation())
                    .translater(dx, dy);
            
        } else if (panelDessin.getFigureEnCoursDeTranslation() instanceof Rectangle) {
            ((Rectangle) panelDessin.getFigureEnCoursDeTranslation())
                    .translater(dx, dy);
            
        } else if (panelDessin.getFigureEnCoursDeTranslation() instanceof Ligne) {
            ((Ligne) panelDessin.getFigureEnCoursDeTranslation())
                    .translater(dx, dy);
        }
    }

    @Override
    public void drawShape(Graphics g, Figure forme, CommandHandler handler) {
        // Non Implémenté 
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté     
    }
}
