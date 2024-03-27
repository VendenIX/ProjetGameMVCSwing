package projetgamemvcswing.controller.Command;

import java.awt.Color;

import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * Classe pour la création d'un rectangle avec la commande de création 
 * 
 * @see OperationCommand
 * @see CreationRectangle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 * 
 */
public class CreationRectangle implements OperationCommand{


    /**
     * Attibuts de la classe CreationRectangle
     */
    private double x;
    private double y;
    private Rectangle rectangle;
    private FormContainer formContainer;
    private double largeur;
    private double hauteur;
    private Color color;

    /**
     * Constructeur de la classe CreationRectangle
     * @param x
     * @param y
     * @param largeur
     * @param hauteur
     * @param formContainer
     * @param rectangle
     */
    public CreationRectangle(double x, double y, double largeur, double hauteur, Rectangle rectangle){
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
        //this.formContainer = formContainer;
        this.rectangle = rectangle;
    }

    /**
     * Méthode pour créer un rectangle
     */
    @Override
    public void operate() {
        // On va créer un rectangle 
        this.rectangle = new Rectangle(this.x, this.y, this.largeur, this.hauteur, this.color);
        //this.formContainer.ajoutForm(this.rectangle);
        
    }

    /**
     * Méthode pour compenser la création d'un rectangle
     */
    @Override
    public void compensate() {
        //this.formContainer.suppressionForm(this.rectangle);
        // On détruit le rectangle créé
        this.rectangle.finalize();
    }

}