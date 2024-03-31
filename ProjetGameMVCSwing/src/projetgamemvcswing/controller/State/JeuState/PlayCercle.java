
package projetgamemvcswing.controller.State.JeuState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import projetgamemvcswing.controller.Command.CreationForme;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;


public class PlayCercle implements JeuState {

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

        // créer le cercle que si le point de pression n'est pas à l'intérieur d'une autre forme
        if (!dansUneAutreForme) {
            Cercle cercle = new Cercle(new Point(x, y), 0, new Color(30, 144, 255));
            cercle.ajoutEcouteur(panelJeu); 
            panelJeu.setFigureEnCoursDeDessin(cercle);
        } else {
            // // faire un pop up que on peut pas colisionner ou un effet visuel ou audio ?
        }
    }


    @Override
    public void handleMouseReleased(PanelJeu panelJeu, MouseEvent e) {
        // Recuperer le cercle en cours de dessin
        Figure formeEnCoursDeDessin = panelJeu.getFigureEnCoursDeDessin();
    
        if (formeEnCoursDeDessin != null) {
            
            // Ajouter le cercle actuellement dessiné à la liste des figures
            panelJeu.getFigures().add(formeEnCoursDeDessin);
            // Utiliser CreationForme avec l'objet Figure et le container
            //handler.handle(new CreationForme(formeEnCoursDeDessin, container));
            
            panelJeu.setFigureEnCoursDeDessin(null);
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


    @Override
    public void drawShape(Graphics g, Figure forme) {
        //g.setColor(Color.BLACK);
        Cercle cercle = (Cercle) forme;
        
        // Recuperation des coordonnees et Calcule du diamètre
        int x = (int) Math.round(cercle.getX() - cercle.getRayon());
        int y = (int) Math.round(cercle.getY() - cercle.getRayon());
        int diameter = (int) Math.round(2 * cercle.getRayon());
        
        // Dessiner un cercle avec les coordonnées et le diamètre calculés
        //g.drawOval(x, y, diameter, diameter);
    }
    
}
