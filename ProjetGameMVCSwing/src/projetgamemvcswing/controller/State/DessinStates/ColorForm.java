
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
        if (forme != null) {
            handler.handle(new ColoriageForme(forme, panelDessin.couleurChoisie));
        }
        panelDessin.setFigureEnCoursDeColoration(null);
    }
    
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté 
    }

    /**
     * Remplit un cercle avec la couleur spécifiée.
     *
     * @param g     L'objet Graphics2D pour dessiner.
     * @param cercle Le cercle à remplir.
     */
    private void fillCircle(Graphics2D g, Cercle cercle) {
        double x = cercle.getX();
        double y = cercle.getY();
        double rayon = cercle.getRayon();
        
        int xInt = (int) Math.round(x - rayon);
        int yInt = (int) Math.round(y - rayon);
        int diamètre = (int) Math.round(2 * rayon);
        
        g.fillOval(xInt, yInt, diamètre, diamètre);
    }

    /**
     * Remplit un rectangle avec la couleur spécifiée.
     *
     * @param g          L'objet Graphics2D pour dessiner.
     * @param rectangle  Le rectangle à remplir.
     */
    private void fillRectangle(Graphics2D g, Rectangle rectangle) {
        double x = rectangle.getX();
        double y = rectangle.getY();
        double largeur = rectangle.getLargeur();
        double hauteur = rectangle.getHauteur();
        
        int xInt = (int) Math.round(x);
        int yInt = (int) Math.round(y);
        int largeurInt = (int) Math.round(largeur);
        int hauteurInt = (int) Math.round(hauteur);
        
        g.fillRect(xInt, yInt, largeurInt, hauteurInt);
    }

    /**
     * Remplit une ligne avec la couleur spécifiée.
     *
     * @param g     L'objet Graphics2D pour dessiner.
     * @param ligne La ligne à remplir.
     */
    private void fillLine(Graphics2D g, Ligne ligne) {
        double x1 = ligne.getXDebut();
        double y1 = ligne.getYDebut();
        double x2 = ligne.getXFin();
        double y2 = ligne.getYFin();
        
        g.fill(new Line2D.Double(x1, y1, x2, y2));
    }
}

