
package projetgamemvcswing.vue.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import projetgamemvcswing.vue.InterfaceGraphique.InterfaceGraphiqueDessin.PanelDessin;

public class SauvegardeurImage {

    /**
    * Cette méthode affiche une boîte de dialogue pour permettre à l'utilisateur de
    * choisir l'emplacement où enregistrer l'image du JPanel en tant que fichier JPG.
    * @param panelASauvegarder
    */
    public void SauvegarderImageCommeJPG(PanelDessin panelASauvegarder) {
        
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
        int userSelection = fileChooser.showSaveDialog(panelASauvegarder);

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
            saveAsJPG(fileToSave.getAbsolutePath(), panelASauvegarder);

            // Afficher un message de succès
            JOptionPane.showMessageDialog(panelASauvegarder, "Image enregistrée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
    * Cette méthode enregistre le contenu du JPanel en tant que fichier JPG.
    * 
    * @param filePath Le chemin du fichier JPG.
    * @param panelASauvegarder
    */
    public void saveAsJPG(String filePath, PanelDessin panelASauvegarder) {
        
        // Recuperer la Largeur et la Hauteur
        int width = panelASauvegarder.getWidth();
        int height = panelASauvegarder.getHeight();
        
        // Recuper le dessin 
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        panelASauvegarder.paint(g);
        g.dispose();

        try {
            ImageIO.write(image, "jpg", new File(filePath));
            System.out.println("JPanel enregistré en tant que fichier JPG.");
        } catch (IOException ex) {
            System.err.println("Erreur lors de l'enregistrement du fichier JPG : " + ex.getMessage());
        }
    }

    
}