package projetgamemvcswing.adapter;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import projetgamemvcswing.controller.Observer.EcouteurModele;
import projetgamemvcswing.modele.geometry.*;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;
/**
 *
 * @author 21907062@campus
 */
public class FormContainerAdapter extends AbstractTableModel implements EcouteurModele {
    
    private final FormContainer container;
    private PanelJeu panel;
    
    public FormContainerAdapter(FormContainer container, PanelJeu panel){
        this.container = container;
        this.panel = panel;
    }
    
    @Override
    public int getRowCount(){
        return this.container.getNbForms();
    }
    
    @Override
    public int getColumnCount(){
        return 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Figure> formesJoueur = container.getFormList().stream()
                                         .filter(f -> f.getCouleur().equals(Color.CYAN)) 
                                         .collect(Collectors.toList());
        
        if (rowIndex < formesJoueur.size()) {
            DisplayableProperties figure = (DisplayableProperties) container.getFormList().get(rowIndex + 4); // +4 car les 4 premieres formes c'est les obstacles
            switch (columnIndex) {
                case 0: // Nom de la figure
                    return figure.getName();
                case 1: // Aire de la figure
                    // On suppose que l'aire est toujours le deuxième élément des propriétés, ajustez selon votre implémentation
                    return (int) ((double) figure.getProperties().get(2) / this.panel.getSuperficie() *  100) + "%";
                default:
                    return "";
            }
        } else {
            return "";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Nom";
            case 1:
                return "Aire";
            default:
                return "";
        }
    }

    @Override
    public void modelUpdated(Object source) {
        fireTableDataChanged(); //c'est une methode de abstractTableModel donc ca update bien
    }
    
}
