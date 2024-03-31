
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Set;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Intersection;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayRectangle implements JeuState {

    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        // Recuperation des cordonnés de la souris apres le press
        double x = e.getX();
        double y = e.getY();
        
        panelJeu.setLastMouseX(x);
        panelJeu.setLastMouseY(y);
        
        Rectangle nouveauRectangle = new Rectangle(x, y, 0, 0, new Color(0, 0, 0, 0));

        nouveauRectangle.ajoutEcouteur(panelJeu); 

        panelJeu.setFigureEnCoursDeDessin(nouveauRectangle);
    }

    @Override
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e, GameScore gameScore) {
        
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
        
        // S'assurer que la forme n'est pas nulle
        if (formeEnCoursDeDessin != null) {
            // Ajouter la forme actuellement dessinée à la liste des figures du panneau
            panelJeu.getFigures().add(formeEnCoursDeDessin);
            
            Rectangle rectangle = (Rectangle) formeEnCoursDeDessin;
            // Calculate surface area for rectangle
            double surfaceRectangle = rectangle.getSurface();
            // Add surface area to scoreJeu
            gameScore.setScoreJeu(surfaceRectangle);

            System.out.println(gameScore.getScoreJeu());

            // Utiliser CreationForme avec l'objet Figure et le container
            //handler.handle(new CreationForme(formeEnCoursDeDessin, container));

            // Réinitialiser la figure en cours de dessin à null pour le prochain dessin
            panelJeu.setFigureEnCoursDeDessin(null);
        }

        
        //System.out.println("Taille handler : " + handler.getStackSize());

        
        
    }

    @Override
    public void handleMouseDragged(PanelJeu panelJeu, MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        double lastMouseX = panelJeu.getLastMouseX();
        double lastMouseY = panelJeu.getLastMouseY();

        // Récupérer la figure (rectangle) en cours de dessin depuis le panneau
        Figure rectangleEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
        
        Rectangle rectangle = (Rectangle) rectangleEnCoursDeDessin;

        // Calculer la nouvelle largeur et hauteur du rectangle
        double newWidth = Math.abs(mouseX - lastMouseX);
        double newHeight = Math.abs(mouseY - lastMouseY);
        double newX = Math.min(mouseX, lastMouseX);
        double newY = Math.min(mouseY, lastMouseY);

        // Obtenir les dimensions actuelles du JPanel
        double panelWidth = panelJeu.getWidth(); // Largeur actuelle du JPanel
        double panelHeight = panelJeu.getHeight(); // Hauteur actuelle du JPanel

        // Assurer que le rectangle reste à l'intérieur des limites du JPanel
        // Si le rectangle dépasse les limites, ajuster ses dimensions pour qu'il reste à l'intérieur

        // Vérifier si le rectangle dépasse la bordure gauche du JPanel
        if (newX < 0) {
            newWidth += newX; 
            newX = 0; 
        }

        // Vérifier si le rectangle dépasse la bordure supérieure du JPanel
        if (newY < 0) {
            newHeight += newY;
            newY = 0; 
        }

        // Vérifier si le rectangle dépasse la bordure droite du JPanel
        if (newX + newWidth > panelWidth) {
            newWidth = panelWidth - newX - 1;
        }

        // Vérifier si le rectangle dépasse la bordure inférieure du JPanel
        if (newY + newHeight > panelHeight) {
            newHeight = panelHeight - newY - 1;
        }
        
        

        // Mettre à jour les dimensions et la position du rectangle
        rectangle.setX(newX); 
        rectangle.setY(newY); 
        rectangle.setLargeur(newWidth); 
        rectangle.setHauteur(newHeight); 
        
        // Get the set of intersecting pixels
        Set<Point> intersectingPixels = Intersection.findIntersectingPixels(panelJeu);

        // Check if the rectangle touches any intersecting point
        for (Point point : intersectingPixels) {
            if (rectangle.contient(point.getX(), point.getY())) {
                System.out.println("GameOver ! Intersected Rectangle");
                break; // No need to check further
            }
        }
        
    }


    @Override
    public void drawShape(Graphics g, Figure forme) {
       // Dessiner la forme (rectangle) sur le panneau
        g.setColor(Color.BLACK);
        
        Rectangle rectangle = (Rectangle) forme;
        
        int xInt = (int) Math.round(rectangle.getX());
        int yInt = (int) Math.round(rectangle.getY());
        
        int LargeurInt = (int) Math.round(rectangle.getLargeur());
        int HauteurtInt = (int) Math.round(rectangle.getHauteur());
        
        // Dessiner le rectangle avec les coordonnées calculées
        g.drawRect(xInt, yInt, LargeurInt, HauteurtInt);
    }
    
}
