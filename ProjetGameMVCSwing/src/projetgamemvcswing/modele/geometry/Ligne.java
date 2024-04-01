package projetgamemvcswing.modele.geometry;

import projetgamemvcswing.controller.Observer.AbstractModeleEcoutable;

import java.awt.Color;

/**
 * Représente une ligne dans l'espace 2D.
 */
public class Ligne extends AbstractModeleEcoutable implements Figure {

    // Point de départ de la ligne
    private final Point debut;

    // Point d'arrivée de la ligne
    private final Point fin;

    // Couleur de la ligne
    private Color couleur;

    /**
     * Constructeur de la ligne.
     *
     * @param debut   Le point de départ de la ligne.
     * @param fin     Le point d'arrivée de la ligne.
     * @param couleur La couleur de la ligne.
     */
    public Ligne(Point debut, Point fin, Color couleur) {
        this.debut = debut;
        this.fin = fin;
        this.couleur = couleur;
    }

    // Méthodes getters et setters pour les points de début et d'arrivée

    public double getXDebut() {
        return this.debut.getX();
    }

    public double getYDebut() {
        return this.debut.getY();
    }

    public Point getDebut() {
        return this.debut;
    }

    public void setXDebut(double Xdebut) {
        this.debut.setX(Xdebut);
        fireChange(); // Notifier les écouteurs du changement
    }

    public void setYDebut(double Ydebut) {
        this.debut.setY(Ydebut);
        fireChange(); // Notifier les écouteurs du changement
    }

    public void setDebut(double Xdebut, double Ydebut) {
        this.setXDebut(Xdebut);
        this.setYDebut(Ydebut);
    }

    // Méthodes getters et setters pour le point d'arrivée

    public double getXFin() {
        return this.fin.getX();
    }

    public double getYFin() {
        return this.fin.getY();
    }

    public Point getFin() {
        return this.fin;
    }

    public void setXFin(double Xfin) {
        this.fin.setX(Xfin);
        fireChange(); // Notifier les écouteurs du changement
    }

    public void setYFin(double Yfin) {
        this.fin.setY(Yfin);
        fireChange(); // Notifier les écouteurs du changement
    }

    public void setFin(double Xfin, double Yfin) {
        this.setXFin(Xfin);
        this.setYFin(Yfin);
    }

    // Méthodes getters et setters pour la couleur

    @Override
    public Color getCouleur() {
        return couleur;
    }

    @Override
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
        fireChange(); // Notifier les écouteurs du changement
    }

    /**
     * Translade la ligne par les distances spécifiées.
     *
     * @param dx Le déplacement dans la direction x.
     * @param dy Le déplacement dans la direction y.
     */
    @Override
    public void translater(double dx, double dy) {

        double newDebutX = this.getXDebut() + dx;
        double newDebutY = this.getYDebut() + dy;
        double newFinX = this.getXFin() + dx;
        double newFinY = this.getYFin() + dy;

        this.setXDebut(newDebutX);
        this.setYDebut(newDebutY);
        this.setXFin(newFinX);
        this.setYFin(newFinY);
    }

    /**
     * Vérifie si un point donné est contenu dans la ligne.
     *
     * @param x La coordonnée x du point à vérifier.
     * @param y La coordonnée y du point à vérifier.
     * @return true si le point est contenu dans la ligne, sinon false.
     */
    @Override
    public boolean contient(double x, double y) {
        // Récupérer les coordonnées des points de début et de fin de la ligne
        double xDebut = getXDebut();
        double yDebut = getYDebut();
        double xFin = getXFin();
        double yFin = getYFin();

        // Calculer les distances entre le point donné (x, y) et les extrémités de la ligne
        double d1 = Math.sqrt(Math.pow(x - xDebut, 2) + Math.pow(y - yDebut, 2));
        double d2 = Math.sqrt(Math.pow(x - xFin, 2) + Math.pow(y - yFin, 2));

        // Calculer la longueur totale de la ligne
        double lineLength = Math.sqrt(Math.pow(xFin - xDebut, 2) + Math.pow(yFin - yDebut, 2));

        // Définir une tolérance pour les erreurs de calcul
        double epsilon = 0.1; // Vous pouvez ajuster cette valeur selon vos besoins

        // Vérifier si la somme des distances d1 et d2 est égale à la longueur de la ligne avec une certaine tolérance
        return Math.abs(d1 + d2 - lineLength) < epsilon;
    }

    // Les autres méthodes non implémentées pour le moment

    @Override
    public boolean intersecteAvec(Figure autre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return String.format("Ligne [debut=(%.2f, %.2f), fin=(%.2f, %.2f), couleur=%s]",
                debut.getX(), debut.getY(),
                fin.getX(), fin.getY(),
                couleur.toString());
    }

    @Override
    public Figure copie() {
        return new Ligne(this.getDebut(), this.getFin(), this.getCouleur());
    }

    @Override
    public String getName() {
        return "Ligne";
    }

    @Override
    public double getSurface() {
        return 0; // Une ligne n'a pas de surface
    }

}
