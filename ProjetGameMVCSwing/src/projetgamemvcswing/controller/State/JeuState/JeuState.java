
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public interface JeuState {
    /**
     * Gère l'événement de pression de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     */
    void handleMousePressed(PanelJeu panel, MouseEvent e);
    
    /**
     * Gère l'événement de relâchement de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     * @param gameScore
     */
    void handleMouseReleased(PanelJeu panel, MouseEvent e, GameScore gameScore);
    
    /**
     * Gère l'événement de glissement de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     */
    void handleMouseDragged(PanelJeu panel, MouseEvent e);
}
