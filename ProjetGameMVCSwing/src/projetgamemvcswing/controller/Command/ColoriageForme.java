package projetgamemvcswing.controller.Command;

import java.awt.Color;
import projetgamemvcswing.modele.geometry.Figure;

/**
 * Commande pour colorier une forme géométrique.
 */
public class ColoriageForme implements OperationCommand {

    private Figure forme;
    private Color couleurInitiale;
    private Color nouvelleCouleur;
    private boolean needsBorderInitiale;
    private boolean nouvelleNeedsBorder;

    /**
     * Constructeur pour la commande de coloriage.
     * @param forme La forme à colorier.
     * @param nouvelleCouleur La nouvelle couleur à appliquer à la forme.
     */
    public ColoriageForme(Figure forme, Color nouvelleCouleur, boolean nouvelleNeedsBorder) {
        this.forme = forme;
        this.nouvelleCouleur = nouvelleCouleur;
        this.couleurInitiale = forme.getCouleur(); // Sauvegarde de la couleur initiale pour l'undo.
        this.needsBorderInitiale = forme.needsBorder(); // Sauvegarde de l'état initial de needsBorder.
        this.nouvelleNeedsBorder = nouvelleNeedsBorder; 
    }

    @Override
    public void operate() {
        forme.setCouleur(nouvelleCouleur); // Applique la nouvelle couleur.
        forme.setNeedBorder(nouvelleNeedsBorder);
    }

    @Override
    public void compensate() {
        forme.setCouleur(couleurInitiale); // Rétablit la couleur initiale.
        forme.setNeedBorder(needsBorderInitiale);
    }
}
