package projetgamemvcswing.controller.State;

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

        // Identifier la figure à supprimer
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(x, y)) {
                figureASupprimer = f;
                break; // Sortir de la boucle après avoir trouvé une figure
            }
        }
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        // S'assurer qu'une figure a été sélectionnée pour la suppression
        if (figureASupprimer != null) {
            // Créer et exécuter la commande de suppression
            handler.handle(new SuppressionForme(figureASupprimer, container));

            // Supprimer visuellement la figure
            panelDessin.getFigures().remove(figureASupprimer);
            panelDessin.modelUpdated(this); // Redessiner le panneau pour refléter la suppression

            // Réinitialiser la figure à supprimer pour la prochaine opération
            figureASupprimer = null;
        }
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
