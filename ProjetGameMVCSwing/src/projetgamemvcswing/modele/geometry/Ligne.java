package projetgamemvcswing.modele.geometry;

import projetgamemvcswing.modele.historique.Memento;

public class Ligne implements Figure {
    
    private Point debut;
    private Point fin;
    
    
    public Ligne(Point debut, Point fin) {
        this.debut = debut;
        this.fin = fin;
    }
    
    public double getXDebut() { return this.debut.getX(); }
    public double getYDebut() { return this.debut.getY(); }
    
    public void setXDebut(double Xdebut) { this.debut.setX(Xdebut);}
    public void setYDebut(double Ydebut) { this.debut.setY(Ydebut); }
    
    public double getXFin() { return this.fin.getX(); }
     public double getYFin() { return this.fin.getY(); }
     
    public void setXFin(double Xfin) { this.fin.setX(Xfin); }
    public void setYFin(double Yfin) { this.fin.setY(Yfin); }  
    
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
    
    @Override
    public Memento creerMemento() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void restaurerEtat(Memento memento) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean intersecteAvec(Figure autre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
    
}
