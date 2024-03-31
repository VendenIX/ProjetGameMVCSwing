
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Set;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Intersection;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayCercle implements JeuState {

    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
    
        
        Cercle cercle = new Cercle(new Point(x, y), 0, new Color(0, 0, 0, 0));
        cercle.ajoutEcouteur(panelJeu); // Enregistre PanelDessin comme écouteur
        panelJeu.setFigureEnCoursDeDessin(cercle);
    }

    @Override
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e, GameScore gameScore) {
        // Recuperer le cercle en cours de dessin
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
    
        if (formeEnCoursDeDessin != null) {
            
            // Ajouter le cercle actuellement dessiné à la liste des figures
            panelJeu.getFigures().add(formeEnCoursDeDessin);
            // Utiliser CreationForme avec l'objet Figure et le container
            //handler.handle(new CreationForme(formeEnCoursDeDessin, container));
            
            Cercle cercle = (Cercle) formeEnCoursDeDessin;
            // Calculate surface area for rectangle
            double surfaceCercle = cercle.getSurface();
            
            // Add surface area to scoreJeu
            gameScore.setScoreJeu(surfaceCercle);

            System.out.println(gameScore.getScoreJeu());
            
            panelJeu.setFigureEnCoursDeDessin(null);
        }
        
        //System.out.println("Taille handler : " + handler.getStackSize());
        //System.out.println(handler);
        // Réinitialiser la figure en cours de dessin à null
        panelJeu.setFigureEnCoursDeDessin(null);
    }

    @Override
    public void handleMouseDragged(PanelJeu panelJeu, MouseEvent e) {
        // Récupérer le cercle en cours de dessin depuis le panel
        Figure cercleEnCoursDeDessin = (Cercle) panelJeu.getFigureEnCoursDeDessin();

        // Effectuer un cast pour obtenir un objet de type Cercle
        Cercle cercle = (Cercle) cercleEnCoursDeDessin;

        // Récupérer les coordonnées de la souris
        double mouseX = e.getX();
        double mouseY = e.getY();

        // Calculer la distance entre le centre du cercle et la position de la souris
        double radius = cercle.distance(mouseX, mouseY);

        // Obtenir les dimensions du JPanel
        int panelWidth = panelJeu.getWidth();
        int panelHeight = panelJeu.getHeight();

        // Calculer le rayon maximum pour que le cercle reste dans les limites du Panel
        double maxRadius = Math.min(cercle.getX(), panelWidth - cercle.getX());      
        maxRadius = Math.min(maxRadius, Math.min(cercle.getY(), panelHeight - cercle.getY()));

        // S'assurer que le rayon calculé ne dépasse pas le rayon maximum autorisé
        radius = Math.min(radius, maxRadius);

        // Mettre à jour le rayon du cercle
        cercle.setRayon(radius);
        
        // Get the set of intersecting pixels
        Set<Point> intersectingPixels = Intersection.findIntersectingPixels(panelJeu);

        // Check if the rectangle touches any intersecting point
        for (Point point : intersectingPixels) {
            if (cercle.contient(point.getX(), point.getY())) {
                System.out.println("GameOver ! Intersected Cercle");
                break; // No need to check further
            }
        }
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
    
}
