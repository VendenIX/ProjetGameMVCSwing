package projetgamemvcswing.vue.InterfaceGraphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
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
    
    
    // Variables
    // ArrayList qui stock toute les figures
    private final List<Figure> figures = new ArrayList<>();
    
    private Figure figureEnCoursDeDessin;
    private Figure figureEnCoursDeTranslation;
    private Figure figureEnCoursDeColoration;
    
    // Etat
    private String currentDrawState = "Default";
    
    // Couleur choisie
    private Color couleurChoisie;
    
    // Position précédente de la souris 
    private double lastMouseX;
    private double lastMouseY;

    /**
     * Constructeur de la classe InterfaceDessin.
     * Il initialise le panneau de dessin avec un fond blanc et ajoute des écouteurs de souris.
     */
    public InterfaceDessin() {
        // Set de fond blanc
        setBackground(Color.WHITE);
        
        

        // Ajouter un écouteur pour les événements de la souris
        addMouseListener(new MouseAdapter() {
            // Quand il y a un clique de souris
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                handleMousePressed(e);
            }

            // Quand il y a une relache de souris
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
            // Obtenir les coordonnées de la souris
            double x = e.getX();
            double y = e.getY();

            // Si l'état de dessin actuel est "Cercle"
            if ("Cercle".equals(currentDrawState)) {
                // Créer un nouveau cercle avec le point de départ à (x, y)
                // un rayon initial de 0 et une couleur transparent
                figureEnCoursDeDessin = new Cercle(new Point(x, y), 0, new Color(0, 0, 0, 0));

            // Si l'état de dessin actuel est "Rectangle"
            } else if ("Rectangle".equals(currentDrawState)) {
                // Créer un nouveau rectangle avec le coin supérieur gauche à (x, y)
                // une largeur et une hauteur initiales de 0 et une couleur blanche
                figureEnCoursDeDessin = new Rectangle(x, y, 0, 0, new Color(0, 0, 0, 0));

            // Si l'état de dessin actuel est "Ligne"
            } else if ("Ligne".equals(currentDrawState)){
                // Créer une nouvelle ligne avec le point de départ à (x, y)
                // le point d'arrivée initial à (0, 0) et une couleur blanche
                figureEnCoursDeDessin = new Ligne(new Point(x, y), new Point(x, y), new Color(0, 0, 0, 0));

            // Si l'état de dessin actuel est "Supprimer"
            } else if ("Supprimer".equals(currentDrawState)) {
                // Parcourir la liste des figures
                for (Figure f : figures) {
                    // Vérifier si la figure contient les coordonnées de la souris
                    if (f.contient(x, y)) {
                        // Supprimer la figure sélectionnée de la liste principale
                        figures.remove(f);

                        // Redessiner le panneau
                        repaint();

                        // Sortir de la boucle après la suppression d'une figure
                        return;
                    }
                }

            // Si l'état de dessin actuel est "Déplacer"
            } else if ("Déplacer".equals(currentDrawState)){
                // Parcourir la liste des figures
                for (Figure f : figures) {
                    // Vérifier si la figure contient les coordonnées de la souris
                    if (f.contient(x, y)) {
                        // Définir la figure sélectionnée comme celle à déplacer
                        figureEnCoursDeTranslation = f;
                        return;
                    }
                }

            // Si l'état de dessin actuel est "Coloration"
            } else if ("Coloration".equals(currentDrawState)){
                // Parcourir la liste des figures
                for (Figure f : figures) {
                    // Vérifier si la figure contient les coordonnées de la souris
                    if (f.contient(x, y)) {
                        // Définir la figure sélectionnée comme celle à colorier
                        figureEnCoursDeColoration = f;

                        // Appliquer la couleur sélectionnée à la figure, si elle existe
                        if (figureEnCoursDeColoration != null) {
                            if (figureEnCoursDeColoration instanceof Cercle) {
                                ((Cercle) figureEnCoursDeColoration).setCouleur(couleurChoisie);
                            } else if (figureEnCoursDeColoration instanceof Rectangle) {
                                ((Rectangle) figureEnCoursDeColoration).setCouleur(couleurChoisie);
                            } else if (figureEnCoursDeColoration instanceof Ligne) {
                                ((Ligne) figureEnCoursDeColoration).setCouleur(couleurChoisie);
                            }
                        }

                        // Redessiner le panneau
                        repaint();
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
            
            // Si une figure est en cours de dessin, l'ajoute à la liste des figures
            // et la réinitialise
            if (figureEnCoursDeDessin != null) {
                figures.add(figureEnCoursDeDessin);
                figureEnCoursDeDessin = null;
                repaint();
            }

            // Si l'état de dessin est "Déplacer" et une figure est en cours de translation,
            // réinitialise cette dernière
            if ("Déplacer".equals(currentDrawState) && figureEnCoursDeTranslation != null) {
                figureEnCoursDeTranslation = null;
                repaint();
            }

            // Si l'état de dessin est "Coloration" et une figure est en cours de coloration,
            // réinitialise cette dernière
            if ("Coloration".equals(currentDrawState) && figureEnCoursDeColoration != null) {
                figureEnCoursDeColoration = null;
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

        // Récupère les coordonnées de la souris
        double mouseX = e.getX();
        double mouseY = e.getY();

        // Si une figure est en cours de dessin, ajuste sa taille ou position
        if (figureEnCoursDeDessin != null) {

            // Si la figure en cours est un cercle
            if (figureEnCoursDeDessin instanceof Cercle) {

                // Calcule le rayon en fonction de la position de la souris
                double rayon = ((Cercle) figureEnCoursDeDessin).distance(mouseX, mouseY);
                ((Cercle) figureEnCoursDeDessin).setRayon(rayon);

            } 
            // Si la figure en cours est un rectangle
            else if (figureEnCoursDeDessin instanceof Rectangle) {

                // Calcule la largeur et la hauteur en fonction de la position de la souris
                double width = Math.abs(mouseX - ((Rectangle) figureEnCoursDeDessin).getX());
                double height = Math.abs(mouseY - ((Rectangle) figureEnCoursDeDessin).getY());

                ((Rectangle) figureEnCoursDeDessin).setLargeur(width);
                ((Rectangle) figureEnCoursDeDessin).setHauteur(height);

            } 
            // Si la figure en cours est une ligne
            else if (figureEnCoursDeDessin instanceof Ligne) {
                ((Ligne) figureEnCoursDeDessin).setXFin(mouseX);
                ((Ligne) figureEnCoursDeDessin).setYFin(mouseY);
            }

            repaint(); // Redessine le panneau
        }

        // Si l'état de dessin est "Déplacer" et une figure est en cours de translation
        if ("Déplacer".equals(currentDrawState) && figureEnCoursDeTranslation != null) {

            // Calcule les déplacements en x et y
            double dx = mouseX - lastMouseX;
            double dy = mouseY - lastMouseY;


            // Met à jour la dernière position de la souris
            lastMouseX = mouseX; 
            lastMouseY = mouseY;

            // Translate la figure en cours de translation en fonction des déplacements
            if (figureEnCoursDeTranslation instanceof Cercle) {

                ((Cercle) figureEnCoursDeTranslation).translater(dx, dy);

            } else if (figureEnCoursDeTranslation instanceof Rectangle) {

                ((Rectangle) figureEnCoursDeTranslation).translater(dx, dy);

            } else if (figureEnCoursDeTranslation instanceof Ligne) {

                ((Ligne) figureEnCoursDeTranslation).translater(dx, dy);

            }
            repaint(); // Redessine le panneau
        } 
    }

    /**
    * Redéfinition de la méthode paintComponent pour dessiner les figures.
    * 
    * @param g L'objet Graphics pour dessiner.
    */
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Dessiner les figures existantes
        for (Figure forme : figures) {
            if (forme instanceof Cercle) {
                Cercle cercle = (Cercle) forme;
                g2d.setColor(((Cercle) forme).getCouleur());
                fillCircle(g2d, cercle.getX(), cercle.getY(), cercle.getRayon());
            } else if (forme instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) forme;
                g2d.setColor(((Rectangle) forme).getCouleur());
                fillRectangle(g2d, rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur());
            } else if (forme instanceof Ligne) {
                Ligne ligne = (Ligne) forme;
                g2d.setColor(((Ligne) forme).getCouleur());
                fillLine(g2d, ligne.getXDebut(), ligne.getYDebut(), ligne.getXFin(), ligne.getYFin());
            }
        }

        // Dessiner la figure en cours de coloration
        if (figureEnCoursDeColoration != null) {
            if (figureEnCoursDeColoration instanceof Cercle) {
                Cercle cercleEnCours = (Cercle) figureEnCoursDeColoration;
                fillCircle(g2d, cercleEnCours.getX(), cercleEnCours.getY(), cercleEnCours.getRayon());
            } else if (figureEnCoursDeColoration instanceof Rectangle) {
                Rectangle rectangleEnCours = (Rectangle) figureEnCoursDeColoration;
                fillRectangle(g2d, rectangleEnCours.getX(), rectangleEnCours.getY(),
                        rectangleEnCours.getLargeur(), rectangleEnCours.getHauteur());
            } else if (figureEnCoursDeColoration instanceof Ligne) {
                Ligne ligneEnCours = (Ligne) figureEnCoursDeColoration;
                fillLine(g2d, ligneEnCours.getXDebut(), ligneEnCours.getYDebut(), ligneEnCours.getXFin(), ligneEnCours.getYFin());
            }
        }

        // Dessiner les bordures des figures existantes
        for (Figure forme : figures) {
            if (forme instanceof Cercle) {
                Cercle cercle = (Cercle) forme;
                drawCircle(g, cercle.getX(), cercle.getY(), cercle.getRayon());
            } else if (forme instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) forme;
                drawRectangle(g, rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur());
            } else if (forme instanceof Ligne) {
                Ligne ligneEnCours = (Ligne) forme;
                drawLine(g, ligneEnCours.getXDebut(), ligneEnCours.getYDebut(), ligneEnCours.getXFin(), ligneEnCours.getYFin());
            }
        }

        // Dessiner la figure en cours de dessin
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
                drawLine(g, ligneEnCours.getXDebut(), ligneEnCours.getYDebut(), ligneEnCours.getXFin(), ligneEnCours.getYFin());
            }
        }
    }

    
    /**
    * Remplir un cercle.
    *
    * @param g       L'objet Graphics2D pour dessiner.
    * @param centerX La coordonnée x du centre du cercle.
    * @param centerY La coordonnée y du centre du cercle.
    * @param rayon   Le rayon du cercle.
    */
    private void fillCircle(Graphics2D g, double centerX, double centerY, double rayon) {
        int x = (int) Math.round(centerX - rayon);
        int y = (int) Math.round(centerY - rayon);
        int diamètre = (int) Math.round(2 * rayon);
        g.fillOval(x, y, diamètre, diamètre);
    }

   /**
    * Remplir un rectangle.
    *
    * @param g      L'objet Graphics2D pour dessiner.
    * @param x      La coordonnée x du coin supérieur gauche du rectangle.
    * @param y      La coordonnée y du coin supérieur gauche du rectangle.
    * @param largeur  La largeur du rectangle.
    * @param hauteur La hauteur du rectangle.
    */
    private void fillRectangle(Graphics2D g, double x, double y, double largeur, double hauteur) {
        int xInt = (int) Math.round(x);
        int yInt = (int) Math.round(y);
        int largeurInt = (int) Math.round(largeur);
        int hauteurInt = (int) Math.round(hauteur);
        g.fillRect(xInt, yInt, largeurInt, hauteurInt);
    }

   /**
    * Remplir une ligne (non standard, car les lignes ne sont généralement pas remplies, mais peuvent être dessinées avec une forme remplie).
    *
    * @param g  L'objet Graphics2D pour dessiner.
    * @param x1 La coordonnée x du début de la ligne.
    * @param y1 La coordonnée y du début de la ligne.
    * @param x2 La coordonnée x de la fin de la ligne.
    * @param y2 La coordonnée y de la fin de la ligne.
    */
    private void fillLine(Graphics2D g, double x1, double y1, double x2, double y2) {
        // Cette méthode est non standard car les lignes ne sont généralement pas remplies, mais vous pouvez dessiner une forme remplie
        g.fill(new Line2D.Double(x1, y1, x2, y2));
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
    * Donc le panel Dessin sera vide
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
    
    /**
    * Définit la couleur sélectionnée.
    * 
    * @param selectedColor La couleur sélectionnée.
    */
    public void setSelectedColor(Color selectedColor) {
        this.couleurChoisie = selectedColor;
    }
}

