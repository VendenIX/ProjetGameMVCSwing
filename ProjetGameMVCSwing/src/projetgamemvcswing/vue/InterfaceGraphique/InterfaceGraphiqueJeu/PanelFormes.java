package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import projetgamemvcswing.adapter.FormContainerAdapter;
import projetgamemvcswing.modele.geometry.FormContainer;

/**
 * Classe qui permet de créer une zone d'information sur le coté du jeu
 * @author romain
 */
public class PanelFormes extends JPanel {

    private JTable tableFormes;
    
    /**
     * Créer un panel de formes sur le coté pour afficher des informations
     * @param formContainer
     * @param panel 
     */
    public PanelFormes(FormContainer formContainer, PanelJeu panel) {
        FormContainerAdapter adapter = new FormContainerAdapter(formContainer, panel);
        tableFormes = new JTable(adapter);
        
        setLayout(new BorderLayout());
        add(new JScrollPane(tableFormes), BorderLayout.CENTER);
        formContainer.ajoutEcouteur(adapter);
    }
}
