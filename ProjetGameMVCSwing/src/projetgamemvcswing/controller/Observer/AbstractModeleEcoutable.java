
package projetgamemvcswing.controller.Observer;
import java.util.*;
import java.util.Random;
public class AbstractModeleEcoutable implements ModeleEcoutable {
  private List<EcouteurModele> ecouteurs;

  public AbstractModeleEcoutable(){
    this.ecouteurs = new ArrayList<>();
  }

  @Override
  public void ajoutEcouteur(EcouteurModele e){
    ecouteurs.add(e);
    e.modelUpdated(this);
  }

  @Override
  public void retraitEcouteur(EcouteurModele e){
    ecouteurs.remove(e);
  }

  protected void fireChange(){
     Random random = new Random();
    double randomNumber = random.nextDouble(); // Génère un nombre aléatoire entre 0.0 et 1.0
    String formattedNumber = String.format("%.8f", randomNumber); // Formate le nombre pour avoir 8 décimales
    
    for(EcouteurModele e : ecouteurs){
      e.modelUpdated(this);
    }
    
    // Affiche le nom de la classe, le hashCode (substitut de l'adresse mémoire) et le nombre aléatoire
    System.out.println(this.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this)) + " modifié. Nombre aléatoire : " + formattedNumber);
  }
}
