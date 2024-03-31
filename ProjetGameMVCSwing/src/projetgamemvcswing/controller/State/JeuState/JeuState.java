
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
     */
    void handleMouseReleased(PanelJeu panel, MouseEvent e);
    
    /**
     * Gère l'événement de glissement de la souris.
     * 
     * @param panel Le panneau de dessin.
     * @param e     L'événement de la souris.
     */
    void handleMouseDragged(PanelJeu panel, MouseEvent e);

    /**
     * Dessine la forme spécifiée.
     * 
     * @param g     L'objet Graphics pour dessiner.
     * @param forme La forme à dessiner.
     */
    void drawShape(Graphics g, Figure forme);
    
    /**
     * Remplit la forme spécifiée.
     * 
     * @param g2d   L'objet Graphics2D pour dessiner.
     * @param forme La forme à remplir.
     */
}
