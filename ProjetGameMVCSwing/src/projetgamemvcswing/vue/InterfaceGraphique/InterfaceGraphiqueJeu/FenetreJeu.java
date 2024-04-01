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
 *
 * @author Islem
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
    
    public FenetreJeu(SolverStrategy solverStrategy) {
        
        this.solverStrategy = solverStrategy;
        // Configuration de JFrame Dessin
        setTitle("Jeu");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(new ImageIcon("images/cont.png").getImage());
        
        // Initialiser les composants de l'interface utilisateur
        initUIComponents(this, gameScore, container);
        
        
        this.formesGenerees = new RandomShapeGenerator().generateFormes( panelJeu, 4);
        // Créer une instance de MenuBarJeu
        menuBarJeu = new MenuBarJeu(this);

        // Ajouter le menuBarJeu
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(menuBarJeu);
        
        
        panelScore = new PanelScore(gameScore, ordinateurScore);
        int largeurPanelFormes = (int) (getWidth() * 0.15);
        this.panelFormes.setPreferredSize(new Dimension(largeurPanelFormes, getHeight()));
        getContentPane().add(panelScore, BorderLayout.SOUTH);

        // Créer une instance de BarreOutilsJeu
        barJeu = new BarreOutilsJeu(this, this.panelJeu);

        // Ajouter la barre d'outils personnalisée
        add(barJeu, BorderLayout.WEST);
        
        // Ajouter l'interface de dessin au centre
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        getContentPane().add(this.panelFormes, BorderLayout.EAST);

        // Positionner la fenêtre au centre de l'écran
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.calculerSolutionOrdinateurAvecDelai(); //on commence a calculer la soluce en cachette sans que l'utilisateur ne le sache !
    }
    
    /**
     * Initialise les composants de l'interface utilisateur.
     */
    private void initUIComponents(JFrame frame, GameScore gameScore, FormContainer formContainer) {
        // Créer une instance de l'interface de dessin
        this.panelJeu = new PanelJeu(frame, gameScore, ordinateurScore, this.formesGenerees, formContainer);
        this.panelFormes = new PanelFormes(formContainer, this.panelJeu);
        
        // Ajuster la préférence de taille du panelFormes ici si nécessaire
        int largeurPanelFormes = (int) (getWidth() * 0.15);
        panelFormes.setPreferredSize(new Dimension(largeurPanelFormes, getHeight()));

        // Initialiser et configurer le JSplitPane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelJeu, panelFormes);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.85); // Ajuster selon la proportion désirée

        // Assurer que le JSplitPane respecte la taille préférée de panelFormes
        splitPane.setResizeWeight(1.0);
        
        
        
    }
    
    private void calculerSolutionOrdinateur() {
        SwingWorker<List<Figure>, Void> worker = new SwingWorker<List<Figure>, Void>() {
            
            private RandomSolve solver; 
            
            @Override
            protected List<Figure> doInBackground() throws Exception {
                // Votre logique de solveur exécutée en arrière-plan
                //this.solver = new RandomSolve(formesGenerees, ordinateurScore, new ArrayList<>(), panelJeu.getWidth(), panelJeu.getHeight());
                this.solver = solverStrategy.getSolver();
                return solver.getSoluce();
            }

            @Override
            protected void done() {
                try {
                    // Mise à jour de l'interface utilisateur avec le résultat du solveur
                    List<Figure> solutions = get();
                    solutionsOrdinateur = solutions; // Stockez les solutions pour dessin ultérieur
                    ordinateurScore.setAireCouverte(solver.getScore().getAireCouverte()); // Calculez et mettez à jour le score basé sur les solutions
                    panelJeu.setSolutionsOrdinateur(solutions);
                    panelJeu.updateOrdinateurScore(ordinateurScore);
                    panelJeu.modelUpdated(this);// Redessinez le PanelJeu pour montrer les solutions
                    panelJeu.setCurrentState(new FinGame());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute(); // Démarrer le worker
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
    
    SolverStrategy getSolverStrategy() {
        return this.solverStrategy;
    }


}