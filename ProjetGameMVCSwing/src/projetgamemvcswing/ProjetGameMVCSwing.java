package projetgamemvcswing;

import projetgamemvcswing.vue.InterfaceGraphique.fenetreContact;
import projetgamemvcswing.vue.InterfaceGraphique.fenetreDessin;
import projetgamemvcswing.vue.InterfaceGraphique.fenetreInformation;
import projetgamemvcswing.vue.InterfaceGraphique.menuChoix;


public class ProjetGameMVCSwing{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Yououh ça compile !"); //sinon allez modifier le jdk via files dans nbproject > project.properties jdksource 17 => 11 par exemple
        new menuChoix();
        //new fenetreContact();
        //new fenetreDessin();
        //new fenetreJeu();
    }
    
}
