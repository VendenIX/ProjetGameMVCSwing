package projetgamemvcswing.controller.State.DessinState;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Ligne;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;
import projetgamemvcswing.controller.Command.CommandHandler;
import projetgamemvcswing.controller.Command.DeplacementForme;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * La classe MoveForm implémente l'interface DessinState
 * pour gérer le déplacement des formes dans le panneau de dessin.
 */
public class MoveForm implements DessinState {
    
    private double initialX, initialY; // des attributs pour stocker les positions initiales

    @Override
    public void handleMousePressed(PanelDessin panelDessin, MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
        
        
        // Parcours la liste des figures
        for (Figure f : panelDessin.getFigures()) {
            if (f.contient(initialX, initialY)) {
                // Définir la figure sélectionnée comme celle à déplacer
                panelDessin.setFigureEnCoursDeTranslation(f);                    
                return;
            }
        } 
    }

    @Override
    public void handleMouseReleased(PanelDessin panelDessin, MouseEvent e, CommandHandler handler, FormContainer container) {
        Figure figureEnCoursDeTranslation = panelDessin.getFigureEnCoursDeTranslation();
        if (figureEnCoursDeTranslation != null) {
            double dx = e.getX() - initialX;
            double dy = e.getY() - initialY;
            
            // Appliquer de manière permanente la translation à la figure
            figureEnCoursDeTranslation.translater(dx, dy);

            // Créer et exécuter la commande de déplacement pour enregistrement
            DeplacementForme commandeDeplacement = new DeplacementForme(figureEnCoursDeTranslation, dx, dy);
            handler.handle(commandeDeplacement);

            panelDessin.modelUpdated(this); // Rafraîchir l'affichage après le déplacement

            // Réinitialiser la figure en cours de translation
            panelDessin.setFigureEnCoursDeTranslation(null);
        }
    }

    @Override
    public void handleMouseDragged(PanelDessin panelDessin, MouseEvent e) {
        if (panelDessin.getFigureEnCoursDeTranslation() != null) {
            double dx = e.getX() - initialX;
            double dy = e.getY() - initialY;
            
            // Appliquer temporairement la translation pour affichage dynamique
            panelDessin.getFigureEnCoursDeTranslation().translater(dx, dy);

            // Préparer pour le prochain calcul de delta
            initialX = e.getX();
            initialY = e.getY();

            panelDessin.modelUpdated(this); // Rafraîchir pour montrer la position temporaire
        }
    }

    @Override
    public void drawShape(Graphics g, Figure forme) {
        // Non Implémenté 
    }

    @Override
    public void fillShape(Graphics2D g2d, Figure forme) {
        // Non Implémenté     
    }
}
