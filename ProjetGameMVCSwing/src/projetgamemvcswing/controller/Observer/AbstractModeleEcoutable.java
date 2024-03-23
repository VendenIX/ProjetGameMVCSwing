
package projetgamemvcswing.controller.Observer;
import java.util.*;

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
    for(EcouteurModele e : ecouteurs){
      e.modelUpdated(this);
    }
  }
}
