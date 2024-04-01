package projetgamemvcswing.controller.State.JeuState;

import java.awt.event.MouseEvent;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;

public class FinGame implements JeuState {
    
    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        // Ne rien faire
    }

    @Override
    public void handleMouseDragged(PanelJeu panelJeu, MouseEvent e) {
        // Ne rien faire
    }
    
    @Override
    public void handleMouseReleased(PanelJeu panel, MouseEvent e, GameScore gameScore) {
        
    }
}
