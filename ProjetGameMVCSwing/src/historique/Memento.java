package historique;

public class Memento {
    private double x, y;
    private double[] proprietes;

    public Memento(double x, double y, double... proprietes) {
        this.x = x;
        this.y = y;
        this.proprietes = proprietes;
    }

    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    public double[] getProprietes() { return proprietes; }
}