package projetgamemvcswing.modele.geometry;

import java.util.ArrayList;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

public class FormContainer extends AbstractModeleEcoutable {

    private final ArrayList<Figure> formList;

    public FormContainer(){

        this.formList = new ArrayList<>();

    }

    public ArrayList<Figure> getFormList() {
        return formList;
    }
    
    public int getNbForms() {
        return this.formList.size();
    }
    
    public double getTotalArea() {
        double totalArea = 0.0;
        for (Figure figure : formList) {
            totalArea += figure.getSurface();
        }
        return totalArea;
    }

    /**
     * Ajout d'une forme à la liste des formes
     * @param f 
     */
    public void ajoutForm(Figure f) {
        this.formList.add(f);
        fireChange(); 
    }

    /**
     * Suppression d'une forme de la liste des formes
     * @param f 
     */
    public void suppressionForm(Figure f) {
        this.formList.remove(f);
        fireChange(); 
    }
    
    @Override
    public String toString() {
        String result = "FormContainer contient les formes suivantes :\n";
        for (Figure f : formList) {
            result += f.toString() + "\n";
        }
        return result;
    }
    
}
