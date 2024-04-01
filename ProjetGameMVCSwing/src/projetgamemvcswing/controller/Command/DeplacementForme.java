package projetgamemvcswing.controller.Command;

import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * Classe pour le déplacement d'une figure avec la commande de déplacement.
 */
public class DeplacementForme implements OperationCommand {
    private final Figure figure;
    private final double deltaX;
    private final double deltaY;

    /**
     * Constructeur pour créer une commande de déplacement de figure.
     *
     * @param figure    La figure à déplacer.
     * @param deltaX    Le déplacement sur l'axe X.
     * @param deltaY    Le déplacement sur l'axe Y.
     */
    public DeplacementForme(Figure figure, double deltaX, double deltaY) {
        this.figure = figure;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Déplace la figure.
     */
    @Override
    public void operate() {
        figure.translater(deltaX, deltaY);
    }

    /**
     * Compense le déplacement de la figure (annule le déplacement).
     */
    @Override
    public void compensate() {
        figure.translater(-deltaX, -deltaY);
    }
}
