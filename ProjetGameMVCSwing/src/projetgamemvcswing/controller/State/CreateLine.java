package projetgamemvcswing.controller.State;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;

/**
 * La classe CreateLine gére l'état de la creation de Ligne
 * Elle implémente l'interface DessinState pour gérer la création de lignes.
 */
public class CreateLine implements DessinState {

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
        // Créer une ligne avec un point de départ et de fin identiques et une couleur noire
        panelDessin.setFigureEnCoursDeDessin(new Ligne(new Point(x, y), new Point(x, y), Color.BLACK));
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e) {
        
        Figure ligneEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        // Ajouter la ligne actuellement dessinée à la liste des figures du panneau
        panelDessin.getFigures().add(ligneEnCoursDeDessin);
        
        // Réinitialiser la figure en cours de dessin à null
        panelDessin.setFigureEnCoursDeDessin(null);
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        
        double mouseX = e.getX();
        double mouseY = e.getY();
        
        Figure ligneEnCoursDeDessin = panelDessin.getFigureEnCoursDeDessin();
        
        // Mettre à jour les coordonnées de fin de la ligne 
        // en fonction de la position actuelle de la souris
        ((Ligne) ligneEnCoursDeDessin).setXFin(mouseX);
        ((Ligne) ligneEnCoursDeDessin).setYFin(mouseY);
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        // Dessiner la forme (ligne) sur le panneau
        Ligne ligne = (Ligne) forme;
        
        // Calcule Rectangle
        int x1Int = (int) Math.round(ligne.getXDebut());
        int y1Int = (int) Math.round(ligne.getYDebut());
        int x2Int = (int) Math.round(ligne.getXFin());
        int y2Int = (int) Math.round(ligne.getYFin());
        
        // Dessiner une ligne avec les coordonnées calculées
        g.drawLine(x1Int, y1Int, x2Int, y2Int);
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté 
    }
}
