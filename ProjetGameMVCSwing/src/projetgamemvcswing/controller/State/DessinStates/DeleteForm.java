package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.SuppressionForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe DeleteForm gére l'état suppression des formes 
 * Elle implémente l'interface DessinState pour gérer 
 * la suppression des formes dans le panneau de dessin.
 */
public class DeleteForm implements DessinState {

    private Figure figureASupprimer = null;
    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        // identifier la figure à supprimer
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(x, y)) {
                figureASupprimer = f;
                break; // sortir de la boucle après avoir trouvé une figure
            }
        }
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        
        if (figureASupprimer != null) {

            handler.handle(new SuppressionForme(figureASupprimer, container));

            panelDessin.getFigures().remove(figureASupprimer);
            panelDessin.modelUpdated(this); 

            figureASupprimer = null;
        }
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté  
    }


}
