
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import projetgamemvcswing.controller.GameScore;
import projetgamemvcswing.controller.Observer.EcouteurModele;
import projetgamemvcswing.controller.RandomShapeGenerator;
import projetgamemvcswing.controller.ShapeDrawer;
import projetgamemvcswing.controller.ShapeFiller;
import projetgamemvcswing.controller.State.JeuState.FinGame;
import projetgamemvcswing.controller.State.JeuState.PlayDefaultState;
import projetgamemvcswing.controller.State.JeuState.JeuState;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.solveurs.RandomSolve;

/**
 * Classe qui permet de créer un panel pour le jeu pour afficher les formes
 * @author romain
 */
public class PanelJeu extends JPanel implements EcouteurModele {

    // container de type FormContainer qui stocke toutes les figures
    private final FormContainer container;
    
    // classe contenant des methodes d'affichage de formes 
    private final ShapeDrawer shapeDrawer = new ShapeDrawer();
    private final ShapeFiller shapeFiller = new ShapeFiller();
    
    private List<Figure> solutionsOrdinateur = null;

   // variable pour stocker la figure en cours de dessin
    private Figure figureEnCoursDeDessin;
    
    // initialisation d'état par defaut
    private JeuState currentState = new PlayDefaultState();
    
    // position précédente de la souris 
    private double lastMouseX;
    private double lastMouseY;
    
    private final GameScore gameScore;
    private final GameScore ordinateurScore;
    private List<Figure> formesGenerees;
    
    private final Dimension size;

    /**
     * Classe qui créer un panel de jeu pour afficher les formes du jeu
     * @param frame
     * @param gameScore
     * @param ordinateurScore
     * @param formesGenerees
     * @param formContainer
     * @param size 
     */
    public PanelJeu(JFrame frame, GameScore gameScore, GameScore ordinateurScore, List<Figure> formesGenerees, FormContainer formContainer, Dimension size) {
        
        this.size = size;
        
        if (gameScore == null) {
            throw new IllegalArgumentException("gameScore ne peut pas être null");
        }
        this.gameScore = gameScore;
        this.ordinateurScore = ordinateurScore;
        // set de fond blanc
        setBackground(Color.WHITE);
        setSize(this.size);
        
        
        //met les formes et on les recup dans une liste en meme temps pour le solver
        this.formesGenerees = formesGenerees;
        this.container = formContainer;

        
        // ajouter un écouteur pour les événements de la souris
        addMouseListener(new MouseAdapter() {
            // quand il y a un clique de souris
            @Override
            public void mousePressed(MouseEvent e) {
                currentState.handleMousePressed(PanelJeu.this, e);
            }

            // quand il y a une relache de souris
            @Override
            public void mouseReleased(MouseEvent e) {
                currentState.handleMouseReleased(PanelJeu.this, e, gameScore);
            }
        });

        // ajouter un écouteur pour les mouvements de la souris
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentState.handleMouseDragged(PanelJeu.this, e);
            }
        });
        
        
    }
    
    /**
     * Met a jour le model
     * @param source 
     */
    @Override
    public void modelUpdated(Object source) {
        double airePanel = this.getWidth() * this.getHeight();
        double pourcentageAireCouverte = gameScore.calculerPourcentageAireCouverte(airePanel);
        repaint();
    }

    /**
     * Met a jour le score du joueur
     */
    private void updateGameScore() {
        double aireTotalePanel = getWidth() * getHeight();
        double aireCouverteBleue = container.calculerAireCouverteParCouleur(Color.BLUE); // Remplacez Color.BLUE par la couleur exacte utilisée pour les formes en mode jeu.
        gameScore.addAireCouverte(aireCouverteBleue);
    }
    
    /**
     * Met a jour le score de l'ordinateur
     * @param gameScore 
     */
    void updateOrdinateurScore(GameScore gameScore) {
        double aireTotalePanel = getWidth() * getHeight();
        double aireCouverteBleue = gameScore.getAireCouverte();
        ordinateurScore.addAireCouverte(aireCouverteBleue);
    }
    
    

    
    

   // setter pour définir la figure en cours de dessin
    public void setFigureEnCoursDeDessin(Figure figure) {this.figureEnCoursDeDessin = figure;}

    // getter pour obtenir la figure en cours de dessin
    public Figure getFigureEnCoursDeDessin() {return this.figureEnCoursDeDessin;}

    // setter pour définir l'état actuel
    public void setCurrentState(JeuState state) {this.currentState = state;}

    // getter pour obtenir la liste des figures
    public List<Figure> getFigures() {return this.container.getFormList();}

    // getter pour obtenir la position X précédente de la souris
    public double getLastMouseX() {return lastMouseX;}

    // setter pour définir la position X précédente de la souris
    public void setLastMouseX(double lastMouseX) {this.lastMouseX = lastMouseX;}

    // getter pour obtenir la position Y précédente de la souris
    public double getLastMouseY() { return lastMouseY;}

    // setter pour définir la position Y précédente de la souris
    public void setLastMouseY(double lastMouseY) {this.lastMouseY = lastMouseY;}
    
    /**
     * Donne la superficie du panel
     * @return 
     */
    public double getSuperficie() {
        return this.getWidth() * this.getHeight();
    }

    /**
    * Redéfinition de la méthode paintComponent pour dessiner et afficher les figures.
    * 
    * @param g L'objet Graphics pour dessiner.
    */
    @Override
    protected void paintComponent(Graphics g) {
        // appel à la méthode paintComponent de la classe parent
        // pour effectuer les opérations de base
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        
        // dessiner les contours des formes existantes
        shapeDrawer.drawFigures(g, this.getFigures());  
        
        // Dessiner la figure en cours de dessin
        if (figureEnCoursDeDessin != null) {
            shapeDrawer.drawFigure(g, figureEnCoursDeDessin);
            shapeFiller.drawFilledFigure(g2d, figureEnCoursDeDessin);
        }

        // dessiner la solution de l'ordinateur si on est en FinGame
        if (currentState instanceof FinGame && solutionsOrdinateur != null) {
            System.out.println("Dessinner la solution de l'ordinateur " + solutionsOrdinateur);
            for (Figure f : solutionsOrdinateur) {
                shapeDrawer.drawFigure(g, f);
                shapeFiller.drawFilledFigure(g2d, f);
            }
        }
        
    }
    
    /**
    * Cette méthode réinitialise la liste des figures à une liste vide.
    * Donc le panel Dessin devient vide
    */
    public void CreeNouvelInterfaceDessin() {
        this.container.getFormList().clear();
        repaint();
    }
    
    /**
     * parcours toutes les formes pour verifier si y a une intersection
     * @param figure
     * @return 
     */
    public boolean intersecteAvecAutreFigure(Figure figure) {
        for (Figure f : this.getFigures()) {
            if (f != figure && f.intersecteAvec(figure)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * passe le jeu en mode fin de game
     */
    public void passerEnFinGame() {
        System.out.println("affichage solution ordinateur : ");
        repaint();
    }
    
    /**
     * set la solution de l'ordinateur avec la configuration choisie en parametre
     * @param solutions 
     */
    void setSolutionsOrdinateur(List<Figure> solutions) {
        this.solutionsOrdinateur = solutions;
        this.modelUpdated(this);
    }
    
    /**
     * retourne le container
     * @return 
     */
    public FormContainer getContainer() {
        return this.container;
    }


    
}
