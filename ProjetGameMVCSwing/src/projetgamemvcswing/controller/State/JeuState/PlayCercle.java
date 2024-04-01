
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Set;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayCercle implements JeuState {

    @Override
    public void handleMousePressed(PanelJeu panelJeu, MouseEvent e) {
        long nombreFiguresUtilisateur = panelJeu.getFigures().stream()
            .filter(figure -> !figure.getCouleur().equals(Color.RED)) 
            .count();

        if (nombreFiguresUtilisateur >= 4) {
            // Afficher un message ou désactiver la création de nouvelles figures
            System.out.println("Nombre maximum de figures ajoutées par l'utilisateur atteint.");
            panelJeu.setCurrentState(new FinGame());
            System.out.println("Passer en fin de jeu :(");
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

        // créer le cercle que si le point de pression n'est pas à l'intérieur d'une autre forme
        if (!dansUneAutreForme) {
            Cercle cercle = new Cercle(new Point(x, y), 0, Color.CYAN);
            cercle.ajoutEcouteur(panelJeu); 
            panelJeu.setFigureEnCoursDeDessin(cercle);
        } else {
            // // faire un pop up que on peut pas colisionner ou un effet visuel ou audio ?
        }
    }


    @Override
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e, GameScore gameScore) {
        // Recuperer le cercle en cours de dessin
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
    
        if (formeEnCoursDeDessin != null) {
            
            panelJeu.getContainer().ajoutForm(formeEnCoursDeDessin);

            // Mise à jour du score de jeu avec la surface du cercle
            gameScore.addAireCouverte(formeEnCoursDeDessin.getSurface());

            // Réinitialiser la figure en cours de dessin à null
            panelJeu.setFigureEnCoursDeDessin(null);

            // Mettre à jour l'interface utilisateur si nécessaire
            panelJeu.modelUpdated(this);
        }
        
        //System.out.println("Taille handler : " + handler.getStackSize());
        //System.out.println(handler);
        // Réinitialiser la figure en cours de dessin à null
        panelJeu.setFigureEnCoursDeDessin(null);
        panelJeu.modelUpdated(this);
    }

    @Override
    public void handleMouseDragged(PanelJeu panelJeu, MouseEvent e) {
        double mouseX = e.getX();
        double mouseY = e.getY();
        Cercle cercleTemp = (Cercle) panelJeu.getFigureEnCoursDeDessin().copie();
        double radius = cercleTemp.distance(mouseX, mouseY);

        // Limiter le rayon pour rester dans les limites du panel
        int panelWidth = panelJeu.getWidth();
        int panelHeight = panelJeu.getHeight();
        double maxRadius = Math.min(cercleTemp.getX(), panelWidth - cercleTemp.getX());      
        maxRadius = Math.min(maxRadius, Math.min(cercleTemp.getY(), panelHeight - cercleTemp.getY()));
        radius = Math.min(radius, maxRadius);

        cercleTemp.setRayon(radius);

        // Vérifier s'il y a intersection avec une autre forme
        if (!panelJeu.intersecteAvecAutreFigure(cercleTemp)) {
            Cercle cercle = (Cercle) panelJeu.getFigureEnCoursDeDessin();
            cercle.setRayon(radius);
        }
    }

}
