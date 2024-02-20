/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgamemvcswing.controller;
import java.util.*;
/**
 *
 * @author 21907062
 */
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
