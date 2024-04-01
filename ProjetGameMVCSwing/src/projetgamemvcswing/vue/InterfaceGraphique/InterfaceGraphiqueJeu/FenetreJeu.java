package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.RandomShapeGenerator;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.solveurs.RandomSolve;


/**
 *
 * @author Islem
 */
public class FenetreJeu extends JFrame {
    
     // Variables de classe
    private PanelJeu panelJeu;
    private MenuBarJeu menuBarJeu;
    private BarreOutilsJeu barJeu;
    private PanelScore panelScore;
    
    private final GameScore gameScore = new GameScore();
    private final GameScore ordinateurScore = new GameScore();
    private List<Figure> formesGenerees;
    private List<Figure> solutionsOrdinateur;
    
    
    public FenetreJeu() {
        // Configuration de JFrame Dessin
        setTitle("Jeu");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/cont.png").getImage());
        
        // Initialiser les composants de l'interface utilisateur
        initUIComponents(this, gameScore);
        
        
        this.formesGenerees = new RandomShapeGenerator().generateFormes( panelJeu, 4);
        // Créer une instance de MenuBarJeu
        menuBarJeu = new MenuBarJeu(this);

        // Ajouter le menuBarJeu
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarJeu);
        
        
        panelScore = new PanelScore(gameScore, ordinateurScore);
        getContentPane().add(panelScore, BorderLayout.SOUTH);

        // Créer une instance de BarreOutilsJeu
        barJeu = new BarreOutilsJeu(this, this.panelJeu);

        // Ajouter la barre d'outils personnalisée
        add(barJeu, BorderLayout.WEST);
        
        // Ajouter l'interface de dessin au centre
        getContentPane().add(panelJeu, BorderLayout.CENTER);

        // Positionner la fenêtre au centre de l'écran
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.calculerSolutionOrdinateurAvecDelai(); //on commence a calculer la soluce en cachette sans que l'utilisateur ne le sache !
    }
    
    /**
     * Initialise les composants de l'interface utilisateur.
     */
    private void initUIComponents(JFrame frame, GameScore gameScore) {
        // Créer une instance de l'interface de dessin
        panelJeu = new PanelJeu(frame, gameScore, ordinateurScore, this.formesGenerees);
        
    }
    
    private void calculerSolutionOrdinateur() {

        RandomSolve solver = new RandomSolve(this.formesGenerees, ordinateurScore, new ArrayList<>(),panelJeu.getWidth(), panelJeu.getHeight());
        List<Figure> solutions = solver.getSoluce();
        this.solutionsOrdinateur = solutions;
        ordinateurScore.setAireCouverte(solver.getScore().getAireCouverte()); // Supposons cette méthode existante dans RandomSolve
        panelJeu.setSolutionsOrdinateur(solutions);
        panelJeu.updateOrdinateurScore(ordinateurScore);
    }
    
    private void calculerSolutionOrdinateurAvecDelai() {
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Attend 3 secondes
                System.out.println("Lancement ! ");
                calculerSolutionOrdinateur(); // Lance le calcul après le délai
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Gestion de l'interruption du thread
                e.printStackTrace();
            }
        }).start();
    }


}
