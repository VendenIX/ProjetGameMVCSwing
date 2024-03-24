package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * Classe pour le déplacement d'un cercle avec la commande de déplacement
 * 
 * @see OperationCommand
 * @see DeplacerCercle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 */


public class DeplacerCercle implements OperationCommand{
            
                /**
                * Attributs de la classe DeplacerCercle
                */
                private double x;
                private double y;
                private double xFinal;
                private double yFinal;
                private FormContainer formContainer;
                private Cercle cercle;
            
                /**
                * Constructeur de la classe DeplacerCercle
                * @param x
                * @param y
                * @param xFinal
                * @param yFinal
                * @param formContainer
                * @param cercle
                */
                public DeplacerCercle(double x, double y, double xFinal, double yFinal, FormContainer formContainer, Cercle cercle){
                    this.x = x;
                    this.y = y;
                    this.xFinal = xFinal;
                    this.yFinal = yFinal;
                    this.formContainer = formContainer;
                    this.cercle = cercle;
                }
            
                /**
                * Méthode pour déplacer un cercle
                */
                @Override
                public void operate() {
                    // On va déplacer un cercle
                    this.cercle.deplacer(this.xFinal, this.yFinal);
                }
            
                /**
                * Méthode pour compenser le déplacement d'un cercle
                */
                @Override
                public void compensate() {
                    // On va déplacer un cercle
                    this.cercle.deplacer(this.x, this.y);
                }
}