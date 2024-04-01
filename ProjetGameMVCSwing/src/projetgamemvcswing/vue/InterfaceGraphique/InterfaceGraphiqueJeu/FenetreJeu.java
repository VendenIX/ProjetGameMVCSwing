package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.RandomShapeGenerator;
import projetgamemvcswing.controller.State.JeuState.FinGame;
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
        SwingWorker<List<Figure>, Void> worker = new SwingWorker<List<Figure>, Void>() {
            
            private RandomSolve solver; 
            
            @Override
            protected List<Figure> doInBackground() throws Exception {
                // Votre logique de solveur exécutée en arrière-plan
                this.solver = new RandomSolve(formesGenerees, ordinateurScore, new ArrayList<>(), panelJeu.getWidth(), panelJeu.getHeight());
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


}
