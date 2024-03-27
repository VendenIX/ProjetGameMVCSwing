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
    private Rectangle rectangle;
    private FormContainer formContainer;

    /**
     * Constructeur de la classe CreationRectangle
     * @param rectangle
     * @param formContainer
     */

//    public CreationRectangle(double x, double y, double largeur, double hauteur, Color couleur){
//        this.x = x;
//       this.y = y;
//        this.largeur = largeur;
//        this.hauteur = hauteur;
//        this.couleur = couleur;
//    }
    
    public CreationRectangle(Rectangle rectangle, FormContainer formContainer){
        this.rectangle = rectangle;
        this.formContainer = formContainer;
    }

    /**
     * Méthode pour créer un rectangle
     */
    @Override
    public void operate() {
        // On va créer un rectangle 
        this.formContainer.ajoutForm(this.rectangle);
        
    }

    /**
     * Méthode pour compenser la création d'un rectangle
     */
    @Override
    public void compensate() {
        this.formContainer.suppressionForm(this.rectangle);
        //this.rectangle.finalize();
    }

}