
package projetgamemvcswing.controller.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;

/**
 * La classe CreateCircle gére l'état de la creation de Cercle
 * Elle implémente l'interface DessinState pour gérer la création de cercles.
 */
public class CreateCircle implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
        // Créer un cercle avec un point de départ (x, y), un rayon de 0 et une couleur transparente
        panelDessin.setFigureEnCoursDeDessin(new Cercle(new Point(x, y), 0, new Color(0, 0, 0, 0)));
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperer le cercle en cours de dessin
        Figure cercleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        // Ajouter le cercle actuellement dessiné à la liste des figures
        panelDessin.getFigures().add(cercleEnCoursDeDessin);
        
        // Réinitialiser la figure en cours de dessin à null
        panelDessin.setFigureEnCoursDeDessin(null);
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperer le cercle en cours de dessin
        Figure cercleEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        Cercle cercle = (Cercle) cercleEnCoursDeDessin;
        double mouseX = e.getX();
        double mouseY = e.getY();
        
        // Calculer la distance entre le centre du cercle et le point actuel de la souris
        double radius = cercle.distance(mouseX, mouseY);
        
        // Mettre à jour le rayon du cercle
        cercle.setRayon(radius);
    }
    
    @Override
    public void drawShape(Graphics g, Figure forme) {
        g.setColor(Color.BLACK);
        Cercle cercle = (Cercle) forme;
        
        // Recuperation des coordonnees et Calcule du diamètre
        int x = (int) Math.round(cercle.getX() - cercle.getRayon());
        int y = (int) Math.round(cercle.getY() - cercle.getRayon());
        int diameter = (int) Math.round(2 * cercle.getRayon());
        
        // Dessiner un cercle avec les coordonnées et le diamètre calculés
        g.drawOval(x, y, diameter, diameter);
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté 
    }
}

