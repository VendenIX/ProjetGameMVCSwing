package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * Classe pour la suppression d'un cercle avec la commande de suppression
 * 
 * @see OperationCommand
 * @see SuppressionCercle
 * 
 * @version 1.0
 * @since 2024-02-06
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">Amirath Fara OROU-GUIDOU</a>
 *
 */

public class SuppressionCercle implements OperationCommand{
    
        /**
        * Attributs de la classe SuppressionCercle
        */
        private Cercle cercle;
        private FormContainer formContainer;
    
        /**
        * Constructeur de la classe SuppressionCercle
        * @param cercle
        * @param formContainer
        */
        public SuppressionCercle(Cercle cercle, FormContainer formContainer){
            this.cercle = cercle;
            this.formContainer = formContainer;
        }
    
        /**
        * Méthode pour supprimer un cercle
        */
        @Override
        public void operate() {
            // On va supprimer un cercle
            this.formContainer.suppressionForm(this.cercle);
        }
    
        /**
        * Méthode pour compenser la suppression d'un cercle
        */
        @Override
        public void compensate() {
            // On va ajouter un cercle
            this.formContainer.ajoutForm(this.cercle);
        }
}