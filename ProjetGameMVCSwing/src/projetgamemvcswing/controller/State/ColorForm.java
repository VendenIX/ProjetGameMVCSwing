
package projetgamemvcswing.controller.State;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;

/**
 * La classe ColorForm gére l'état coloration
 * elle implémente l'interface DessinState pour gérer les actions liées
 * à la coloration des formes.
 */
public class ColorForm implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
        // Parcourir la liste des figures
        for (Figure f : panelDessin.getFigures()) {
            // Vérifier si la figure contient les coordonnées de la souris
            if (f.contient(x, y)) {
                
                // Définir la figure sélectionnée comme celle à colorier
                panelDessin.setFigureEnCoursDeColoration(f);

                // Appliquer la couleur sélectionnée à la figure, si elle existe
                if (panelDessin.getFigureEnCoursDeColoration() != null) {
                    
                    // Cas Cercle
                    if (panelDessin.getFigureEnCoursDeColoration() instanceof Cercle) {
                        ((Cercle) panelDessin.getFigureEnCoursDeColoration())
                                .setCouleur(panelDessin.couleurChoisie);
                    // Cas Rectangle   
                    } else if (panelDessin.getFigureEnCoursDeColoration() instanceof Rectangle) {
                        ((Rectangle) panelDessin.getFigureEnCoursDeColoration())
                                .setCouleur(panelDessin.couleurChoisie);
                    // Cas Ligne    
                    } else if (panelDessin.getFigureEnCoursDeColoration() instanceof Ligne) {
                        ((Ligne) panelDessin.getFigureEnCoursDeColoration())
                                .setCouleur(panelDessin.couleurChoisie);
                    }
                }

                return;
            }
        }  
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        // Remettre la figure en cours de coloration à null lors du relâchement de la souris
        panelDessin.setFigureEnCoursDeColoration(null); 
    }
    
    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        // Non Implémenté 
    }

    @Override
    public void drawShape(Graphics g, Figure forme, CommandHandler handler) {
        // Non Implémenté 
    }
    
    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        if (forme instanceof Cercle) {
            // Remplir un cercle
            fillCircle(g2d, (Cercle) forme);
        } else if (forme instanceof Rectangle) {
            // Remplir un rectangle
            fillRectangle(g2d, (Rectangle) forme);
        } else if (forme instanceof Ligne) {
            // Remplir une ligne (non standard)
            fillLine(g2d, (Ligne) forme);
        }
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

