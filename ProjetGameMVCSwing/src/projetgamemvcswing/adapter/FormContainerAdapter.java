package projetgamemvcswing.adapter;

import javax.swing.table.AbstractTableModel;
import projetgamemvcswing.modele.geometry.*;
/**
 *
 * @author 21907062@campus
 */
public class FormContainerAdapter extends AbstractTableModel {
    
    private final FormContainer container;
    
    public FormContainerAdapter(FormContainer container){
        this.container = container;
    }
    
    @Override
    public int getRowCount(){
        return this.container.getNbForms()+1;
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < container.getNbForms()) {
            DisplayableProperties figure = (DisplayableProperties) container.getFormList().get(rowIndex);
            switch (columnIndex) {
                case 0: return figure.getName();
                case 1: return figure.getProperties().get(0); // Assumer que chaque figure a au moins une propriété
                case 2: return figure.getProperties().size() > 1 ? figure.getProperties().get(1) : "";
                default: return "";
            }
        } else {
            // Gérer la dernière ligne pour le total, si nécessaire
            return "Total";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Nom";
            case 1:
                return "Centre / Hauteur / Nombre";
            case 2:
                return "Rayon / Largeur / Surface";
            default:
                return "";
        }
    }
    
}
