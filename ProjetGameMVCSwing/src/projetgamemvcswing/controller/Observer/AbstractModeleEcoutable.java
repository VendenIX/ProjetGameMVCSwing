
package projetgamemvcswing.controller.Observer;
import java.util.*;
import java.util.Random;

/**
 * La classe AbstractModeleEcoutable sert de base pour les modèles écoutables dans une architecture MVC
 * (Modèle-Vue-Contrôleur) utilisant le pattern Observateur. Cette classe abstraite implémente l'interface
 * ModeleEcoutable et gère la liste des écouteurs (observateurs) qui sont notifiés lors des mises à jour du modèle.
 * 
 * <p>Elle fournit une infrastructure pour ajouter ou retirer des écouteurs et pour notifier ces écouteurs lors
 * que le modèle change. La méthode {@code fireChange} est utilisée pour déclencher les notifications aux écouteurs
 * enregistrés, indiquant qu'une mise à jour a eu lieu.</p>
 * 
 * <p>Cette classe est destinée à être étendue par des modèles spécifiques qui implémenteront la logique métier
 * et les mécanismes de mise à jour.</p>
 * 
 * @see ModeleEcoutable
 * @see EcouteurModele
 */
public class AbstractModeleEcoutable implements ModeleEcoutable {
  private List<EcouteurModele> ecouteurs;

   /**
   * Constructeur qui initialise la liste des écouteurs. Cette liste sera utilisée pour stocker
   * les écouteurs qui s'inscrivent pour recevoir les notifications de mise à jour du modèle.
   */
  public AbstractModeleEcoutable(){
    this.ecouteurs = new ArrayList<>();
  }
  
   /**
   * Ajoute un écouteur à la liste des écouteurs. L'écouteur ajouté sera notifié des mises à jour du modèle.
   * 
   * @param e L'écouteur à ajouter à la liste des notifications.
   */
  @Override
  public void ajoutEcouteur(EcouteurModele e){
    ecouteurs.add(e);
    e.modelUpdated(this);
  }
  
   /**
   * Retire un écouteur de la liste des écouteurs. L'écouteur spécifié ne sera plus notifié des mises à jour du modèle.
   * 
   * @param e L'écouteur à retirer de la liste des notifications.
   */
  @Override
  public void retraitEcouteur(EcouteurModele e){
    ecouteurs.remove(e);
  }
  
   /**
   * Notifie tous les écouteurs enregistrés d'une mise à jour du modèle. Cette méthode génère également
   * un nombre aléatoire qui est utilisé pour illustrer un aspect du changement (par exemple, pour les tests ou le debug).
   * Le nombre aléatoire et les informations d'identification de l'instance sont affichés dans la console.
   */
  protected void fireChange(){
    //Random random = new Random();
    //double randomNumber = random.nextDouble(); // Génère un nombre aléatoire entre 0.0 et 1.0
    //String formattedNumber = String.format("%.8f", randomNumber); // Formate le nombre pour avoir 8 décimales
    
    for(EcouteurModele e : ecouteurs){
      e.modelUpdated(this);
    }

    // Affiche le nom de la classe, le hashCode (substitut de l'adresse mémoire) et le nombre aléatoire
    //System.out.println(this.getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this)) + " modifié. Nombre aléatoire : " + formattedNumber);
  }
}
