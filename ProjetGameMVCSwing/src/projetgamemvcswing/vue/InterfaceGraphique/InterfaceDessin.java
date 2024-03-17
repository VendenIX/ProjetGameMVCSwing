package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import projetgamemvcswing.modele.geometry.Point;
import projetgamemvcswing.modele.geometry.Figure;
import projetgamemvcswing.modele.geometry.Cercle;
import projetgamemvcswing.modele.geometry.Rectangle;
import projetgamemvcswing.modele.geometry.Ligne;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 * La classe InterfaceDessin représente un JPanel pour dessiner des figures.
 * Elle permet de dessiner des cercles et des rectangles et de les afficher.
 * 
 * @author Islem
 */
public class InterfaceDessin extends JPanel {

    private final List<Figure> figures = new ArrayList<>();
    private Figure figureEnCoursDeDessin;
    private Figure figureEnCoursDeTranslation;
    private String currentDrawState = "Default";
    
    private double lastMouseX;
    private double lastMouseY;

    /**
     * Constructeur par défaut de la classe InterfaceDessin.
     * Il initialise le panneau de dessin avec un fond blanc et ajoute des écouteurs de souris.
     */
    public InterfaceDessin() {
        setBackground(Color.WHITE);
        
        

        // Ajouter un écouteur pour les événements de la souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        // Ajouter un écouteur pour les mouvements de la souris
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    /**
     * Gère l'événement de pression de la souris.
     * Initialise la forme en cours de dessin en fonction de l'état actuel du dessin.
     * 
     * @param e L'événement de la souris.
     */
    private void handleMousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if ("Cercle".equals(currentDrawState)) {
            
            figureEnCoursDeDessin = new Cercle(new Point(x, y), 0);
            
        } else if ("Rectangle".equals(currentDrawState)) {
            
            figureEnCoursDeDessin = new Rectangle(x, y, 0, 0);
            
        } else if ("Ligne".equals(currentDrawState)){
            
            System.out.println("Ligne");
            figureEnCoursDeDessin = new Ligne(new Point(x, y),new Point(0, 0));
            
        } else if ("Supprimer".equals(currentDrawState)) {
            
            for (Figure f : figures) {
                if (f.contient(x, y)) {
                    // Supprimer la figure sélectionnée de la liste principale
                    figures.remove(f);

                    // Redessiner le panneau
                    repaint();

                    // Sortir de la boucle après la suppression d'une figure
                    return;
                }
            }
            
        } else if ("Déplacer".equals(currentDrawState)){
            for (Figure f : figures) {
                if (f.contient(x, y)) {
                    figureEnCoursDeTranslation = f;
                    return;
                }
            }
        } else if ("Coloration".equals(currentDrawState)){
            for (Figure f : figures) {
                if (f.contient(x, y)) {
                    figureEnCoursDeTranslation = f;
                    return;
                }
            }
        }
    }

    /**
     * Gère l'événement de relâchement de la souris.
     * Ajoute la forme en cours à la liste des figures et la réinitialise.
     * 
     * @param e L'événement de la souris.
     */
    private void handleMouseReleased(MouseEvent e) {
        if (figureEnCoursDeDessin != null) {
            figures.add(figureEnCoursDeDessin);
            figureEnCoursDeDessin = null;
            repaint();
        }
        
        if ("Déplacer".equals(currentDrawState) && figureEnCoursDeTranslation != null) {
            
            figureEnCoursDeTranslation = null;
            
            repaint();
        }
        
    }

