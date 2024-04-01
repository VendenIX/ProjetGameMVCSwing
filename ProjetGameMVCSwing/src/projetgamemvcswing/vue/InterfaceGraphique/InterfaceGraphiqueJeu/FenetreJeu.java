package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.RandomShapeGenerator;
import projetgamemvcswing.controller.State.JeuState.FinGame;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.solveurs.RandomSolve;
import projetgamemvcswing.strategy.SolverStrategy;


/**
 * classe qui permet de créer une JFrame pour le jeu
 * @author romain
 */
public class FenetreJeu extends JFrame {
    
     // Variables de classe
    private PanelJeu panelJeu;
    private MenuBarJeu menuBarJeu;
    private BarreOutilsJeu barJeu;
    private PanelScore panelScore;
    
    private final FormContainer container = new FormContainer();
    private final GameScore gameScore = new GameScore();
    private final GameScore ordinateurScore = new GameScore();
    private List<Figure> formesGenerees;
    private List<Figure> solutionsOrdinateur;
    private PanelFormes panelFormes;
    private JSplitPane splitPane;
    private SolverStrategy solverStrategy;
    private Dimension size;
    
    /**
     * Permet de créer une fenetre de jeu qui prend en entrée une strategy de solver
     * @param solverStrategy 
     */
    public FenetreJeu(SolverStrategy solverStrategy) {
        
        this.solverStrategy = solverStrategy;
        System.out.println("bien avant");
        System.out.println(this.solverStrategy);
        // configuration de JFrame Dessin
        setTitle("Jeu");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/cont.png").getImage());
        
        // initialiser les composants de l'interface utilisateur
        initUIComponents(this, gameScore, container);
        
        
        this.formesGenerees = new RandomShapeGenerator().generateFormes( panelJeu, 4);
        // créer une instance de MenuBarJeu
        menuBarJeu = new MenuBarJeu(this);

        // ajouter le menuBarJeu
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarJeu);
        
        
        panelScore = new PanelScore(gameScore, ordinateurScore);
        int largeurPanelFormes = (int) (getWidth() * 0.15);
        this.panelFormes.setPreferredSize(new Dimension(largeurPanelFormes, getHeight()));
        getContentPane().add(panelScore, BorderLayout.SOUTH);

        // créer une instance de BarreOutilsJeu
        barJeu = new BarreOutilsJeu(this, this.panelJeu);

        // ajouter la barre d'outils personnalisée
        add(barJeu, BorderLayout.WEST);
        
        // ajouter l'interface de dessin au centre
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        getContentPane().add(this.panelFormes, BorderLayout.EAST);

        // positionner la fenêtre au centre de l'écran
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.calculerSolutionOrdinateurAvecDelai(); //on commence a calculer la soluce en cachette sans que l'utilisateur ne le sache !
    }
    
    /**
     * Initialise les composants de l'interface utilisateur.
     * @param frame
     * @param gameScore
     * @param formContainer 
     */
    private void initUIComponents(JFrame frame, GameScore gameScore, FormContainer formContainer) {
        // créer une instance de l'interface de dessin
        this.size = frame.getSize();
        this.panelJeu = new PanelJeu(frame, gameScore, ordinateurScore, this.formesGenerees, formContainer, size);
        this.panelFormes = new PanelFormes(formContainer, this.panelJeu);
        
        // ajuster la préférence de taille du panelFormes ici si nécessaire
        int largeurPanelFormes = (int) (getWidth() * 0.15);
        panelFormes.setPreferredSize(new Dimension(largeurPanelFormes, getHeight()));

        // initialiser et configurer le JSplitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelJeu, panelFormes);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.85); // ajuster selon la proportion désirée

        // s'assurer que le JSplitPane respecte la taille préférée de panelFormes
        splitPane.setResizeWeight(1.0);
        
        
        
    }
    
    /**
     * Permet de calculer la solution de l'ordinateur
     */
    private void calculerSolutionOrdinateur() {


        try {
            this.solverStrategy.solve(formesGenerees, gameScore, formesGenerees, (int) (this.size.width * 0.85), this.size.height);
            List<Figure> solutions = this.solverStrategy.getSolver().getSoluce();

            // mise à jour de l'interface utilisateur avec le résultat du solveur
            solutionsOrdinateur = solutions; // stocke les solutions pour dessin ultérieur
            ordinateurScore.setAireCouverte(this.solverStrategy.getSolver().getScore().getAireCouverte()); // calcule et met à jour le score basé sur les solutions
            System.out.println("voici les soluces : " + solutions);
            panelJeu.setSolutionsOrdinateur(solutions);
            panelJeu.updateOrdinateurScore(ordinateurScore);
            panelJeu.modelUpdated(this);// redessine le PanelJeu pour montrer les solutions

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de calculer la solution dans un délai de 10 secondes pour l'utilisateur
     */
    private void calculerSolutionOrdinateurAvecDelai() {
    SwingWorker<Void, Void> worker = new SwingWorker<>() {
        
        /**
         * Classe anonyme pour gérer le calcul en parrallèle, un peu technique ahah a voir si c'est efficace
         */
        @Override
        protected Void doInBackground() throws Exception {
            try {
                Thread.sleep(10000); // attend 10 secondes IMPORTANT a def plus tard en param classe
                calculerSolutionOrdinateur(); // lance le calcul après le délai
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // gestion de l'interruption du thread
                e.printStackTrace();
            }
            return null;
        }
    };
    
    worker.execute();
}

    /**
     * Permet de récuperer le solverstrategy
     * @return 
     */
    SolverStrategy getSolverStrategy() {
        return this.solverStrategy;
    }


}