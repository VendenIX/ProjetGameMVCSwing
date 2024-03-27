package projetgamemvcswing.controller.Command;

import java.awt.Color;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Point;

/**
 * Définition de la classe CreationCercle qui permet de créer un cercle avec la commande de création
 * 
 * @see OperationCommand
 * @see CreationCercle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 */


public class CreationCercle implements OperationCommand{
    /**
     * Attributs de la classe CreationCercle
     */
    private Point coordonne;
    private double rayon;
    private Cercle cercle;
    //private FormContainer formContainer;
    private Color color;


    /**
     * Constructeur de la classe CreationCercle
     * @param coordonne
     * @param rayon
     * @param formContainer
     * @param cercle
     */
    public CreationCercle(Point coordonne, double rayon, Cercle cercle){
        this.coordonne = coordonne;
        this.rayon = rayon;
        //this.formContainer = formContainer;
        this.cercle = cercle;
    }

    /**
     * Méthode pour créer un cercle
     */
    @Override
    public void operate() {
        // On va créer un cercle
        this.cercle = new Cercle(this.coordonne, this.rayon, this.color);
        //this.formContainer.ajoutForm(this.cercle);
    }

    /**
     * Méthode pour compenser la création d'un cercle
     */
    @Override
    public void compensate() {
        //this.formContainer.suppressionForm(this.cercle);
        // On détruit le cercle créé
        this.cercle.finalize();
    }
}