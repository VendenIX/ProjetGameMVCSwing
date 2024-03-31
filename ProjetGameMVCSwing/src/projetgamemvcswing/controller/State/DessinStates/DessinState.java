
package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * L'interface DessinState définit les méthodes nécessaires pour
 * gérer les différents états du dessin dans le panneau de dessin.
 */
public interface DessinState {
    
    /**
     * Gère l'événement de pression de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     */
    void handleMousePressed(PanelDessin panel, MouseEvent e);
    
    /**
     * Gère l'événement de relâchement de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     * @param handler Handler de commandes à passer en paramètres pour undo/redo
     */
    void handleMouseReleased(PanelDessin panel, MouseEvent e, CommandHandler handler, FormContainer container);
    
    /**
     * Gère l'événement de glissement de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     */
    void handleMouseDragged(PanelDessin panel, MouseEvent e);
}

