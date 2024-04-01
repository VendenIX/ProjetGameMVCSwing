package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import projetgamemvcswing.adapter.FormContainerAdapter;
import projetgamemvcswing.modele.geometry.FormContainer;

public class PanelFormes extends JPanel {

    private JTable tableFormes;
    
    public PanelFormes(FormContainer formContainer, PanelJeu panel) {
        FormContainerAdapter adapter = new FormContainerAdapter(formContainer, panel);
        tableFormes = new JTable(adapter);
        
        setLayout(new BorderLayout());
        add(new JScrollPane(tableFormes), BorderLayout.CENTER);
        formContainer.ajoutEcouteur(adapter);
    }
}
