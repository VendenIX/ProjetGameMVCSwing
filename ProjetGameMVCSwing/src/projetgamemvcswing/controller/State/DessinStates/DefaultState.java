package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.modele.geometry.FormContainer;


/**
 * La classe DefaultState gére l'état par défaut  
 * Elle implémente l'interface DessinState pour 
 * gérer l'état par défaut du panneau de dessin.
 */
public class DefaultState implements DessinState {
    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        System.out.println("État par défaut : pression de la souris");
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e,  CommandHandler handler, FormContainer container) {
        System.out.println("État par défaut : relâchement de la souris");
    }
    
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        System.out.println("État par défaut : glissement de la souris");
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        System.out.println("État par défaut : dessiner la forme");
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        System.out.println("État par défaut : remplir la forme");
    }
}
