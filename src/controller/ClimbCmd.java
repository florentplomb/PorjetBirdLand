package controller;

import model.Player;

/**
 * Implementation of *Climb* command.
 * With this command, the player can climb the ladder and jump from the wall,
 * so that he can escape the prison and win the game.
 */
public class ClimbCmd extends Command {
    
    public ClimbCmd() {
        } 
    
    /*
     * Goes to the direction which is entered as the second word. If there is no
     * exit for that direction, prints an error message. Returns always *false*.
     */
    public boolean execute (Player player) {
        clearOutputString();
        
        if (hasSecondWord()) {
           String climbedObject = getSecondWord();
           
           if (climbedObject == "Ladder" || climbedObject == "ladder") {
        }
          
        }
return false;
    }
    
}