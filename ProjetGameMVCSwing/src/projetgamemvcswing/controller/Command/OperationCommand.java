/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * OperationCommand interface
 * 
 * This interface is used to define the methods that will be implemented by the
 * classes that will be used to perform the operations
 * 
  @author <a href="mailto:22012235@etu.unicaen.fr">
 *        OROU-GUIDOU Amirath Fara
 *       </a>
 * @since 2024-02-06
 * @version 1.0
*/
package projetgamemvcswing.controller.Command;

public interface OperationCommand {

    /**
     * This method is used to perform the operation
     */
    public void operate();

    /**
     * This method is used to compensate the operation
     */
    public void compensate();
}
