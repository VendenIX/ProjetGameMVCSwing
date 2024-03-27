package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.FormContainer;

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
    private final Cercle cercle;
    private final FormContainer formContainer;


    /**
     * Constructeur de la classe CreationCercle
     * @param formContainer
     * @param cercle
     */
    public CreationCercle(Cercle cercle, FormContainer formContainer){
        this.cercle = cercle;
        this.formContainer = formContainer;
    }

    /**
     * Méthode pour créer un cercle
     */
    @Override
    public void operate() {
        // On va créer un cercle
        this.formContainer.ajoutForm(this.cercle);
    }

    /**
     * Méthode pour compenser la création d'un cercle
     */
    @Override
    public void compensate() {
        this.formContainer.suppressionForm(this.cercle);
        // On détruit le cercle créé
        //this.cercle.finalize();
    }
}