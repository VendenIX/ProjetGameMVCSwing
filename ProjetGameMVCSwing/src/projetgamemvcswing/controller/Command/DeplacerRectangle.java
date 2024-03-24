package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * Classe pour le déplacement d'un rectangle avec la commande de déplacement
 * 
 * @see OperationCommand
 * @see DeplacerRectangle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 */

public class DeplacerRectangle implements OperationCommand{
        
            /**
            * Attributs de la classe DeplacerRectangle
            */
            private double x;
            private double y;
            private double xFinal;
            private double yFinal;
            private FormContainer formContainer;
            private Rectangle rectangle;
        
            /**
            * Constructeur de la classe DeplacerRectangle
            * @param x
            * @param y
            * @param xFinal
            * @param yFinal
            * @param formContainer
            * @param rectangle
            */
            public DeplacerRectangle(double x, double y, double xFinal, double yFinal, FormContainer formContainer, Rectangle rectangle){
                this.x = x;
                this.y = y;
                this.xFinal = xFinal;
                this.yFinal = yFinal;
                this.formContainer = formContainer;
                this.rectangle = rectangle;
            }
        
            /**
            * Méthode pour déplacer un rectangle
            */
            @Override
            public void operate() {
                // On va déplacer un rectangle
                this.rectangle.deplacer(this.xFinal, this.yFinal);
            }
        
            /**
            * Méthode pour compenser le déplacement d'un rectangle
            */
            @Override
            public void compensate() {
                // On va déplacer un rectangle
                this.rectangle.deplacer(this.x, this.y);
            }
}