package projetgamemvcswing.controller.Observer;

/**
 * L'interface ModeleEcoutable définit les méthodes nécessaires pour qu'un modèle
 * puisse communiquer avec ses écouteurs dans le cadre d'une architecture MVC (Modèle-Vue-Contrôleur),
 * en suivant le pattern Observateur. Cette interface est conçue pour être implémentée par des modèles
 * qui doivent notifier les écouteurs (vues ou autres objets intéressés) de toute modification de leur état.
 * 
 * <p>Les méthodes définies permettent l'ajout et le retrait d'écouteurs, facilitant ainsi une communication
 * flexible et dynamique entre le modèle et ses observateurs. Cela est particulièrement utile dans les applications
 * interactives où les changements d'état du modèle doivent être rapidement reflétés dans l'interface utilisateur.</p>
 */
public interface ModeleEcoutable {
    
     /**
     * Ajoute un écouteur au modèle. Cette méthode permet d'enregistrer un écouteur
     * qui sera notifié des mises à jour ou des changements d'état du modèle.
     * 
     * <p>Implémenter cette méthode dans un modèle permet d'assurer que tous les écouteurs enregistrés
     * reçoivent les notifications appropriées lorsque le modèle change, garantissant ainsi que la vue est
     * toujours synchronisée avec l'état actuel du modèle.</p>
     * 
     * @param e l'écouteur à ajouter à la liste des écouteurs notifiés lors des mises à jour du modèle.
     */
    public void ajoutEcouteur(EcouteurModele e);
    
     /**
     * Retire un écouteur du modèle. Si l'écouteur est enregistré auprès du modèle,
     * cette méthode le supprime de la liste des écouteurs qui reçoivent les notifications
     * de mise à jour, cessant ainsi de le notifier lors des changements d'état du modèle.
     * 
     * <p>Il est important de gérer correctement l'ajout et le retrait des écouteurs pour éviter
     * les fuites de mémoire et s'assurer que les écouteurs ne sont plus notifiés une fois qu'ils
     * ne doivent plus l'être (par exemple, lorsqu'ils sont supprimés ou non visibles).</p>
     * 
     * @param e l'écouteur à retirer de la liste des écouteurs notifiés.
     */
    public void retraitEcouteur(EcouteurModele e);
}
