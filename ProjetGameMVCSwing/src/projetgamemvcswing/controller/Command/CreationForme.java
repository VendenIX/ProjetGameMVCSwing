package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;

public class CreationForme implements OperationCommand {
    private final Figure forme;
    private final FormContainer container;

    public CreationForme(Figure forme, FormContainer container) {
        this.forme = forme;
        this.container = container;
    }

    @Override
    public void operate() {
        container.ajoutForm(forme);
    }

    @Override
    public void compensate() {
        container.suppressionForm(forme);
    }
}
