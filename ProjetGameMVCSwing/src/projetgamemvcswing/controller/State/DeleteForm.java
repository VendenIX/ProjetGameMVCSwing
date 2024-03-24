package projetgamemvcswing.controller.State;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;

/**
 * La classe DeleteForm gére l'état suppression des formes 
 * Elle implémente l'interface DessinState pour gérer 
 * la suppression des formes dans le panneau de dessin.
 */
public class DeleteForm implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        // Gérer l'événement de pression de la souris pour la suppression de la forme
        double x = e.getX();
        double y = e.getY();
        
        // Parcourir la liste des figures pour trouver celle qui contient les coordonnées de la souris
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(x, y)) {
                // Supprimer la figure sélectionnée de la liste principale
                panelDessin.supprimerFigure(f);
                // Sortir de la boucle après la suppression d'une figure
                return;
            }
        }
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté 
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté  
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        // Non Implémenté 
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté 
    }
}
