
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayRectangle implements JeuState {

    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        // verif si le point de pression est à l'intérieur d'une autre forme
        boolean dansUneAutreForme = false;
        for (Figure f : panelJeu.getFigures()) {
            if (f.contient(x, y)) {
                dansUneAutreForme = true;
                break;
            }
        }

        // créer le rectangle que si le point de pression n'est pas à l'intérieur d'une autre forme
        if (!dansUneAutreForme) {
            Rectangle nouveauRectangle = new Rectangle(x, y, 0, 0, new Color(30, 144, 255));
            System.out.println("La couleur est : "+ nouveauRectangle.getCouleur());
            nouveauRectangle.ajoutEcouteur(panelJeu);
            panelJeu.setFigureEnCoursDeDessin(nouveauRectangle);
            panelJeu.setLastMouseX(x);
            panelJeu.setLastMouseY(y);
        } else {
            // faire un pop up que on peut pas colisionner ou un effet visuel ou audio ?
        }
    }


    @Override
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e) {
        
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
        
        if (formeEnCoursDeDessin != null) {
            // Ajouter la forme actuellement dessinée à la liste des figures du panneau
            panelJeu.getFigures().add(formeEnCoursDeDessin);

            // Utiliser CreationForme avec l'objet Figure et le container
            //handler.handle(new CreationForme(formeEnCoursDeDessin, container));

            // Réinitialiser la figure en cours de dessin à null pour le prochain dessin
            panelJeu.setFigureEnCoursDeDessin(null);
            panelJeu.modelUpdated(this);
        }

        
        //System.out.println("Taille handler : " + handler.getStackSize());
        
    }

    @Override
    public void handleMouseDragged(PanelJeu panelJeu, MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        double lastMouseX = panelJeu.getLastMouseX();
        double lastMouseY = panelJeu.getLastMouseY();

        // copie temporaire de la figure pour tester les ajustements sans modifier l'original
        Rectangle rectangleTemp = (Rectangle) panelJeu.getFigureEnCoursDeDessin().copie();

        double newWidth = Math.abs(mouseX - lastMouseX);
        double newHeight = Math.abs(mouseY - lastMouseY);
        double newX = Math.min(mouseX, lastMouseX);
        double newY = Math.min(mouseY, lastMouseY);

        rectangleTemp.setX(newX);
        rectangleTemp.setY(newY);
        rectangleTemp.setLargeur(newWidth);
        rectangleTemp.setHauteur(newHeight);

        // limiter le rectangle pour qu'il ne sorte pas du panel
        int panelWidth = panelJeu.getWidth();
        int panelHeight = panelJeu.getHeight();
        if (newX + newWidth > panelWidth) newWidth = panelWidth - newX;
        if (newY + newHeight > panelHeight) newHeight = panelHeight - newY;
        if (newX < 0) {
            newWidth += newX;
            newX = 0;
        }
        if (newY < 0) {
            newHeight += newY;
            newY = 0;
        }

        rectangleTemp.setX(newX);
        rectangleTemp.setY(newY);
        rectangleTemp.setLargeur(newWidth);
        rectangleTemp.setHauteur(newHeight);

        // Vérifier si le rectangle temporaire n'intersecte pas avec d'autres formes
        if (!panelJeu.intersecteAvecAutreFigure(rectangleTemp)) {
            // Si pas d'intersection, appliquer les modifications à la figure originale
            Rectangle rectangle = (Rectangle) panelJeu.getFigureEnCoursDeDessin();
            rectangle.setX(newX);
            rectangle.setY(newY);
            rectangle.setLargeur(newWidth);
            rectangle.setHauteur(newHeight);
        } else {
            // mettre effet visuel ou audio qui dit pas possible de faire ça idk
        }
    }



    @Override
    public void drawShape(Graphics g, Figure forme) {
       // Dessiner la forme (rectangle) sur le panneau
        //g.setColor(Color.BLACK);
        
        Rectangle rectangle = (Rectangle) forme;
        
        int xInt = (int) Math.round(rectangle.getX());
        int yInt = (int) Math.round(rectangle.getY());
        
        int LargeurInt = (int) Math.round(rectangle.getLargeur());
        int HauteurtInt = (int) Math.round(rectangle.getHauteur());
        
        // Dessiner le rectangle avec les coordonnées calculées
        //g.drawRect(xInt, yInt, LargeurInt, HauteurtInt);
    }
    
}
