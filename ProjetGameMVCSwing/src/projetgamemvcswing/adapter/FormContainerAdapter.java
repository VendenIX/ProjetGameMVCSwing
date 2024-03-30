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
    public Object getValueAt(int rowIndex, int columnIndex){
        Figure figure = container.getFormList().get(rowIndex);
        
        if (rowIndex == getRowCount() - 1) { // Si c'est la dernière ligne
            // Afficher le total des formes et leur surface totale
            switch (columnIndex) {
                case 0:
                    return "Total";
                case 1:
                    return container.getNbForms();
                case 2:
                    return container.getTotalArea();
                default:
                    return null;
            }
        }
        
        if (figure instanceof Cercle) {
            Cercle cercle = (Cercle) figure;
            switch (columnIndex) {
                case 0:
                    return cercle.getName();
                case 1:
                    return cercle.getCentre();
                case 2:
                    return cercle.getRayon();
                default:
                    return null;
            }
        } 
        else if (figure instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) figure;
            switch (columnIndex) {
                case 0:
                    return rectangle.getName();
                case 1:
                    return rectangle.getHauteur();
                case 2:
                    return rectangle.getLargeur();
                default:
                    return null;
            }
        }
        else {
            return null;
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
