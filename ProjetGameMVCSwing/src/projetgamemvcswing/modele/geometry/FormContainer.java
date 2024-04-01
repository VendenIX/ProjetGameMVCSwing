package projetgamemvcswing.modele.geometry;

import java.util.ArrayList;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

public class FormContainer extends AbstractModeleEcoutable {

    // Liste des formes
    private final ArrayList<Figure> formList;

    /**
     * Constructeur par défaut.
     */
    public FormContainer() {
        this.formList = new ArrayList<>();
    }

    /**
     * Obtient la liste des formes.
     *
     * @return La liste des formes.
     */
    public ArrayList<Figure> getFormList() {
        return formList;
    }

    /**
     * Obtient le nombre de formes dans le conteneur.
     *
     * @return Le nombre de formes dans le conteneur.
     */
    public int getNbForms() {
        return this.formList.size();
    }

    /**
     * Calcule la surface totale de toutes les formes dans le conteneur.
     *
     * @return La surface totale de toutes les formes.
     */
    public double getTotalArea() {
        double totalArea = 0.0;
        for (Figure figure : formList) {
            totalArea += figure.getSurface();
        }
        return totalArea;
    }

    /**
     * Ajoute une forme à la liste des formes du conteneur.
     *
     * @param f La forme à ajouter.
     */
    public void ajoutForm(Figure f) {
        this.formList.add(f);
        fireChange();
    }

    /**
     * Supprime une forme de la liste des formes du conteneur.
     *
     * @param f La forme à supprimer.
     */
    public void suppressionForm(Figure f) {
        this.formList.remove(f);
        fireChange();
    }

    /**
     * Représentation sous forme de chaîne de caractères du conteneur de formes.
     *
     * @return Une chaîne de caractères représentant le conteneur de formes.
     */
    @Override
    public String toString() {
        String result = "FormContainer contient les formes suivantes :\n";
        for (Figure f : formList) {
            result += f.toString() + "\n";
        }
        return result;
    }
}
