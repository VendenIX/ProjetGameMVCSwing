package projetgamemvcswing.controller.Command;

import java.io.Serializable;
import java.util.Stack;

/**
 * 
 * Class that handles the command and calls the operation and compensation methods
 * 
 * This class is responsible for handling the command and calling the operation and compensation methods
 * 
 * He created two methods to undo and redo the last action
 * 
 * @see Stack
 * 
 * @see OperationCommand
 * 
 * @author <a href="mailto:22012235@etu.unicaen.fr">
 *       OROU-GUIDOU Amirath Fara
 *      </a>
 */



public class CommandHandler implements Serializable{ // Serializable pour sauvegarder l'etat de l'objet

    private Stack<OperationCommand> stack;
    private Stack<OperationCommand> redoStack;


    /**
     * Le constructeur de la classe CommandHandler
     */
    public CommandHandler() {
        stack = new Stack<>();
        redoStack = new Stack<>();
    }


    /**
     * Methode handle qui permet d'effectuer une action
     * @param command
     */
    public void handle(OperationCommand command) {
        command.operate();
        stack.push(command);
        redoStack.clear();
    }


    /**
     * Methode undo qui permet d'annuler la dernière action effectuée
     */
    public void undo() {
        if (!stack.isEmpty()) {
            OperationCommand command = stack.pop();
            command.compensate();
            redoStack.push(command);
        }
    }

    /**
     * Methode redo qui permet de rétablir la dernière action annulée
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            OperationCommand command = redoStack.pop();
            command.operate();
            stack.push(command);
        }
    }
    
    public int getStackSize(){
        return this.stack.size();
    }
    
    public int getRedoStackSize(){
        return this.redoStack.size();
    }
    
    @Override
    public String toString(){
        return "()";
    }
    
}