    /**
     * Gère l'événement de mouvement de la souris lors du dessin d'une figure.
     * Modifie la taille de la figure en cours en fonction de la position de la souris.
     * 
     * @param e L'événement de la souris.
     */
    private void handleMouseDragged(MouseEvent e) {
        
        double mouseX = e.getX();
        double mouseY = e.getY();
            
        if (figureEnCoursDeDessin != null) {
            

            if (figureEnCoursDeDessin instanceof Cercle) {
                
                double rayon = ((Cercle) figureEnCoursDeDessin).distance(mouseX, mouseY);
                ((Cercle) figureEnCoursDeDessin).setRayon(rayon);
                
            } else if (figureEnCoursDeDessin instanceof Rectangle) {
                
                double width = Math.abs(mouseX - ((Rectangle) figureEnCoursDeDessin).getX());
                double height = Math.abs(mouseY - ((Rectangle) figureEnCoursDeDessin).getY());

                ((Rectangle) figureEnCoursDeDessin).setLargeur(width);
                ((Rectangle) figureEnCoursDeDessin).setHauteur(height);
                
            } else if (figureEnCoursDeDessin instanceof Ligne) {
                ((Ligne) figureEnCoursDeDessin).setXFin(mouseX);
                ((Ligne) figureEnCoursDeDessin).setYFin(mouseY);
            }

            repaint();
        }
        
        if ("Déplacer".equals(currentDrawState) && figureEnCoursDeTranslation != null) {
            
            double dx = mouseX - lastMouseX;
            double dy = mouseY - lastMouseY;

            
            lastMouseX = mouseX;
            lastMouseY = mouseY;

            if (figureEnCoursDeTranslation instanceof Cercle) {
                
                ((Cercle) figureEnCoursDeTranslation).translater(dx, dy);
                
            } else if (figureEnCoursDeTranslation instanceof Rectangle) {
                
                ((Rectangle) figureEnCoursDeTranslation).translater(dx, dy);
                
            } else if (figureEnCoursDeTranslation instanceof Ligne) {
                
                ((Ligne) figureEnCoursDeTranslation).translater(dx, dy);
                
            }
            repaint();
        }
        
    }

    /**
     * Redéfinition de la méthode paint pour dessiner les figures.
     * 
     * @param g L'objet Graphics pour dessiner.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Figure forme : figures) {
            
            if (forme instanceof Cercle) {
                Cercle cercle = (Cercle) forme;
                drawCircle(g, cercle.getX(), cercle.getY(), cercle.getRayon());
                
            } else if (forme instanceof Rectangle) {
                
                Rectangle rectangle = (Rectangle) forme;
                drawRectangle(g, rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur());
                
            } else if (forme instanceof Ligne) {
                
                Ligne ligneEnCours = (Ligne) forme;
                drawLine(g,ligneEnCours.getXDebut(), ligneEnCours.getYDebut(), ligneEnCours.getXFin(), ligneEnCours.getYFin());
            }
        }

        if (figureEnCoursDeDessin != null) {
            if (figureEnCoursDeDessin instanceof Cercle) {
                
                Cercle cercleEnCours = (Cercle) figureEnCoursDeDessin;
                drawCircle(g, cercleEnCours.getX(), cercleEnCours.getY(), cercleEnCours.getRayon());
                
            } else if (figureEnCoursDeDessin instanceof Rectangle) {
                
                Rectangle rectangleEnCours = (Rectangle) figureEnCoursDeDessin;
                drawRectangle(g, rectangleEnCours.getX(), rectangleEnCours.getY(),
                        rectangleEnCours.getLargeur(), rectangleEnCours.getHauteur());
                
            } else if (figureEnCoursDeDessin instanceof Ligne) {
                
                Ligne ligneEnCours = (Ligne) figureEnCoursDeDessin;
                drawLine(g,ligneEnCours.getXDebut(), ligneEnCours.getYDebut(), ligneEnCours.getXFin(), ligneEnCours.getYFin());
            }
        }
    }

    /**
     * Dessine un cercle sur le Graphics spécifié.
     * 
     * @param g      L'objet Graphics pour dessiner.
     * @param centerX La coordonnée x du centre du cercle.
     * @param centerY La coordonnée y du centre du cercle.
     * @param rayon   Le rayon du cercle.
     */
    private void drawCircle(Graphics g, double centerX, double centerY, double rayon) {
        g.setColor(Color.BLACK);

        int x = (int) Math.round(centerX - rayon);
        int y = (int) Math.round(centerY - rayon);
        int diameter = (int) Math.round(2 * rayon);

        g.drawOval(x, y, diameter, diameter);
    }

