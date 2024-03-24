package projetgamemvcswing.controller.Observer;

/**
 * L'interface EcouteurModele définit un contrat pour les classes qui souhaitent être notifiées
 * des mises à jour d'un modèle dans le cadre du design pattern Observateur. Cette interface est 
 * destinée à être implémentée par des classes qui agissent en tant qu'observateurs dans 
 * l'architecture MVC (Modèle-Vue-Contrôleur) de l'application. En implémentant cette interface,
 * une classe s'engage à réagir aux mises à jour du modèle qu'elle observe.
 * 
 * <p>Utilisation typique : 
 * Une classe qui implémente EcouteurModele s'inscrit auprès d'un modèle spécifique (qui agit en tant que sujet) 
 * pour recevoir des notifications de mise à jour. Lorsque le modèle change, il invoque {@code modelUpdated} 
 * sur chaque écouteur enregistré, permettant ainsi à l'écouteur de prendre les mesures appropriées en réponse
 * à la modification.</p>
 *
 * @see Modele
 */
public interface EcouteurModele {
    
     /**
     * Méthode appelée par le modèle observé lorsqu'une mise à jour est effectuée.
     * Les classes implémentant cette interface doivent redéfinir cette méthode pour spécifier
     * comment elles réagissent aux notifications de mise à jour du modèle. 
     * 
     * <p>Cette méthode permet une communication flexible entre le modèle et les observateurs,
     * puisqu'elle peut transmettre des informations spécifiques sur la mise à jour via son paramètre.
     * L'objet source, souvent une instance du modèle, fournit le contexte nécessaire à l'observateur
     * pour comprendre la nature de la mise à jour et réagir en conséquence.</p>
     * 
     * @param source l'objet qui a déclenché l'évènement, généralement l'instance du modèle qui a été mis à jour.
     */
    void modelUpdated(Object source);
}
