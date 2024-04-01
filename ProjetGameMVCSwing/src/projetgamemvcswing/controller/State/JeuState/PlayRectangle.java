
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Set;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayRectangle implements JeuState {

    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        long nombreFiguresUtilisateur = panelJeu.getFigures().stream()
            .filter(figure -> !figure.getCouleur().equals(Color.RED)) 
            .count();

        if (nombreFiguresUtilisateur >= 4) {
            // Afficher un message ou désactiver la création de nouvelles figures
            System.out.println("Nombre maximum de figures ajoutées par l'utilisateur atteint.");
            panelJeu.setCurrentState(new FinGame());
            panelJeu.passerEnFinGame();
            return; // Sortir de la méthode pour éviter d'ajouter de nouvelles figures
        }
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
            Rectangle nouveauRectangle = new Rectangle(x, y, 0, 0, Color.CYAN);
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
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e, GameScore gameScore) {
        
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
        
        if (formeEnCoursDeDessin != null) {
            // Ajouter la forme actuellement dessinée à la liste des figures du panneau
            panelJeu.getContainer().ajoutForm(formeEnCoursDeDessin);

            gameScore.addAireCouverte(formeEnCoursDeDessin.getSurface());

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

    
}