    /**
     * Dessine un rectangle sur le Graphics spécifié.
     * 
     * @param g      L'objet Graphics pour dessiner.
     * @param x      La coordonnée x du coin supérieur gauche du rectangle.
     * @param y      La coordonnée y du coin supérieur gauche du rectangle.
     * @param width  La largeur du rectangle.
     * @param height La hauteur du rectangle.
     */
    private void drawRectangle(Graphics g, double x, double y, double width, double height) {
        g.setColor(Color.BLACK);

        int xInt = (int) Math.round(x);
        int yInt = (int) Math.round(y);
        int widthInt = (int) Math.round(width);
        int heightInt = (int) Math.round(height);

        g.drawRect(xInt, yInt, widthInt, heightInt);
    }
    
    /**
    * Dessine une ligne avec deux coordonnées debut et fin
    * 
    * @param g Le contexte graphique dans lequel dessiner la ligne.
    * @param x1 La coordonnée x du point de départ de la ligne (en double).
    * @param y1 La coordonnée y du point de départ de la ligne (en double).
    * @param x2 La coordonnée x du point d'arrivée de la ligne (en double).
    * @param y2 La coordonnée y du point d'arrivée de la ligne (en double).
    */
    public void drawLine(Graphics g, double x1, double y1, double x2, double y2) {
        // Convertir les coordonnées en double en coordonnées entières en arrondissant
        int x1Int = (int) Math.round(x1);
        int y1Int = (int) Math.round(y1);
        int x2Int = (int) Math.round(x2);
        int y2Int = (int) Math.round(y2);

        // Dessiner la ligne avec les coordonnées entières calculées
        g.drawLine(x1Int, y1Int, x2Int, y2Int);
    }

    /**
     * Définit l'état actuel du dessin.
     * 
     * @param drawState Le nouvel état du dessin.
     */
    public void setcurrentDrawState(String drawState) {
        this.currentDrawState = drawState;
    }
    
    /**
    * Cette méthode réinitialise la liste des figures à une liste vide.
    */
    public void CreeNouvelInterfaceDessin() {
        figures.clear();
        repaint();
    }
   
   /**
    * Cette méthode affiche une boîte de dialogue pour permettre à l'utilisateur de
    * choisir l'emplacement où enregistrer l'image du JPanel en tant que fichier JPG.
    */
    public void SauvegarderImageCommeJPG() {
        
        // Créer un sélecteur de fichiers
        JFileChooser fileChooser = new JFileChooser();

        // Définir le titre de la boîte de dialogue
        fileChooser.setDialogTitle("Enregistrer l'image");

        // Filtrer les fichiers pour afficher uniquement les répertoires et les fichiers avec l'extension .jpg
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".jpg");
            }

            @Override
            public String getDescription() {
                return "Fichiers JPG (*.jpg)";
            }
        });

        // Afficher la boîte de dialogue pour choisir l'emplacement de sauvegarde
        int userSelection = fileChooser.showSaveDialog(this);

        // Si l'utilisateur a sélectionné un emplacement de sauvegarde
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtenir le fichier choisi par l'utilisateur
            File fileToSave = fileChooser.getSelectedFile();

            // Vérifier si l'extension du fichier est .jpg, sinon l'ajouter
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".jpg")) {
                fileToSave = new File(filePath + ".jpg");
            }

            // Appeler la méthode pour sauvegarder en tant que JPG
            saveAsJPG(fileToSave.getAbsolutePath());

            // Afficher un message de succès
            JOptionPane.showMessageDialog(this, "Image enregistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
    * Cette méthode enregistre le contenu du JPanel en tant que fichier JPG.
    * 
    * @param filePath Le chemin du fichier JPG.
    */
    public void saveAsJPG(String filePath) {
        
        // Recuperer la Largeur et la Hauteur
        int width = getWidth();
        int height = getHeight();
        
        // Recuper le dessin 
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        paint(g);
        g.dispose();

        try {
            ImageIO.write(image, "jpg", new File(filePath));
            System.out.println("JPanel enregistré en tant que fichier JPG.");
        } catch (IOException ex) {
            System.err.println("Erreur lors de l'enregistrement du fichier JPG : " + ex.getMessage());
        }
    }

    /**
     * Obtient l'état actuel du dessin.
     * 
     * @return L'état actuel du dessin.
     */
    public String getcurrentDrawState() {
        return this.currentDrawState;
    }
}

