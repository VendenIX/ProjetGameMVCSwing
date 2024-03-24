package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Rectangle;

/**
 * Classe pour la suppression d'un rectangle avec la commande de suppression
 * 
 * @see OperationCommand
 * @see SuppressionRectangle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 */



public class SuppressionRectangle implements OperationCommand{

    /**
     * Attributs de la classe SuppressionRectangle
     */
    private Rectangle rectangle;
    private FormContainer formContainer;

    /**
     * Constructeur de la classe SuppressionRectangle
     * @param rectangle
     * @param formContainer
     */
    public SuppressionRectangle(Rectangle rectangle, FormContainer formContainer){
        this.rectangle = rectangle;
        this.formContainer = formContainer;
    }

    /**
     * Méthode pour supprimer un rectangle
     */
    @Override
    public void operate() {
        // On va supprimer un rectangle
        this.formContainer.suppressionForm(this.rectangle);
    }

    /**
     * Méthode pour compenser la suppression d'un rectangle
     */
    @Override
    public void compensate() {
        // On va ajouter un rectangle
        this.formContainer.ajoutForm(this.rectangle);
    }
}