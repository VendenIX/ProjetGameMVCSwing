
package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueJeu;

import java.awt.Color;
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
import projetgamemvcswing.controller.State.JeuState.PlayDefaultState;
import projetgamemvcswing.controller.State.JeuState.JeuState;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.solveurs.RandomSolve;


public class PanelJeu extends JPanel implements EcouteurModele {
    
    // Variables

    // Container de type FormContainer qui stocke toutes les figures
    private final FormContainer container = new FormContainer();
    
    // Class contenant des methodes d'affichage de formes 
    private final ShapeDrawer shapeDrawer = new ShapeDrawer();
    private final ShapeFiller shapeFiller = new ShapeFiller();
    
    
   // Variable pour stocker la figure en cours de dessin
    private Figure figureEnCoursDeDessin;
    
    // Initialisation d'état par defaut
    private JeuState currentState = new PlayDefaultState();
    
    // Position précédente de la souris 
    private double lastMouseX;
    private double lastMouseY;
    
    private final GameScore gameScore;
    private ArrayList<Figure> formesGenerees;
       
    public PanelJeu(JFrame frame, GameScore gameScore) {
        
        if (gameScore == null) {
            throw new IllegalArgumentException("gameScore ne peut pas être null");
        }
        this.gameScore = gameScore;
        // Set de fond blanc
        setBackground(Color.WHITE);
        setSize(frame.getSize());
        
        // Generer des formes et les ajouter au container
        
        //met les formes et on les recup dans une liste en meme temps pour le solver
        this.formesGenerees = new RandomShapeGenerator().generateFormes(this, 4);
        

        
        // Ajouter un écouteur pour les événements de la souris
        addMouseListener(new MouseAdapter() {
            // Quand il y a un clique de souris
            @Override
            public void mousePressed(MouseEvent e) {
                currentState.handleMousePressed(PanelJeu.this, e);
            }

            // Quand il y a une relache de souris
            @Override
            public void mouseReleased(MouseEvent e) {
                currentState.handleMouseReleased(PanelJeu.this, e, gameScore);
            }
        });

        // Ajouter un écouteur pour les mouvements de la souris
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentState.handleMouseDragged(PanelJeu.this, e);
            }
        });
        
        
    }

    @Override
    public void modelUpdated(Object source) {
        double airePanel = this.getWidth() * this.getHeight();
        double pourcentageAireCouverte = gameScore.calculerPourcentageAireCouverte(airePanel);
        repaint();
    }

    
    private void updateGameScore() {
        double aireTotalePanel = getWidth() * getHeight();
        double aireCouverteBleue = container.calculerAireCouverteParCouleur(Color.BLUE); // Remplacez Color.BLUE par la couleur exacte utilisée pour les formes en mode jeu.
        gameScore.addAireCouverte(aireCouverteBleue);
    }

    
    

   // Setter pour définir la figure en cours de dessin
    public void setFigureEnCoursDeDessin(Figure figure) {this.figureEnCoursDeDessin = figure;}

    // Getter pour obtenir la figure en cours de dessin
    public Figure getFigureEnCoursDeDessin() {return this.figureEnCoursDeDessin;}

    // Setter pour définir l'état actuel
    public void setCurrentState(JeuState state) {this.currentState = state;}

    // Getter pour obtenir la liste des figures
    public List<Figure> getFigures() {return this.container.getFormList();}

    // Getter pour obtenir la position X précédente de la souris
    public double getLastMouseX() {return lastMouseX;}

    // Setter pour définir la position X précédente de la souris
    public void setLastMouseX(double lastMouseX) {this.lastMouseX = lastMouseX;}

    // Getter pour obtenir la position Y précédente de la souris
    public double getLastMouseY() { return lastMouseY;}

    // Setter pour définir la position Y précédente de la souris
    public void setLastMouseY(double lastMouseY) {this.lastMouseY = lastMouseY;}

    /**
    * Redéfinition de la méthode paintComponent pour dessiner et afficher les figures.
    * 
    * @param g L'objet Graphics pour dessiner.
    */
    @Override
    protected void paintComponent(Graphics g) {
        // Appel à la méthode paintComponent de la classe parent
        // pour effectuer les opérations de base
        super.paintComponent(g);
        
        // Conversion de l'objet Graphics en Graphics2D pour accéder à des fonctionnalités
        // avancées de dessin comme le remplissage
        Graphics2D g2d = (Graphics2D) g;
        
        // Dessiner les formes remplies existantes
        //shapeFiller.drawFilledFigures(g2d, this.getFigures());
        
        // Dessiner les contours des formes existantes
        shapeDrawer.drawFigures(g, this.getFigures());  
        
        // Dessiner la figure en cours de dessin
        if (figureEnCoursDeDessin != null) {
            shapeDrawer.drawFigure(g, figureEnCoursDeDessin);
            shapeFiller.drawFilledFigure(g2d, figureEnCoursDeDessin);
        }
        
        // Dessiner un cercle noir au milieu de l'écran
        //int centerX = getWidth() / 2;
        //int centerY = getHeight() / 2;    
        //Cercle cercle = new Cercle(new Point(centerX,centerY), 120, Color.YELLOW);
        //shapeDrawer.drawFigure(g, cercle);
        //shapeFiller.drawFilledFigure(g2d, cercle);
        RandomSolve solver = new RandomSolve(this.formesGenerees, this.getWidth(), this.getHeight());
        List<Figure> soluce = solver.getSoluce();
        for(Figure f : soluce) {
            shapeDrawer.drawFigure(g, f);
            shapeFiller.drawFilledFigure(g2d, f);
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
    
    //parcours toutes les formes pour verifier si y a une intersection
    public boolean intersecteAvecAutreFigure(Figure figure) {
        for (Figure f : this.getFigures()) {
            if (f != figure && f.intersecteAvec(figure)) {
                return true;
            }
        }
        return false;
    }

    
}
