package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;


public class SuppressionForme implements OperationCommand {
    
    /**
    * Attributs de la classe SuppressionForme
    */
    private Figure figure;
    private FormContainer formContainer;

    /**
    * Constructeur de la classe SuppressionForme
    * @param figure La figure à supprimer.
    * @param formContainer Le conteneur de figures.
    */
    public SuppressionForme(Figure figure, FormContainer formContainer) {
        this.figure = figure;
        this.formContainer = formContainer;
    }

    /**
    * Méthode pour supprimer une figure.
    */
    @Override
    public void operate() {
        this.formContainer.suppressionForm(this.figure);
    }

    /**
    * Méthode pour compenser la suppression d'une figure (essentiellement l'ajouter à nouveau).
    */
    @Override
    public void compensate() {
        this.formContainer.ajoutForm(this.figure);
    }
}
