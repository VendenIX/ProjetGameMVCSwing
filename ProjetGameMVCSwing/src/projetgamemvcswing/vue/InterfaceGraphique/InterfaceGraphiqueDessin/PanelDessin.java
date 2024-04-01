package projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.List;
import javax.swing.JPanel;
import projetgamemvcswing.controller.Observer.EcouteurModele;
import projetgamemvcswing.modele.geometry.*;
import projetgamemvcswing.controller.ShapeDrawer;
import projetgamemvcswing.controller.ShapeFiller;
import projetgamemvcswing.controller.State.DessinStates.DefaultState;
import projetgamemvcswing.controller.State.DessinStates.DessinState;
import projetgamemvcswing.modele.geometry.FormContainer;
import projetgamemvcswing.controller.Command.CommandHandler;


/**
 * La classe InterfaceDessin représente un JPanel pour dessiner des figures.
 * Elle permet de dessiner des cercles, des rectangles, des lignes et de les afficher.
 * Elle permet la coloration des différentes formes
 * Elle permet le deplacment des différentes formes
 * Elle permet la suppression des différentes formes
 * Elle permet d'annuler ou de refaire les étapes de dessin
 * 
 * @author Islem
 */
public class PanelDessin extends JPanel implements EcouteurModele {
    
    
    // Variables

    private final CommandHandler handler = new CommandHandler();
    // Container de type FormContainer qui stocke toutes les figures
    private final FormContainer container = new FormContainer();
    
    // Class contenant des methodes d'affichage de formes 
    private final ShapeDrawer shapeDrawer = new ShapeDrawer();
    private final ShapeFiller shapeFiller = new ShapeFiller();
    

    
   // Variable pour stocker la figure en cours de dessin
    private Figure figureEnCoursDeDessin;

    // Variable pour stocker la figure en cours de translation
    private Figure figureEnCoursDeTranslation;

    // Variable pour stocker la figure en cours de coloration
    private Figure figureEnCoursDeColoration;
    
    // Initialisation d'état par defaut
    private DessinState currentState = new DefaultState();
    
    // Couleur choisie par l'utilisateur
    public Color couleurChoisie;
    
    
    // Position précédente de la souris 
    public double lastMouseX;
    public double lastMouseY;
    
    private double tempDx = 0, tempDy = 0; //gere les deplacements temporaires

    /**
     * Constructeur de la classe InterfaceDessin.
     * Il initialise le panneau de dessin avec un fond blanc et ajoute des écouteurs de souris.
     */
    public PanelDessin() {
        // Set de fond blanc
        setBackground(Color.WHITE);
        handler.ajoutEcouteur(this);
        // Ajouter un écouteur pour les événements de la souris
        addMouseListener(new MouseAdapter() {
            // Quand il y a un clique de souris
            @Override
            public void mousePressed(MouseEvent e) {
                currentState.handleMousePressed(PanelDessin.this, e);
            }

            // Quand il y a une relache de souris
            @Override
            public void mouseReleased(MouseEvent e) {
                currentState.handleMouseReleased(PanelDessin.this, e, handler, container);
            }
        });

        // Ajouter un écouteur pour les mouvements de la souris
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //handleMouseDragged(e);
                currentState.handleMouseDragged(PanelDessin.this, e);
            }
        });
    }
    
    // Setter pour définir la figure en cours de dessin
    public void setFigureEnCoursDeDessin(Figure figure) {this.figureEnCoursDeDessin = figure;}

    // Getter pour obtenir la figure en cours de dessin
    public Figure getFigureEnCoursDeDessin() {return this.figureEnCoursDeDessin;}

    // Setter pour définir l'état actuel
    public void setCurrentState(DessinState state) {this.currentState = state;}

    // Getter pour obtenir la liste des figures
    public List<Figure> getFigures() {return this.container.getFormList();}

    // Getter pour obtenir la figure en cours de translation
    public Figure getFigureEnCoursDeTranslation() {return figureEnCoursDeTranslation;}

    // Setter pour définir la figure en cours de translation
    public void setFigureEnCoursDeTranslation(Figure figure) {this.figureEnCoursDeTranslation = figure;}

    // Setter pour définir la figure en cours de coloration
    public void setFigureEnCoursDeColoration(Figure figure) {this.figureEnCoursDeColoration = figure;}

    // Getter pour obtenir la figure en cours de coloration
    public Figure getFigureEnCoursDeColoration() {return figureEnCoursDeColoration;}

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
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Utilise figures de FormContainer pour le dessin
        shapeDrawer.drawFigures(g, container.getFormList());
        shapeFiller.drawFilledFigures(g2d, container.getFormList());
        
        
        //état temporaire pour dessinner les figures dynamiquement sans les enregistrer en tant que commande
        if (figureEnCoursDeDessin != null) {
            shapeDrawer.drawFigure(g, figureEnCoursDeDessin); // Utilisation de drawFigure pour la forme temporaire
            shapeFiller.drawFilledFigure(g2d, figureEnCoursDeDessin);
        }
        
        if (figureEnCoursDeTranslation != null && (tempDx != 0 || tempDy != 0)) {
            Graphics2D gTemp = (Graphics2D) g.create();
            gTemp.translate(tempDx, tempDy); // Applique la translation temporaire
            shapeDrawer.drawFigure(gTemp, figureEnCoursDeTranslation); // Dessinez la figure temporairement déplacée
            shapeFiller.drawFilledFigure(gTemp, figureEnCoursDeTranslation);
            gTemp.dispose(); // Libérer la ressource graphique temporaire
        }
        
    }

   
    /**
    * Définit la couleur sélectionnée.
    * 
    * @param selectedColor La couleur sélectionnée.
    */
    public void setSelectedColor(Color selectedColor) {
        this.couleurChoisie = selectedColor;
    }
    
    /**
     * Met à jour le panel
     * @param source 
     */
    @Override
    public void modelUpdated(Object source) {
        //System.out.println("Mise à jour du modèle reçue.");
        //System.out.println(container.toString());
        repaint();
    }
    
    /**
     * Supprime une figure du panel
     * @param f 
     */
    public void supprimerFigure(Figure f) {
        this.container.suppressionForm(f);
        this.modelUpdated(this); // Notifie que le modèle a changé, ce qui déclenchera un repaint
    }

    public CommandHandler getCommandHandler() {
        return handler;
    }
    
    public void setTempTranslation(double dx, double dy) {
        this.tempDx = dx;
        this.tempDy = dy;
    }
    
    /**
    * Cette méthode réinitialise la liste des figures à une liste vide.
    * Donc le panel Dessin devient vide
    */
    public void CreeNouvelInterfaceDessin() {
        this.container.getFormList().clear();
        this.figureEnCoursDeDessin = null;
        this.figureEnCoursDeTranslation = null; 
        this.figureEnCoursDeColoration = null;
        this.modelUpdated(this);
    }
    
    // Ajout de la méthode pour réinitialiser les déplacements temporaires
    public void resetTempTranslation() {
        tempDx = 0;
        tempDy = 0;
    }

    

}