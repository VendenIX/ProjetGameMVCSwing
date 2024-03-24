package projetgamemvcswing.modele.geometry;

import java.util.ArrayList;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;
import projetgamemvcswing.controller.Observer.EcouteurModele;
import projetgamemvcswing.controller.Observer.ModeleEcoutable;

public class FormContainer extends AbstractModeleEcoutable implements EcouteurModele{

    private ArrayList<Figure> formList;
    private ArrayList<ModeleEcoutable> listListeners= new ArrayList<>();

    public FormContainer(){

        this.formList = new ArrayList<>();

    }

    public ArrayList<Figure> getFormList() {
        return formList;
    }

    @Override
    public void modelUpdated(Object source) {
        fireChange();
    }

    /**
     * Ajout d'un écouteur à la liste des écouteurs
     * @param l 
     */
    public void ajoutContainerListeners(ModeleEcoutable l) {
        this.listListeners.add(l);
    }

    /**
     * Suppression d'un écouteur de la liste des écouteurs
     * @param l 
     */
    public void supprimeContainerListeners(ModeleEcoutable l) {
        this.listListeners.remove(l);
    }


    /**
     * Ajout d'une forme à la liste des formes
     * @param f 
     */
    public void ajoutForm(Figure f ) {
        if(formList.size()<= 53){
            this.formList.add(f);
            ajoutEcouteur(this);
        }
    }

    /**
     * Suppression d'une forme de la liste des formes
     * @param f 
     */
    public void suppressionForm(Figure f) {
        this.formList.remove(f);
        retraitEcouteur(this);
    }


    
}
