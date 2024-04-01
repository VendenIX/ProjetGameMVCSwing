package projetgamemvcswing.adapter;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import projetgamemvcswing.modele.geometry.*;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu.PanelJeu;
/**
 *
 * @author 21907062@campus
 */
public class FormContainerAdapter extends AbstractTableModel {
    
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
        if (rowIndex < container.getNbForms()) {
            DisplayableProperties figure = (DisplayableProperties) container.getFormList().get(rowIndex);
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
    
}
