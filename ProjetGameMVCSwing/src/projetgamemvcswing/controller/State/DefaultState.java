package projetgamemvcswing.controller.State;

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
        // Gérer l'événement de pression de la souris dans l'état par défaut
        System.out.println("État par défaut : pression de la souris");
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e,  CommandHandler handler, FormContainer container) {
        // Gérer l'événement de relâchement de la souris dans l'état par défaut
        System.out.println("État par défaut : relâchement de la souris");
    }
    
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Gérer l'événement de glissement de la souris dans l'état par défaut
        System.out.println("État par défaut : glissement de la souris");
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        // Dessiner la forme dans l'état par défaut
        System.out.println("État par défaut : dessiner la forme");
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Remplir la forme dans l'état par défaut
        System.out.println("État par défaut : remplir la forme");
    }
}
