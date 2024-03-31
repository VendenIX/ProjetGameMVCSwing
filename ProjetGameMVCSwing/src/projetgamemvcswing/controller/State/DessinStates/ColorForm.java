
package projetgamemvcswing.controller.State.DessinStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import projetgamemvcswing.controller.Command.ColoriageForme;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe ColorForm gére l'état coloration
 * elle implémente l'interface DessinState pour gérer les actions liées
 * à la coloration des formes.
 */
public class ColorForm implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        // Trouver la première figure qui contient le point (x,y)
        Figure selectedFigure = panelDessin.getFigures().stream() //pour pouvoir faire un filter (conversion en stream java)
                                           .filter(f -> f.contient(x, y)) // garder les éléments qui respecte la condition f contient x y
                                           .findFirst().orElse(null); // recup le premier element qui reste ds le stream, si y a r on return null avec orElse

        if (selectedFigure != null) {
            panelDessin.setFigureEnCoursDeColoration(selectedFigure);
        }
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e,  CommandHandler handler, FormContainer container) {
        Figure forme = panelDessin.getFigureEnCoursDeColoration();
        Boolean nouvelleNeedsBorder = forme.needsBorder();
        handler.handle(new ColoriageForme(forme, panelDessin.couleurChoisie, nouvelleNeedsBorder));
        panelDessin.setFigureEnCoursDeColoration(null);
    }
    
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté 
    }
    
}

