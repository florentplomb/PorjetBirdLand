package controller;

import model.Game;
import model.Player;

/**
 * Implementation of *Climb* command. With this command, the player can climb
 * the ladder and jump from the wall, so that he can escape the prison and win
 * the game.
 */
public class ClimbCmd extends Command {

    public ClimbCmd() {
    }

    /*
     * Goes to the direction which is entered as the second word. If there is no
     * exit for that direction, prints an error message. Returns always *false*.
     */
    public boolean execute(Player player) {
        clearOutputString();

        if (hasSecondWord()) {

            if (hasThirdWord()) {

                String climbedObject = getSecondWord();
                String action = getThirdWord();

                if (climbedObject.equals("ladder") && player.getCurrentRoom().getItem("ladder") != null) {

                    if (action.equals("jump")) {
                        if (player.getCurrentRoom().getId().equals("outside")) {
                            if(player.getCurrentRoom().getItem("blanket") != null)  {
                              
                                appendToOutputString("You just won the game! :) ");                           
                                return true;   
                            }
                            else{
                                /*
                                MORT SI IL Y A PAS  BLANKET
                                */
                                appendToOutputString("You just killed yourself :( ");
                            }                            
                        } else {
                            appendToOutputString("Wow... You were teleported to a random room of the game ");
                            player.setCurrentRoom(Game.getRandomRooms());
                        }

                    } else {
                        appendToOutputString("If you climb the ladder, then you have to jump. ");
                    }
                } else {
                    appendToOutputString("There is no ladder to climb in this room, if you carry one , drop it and try again ;) ");
                }

            } else {
                    appendToOutputString("You have to climb the ladder and jump => 'climb ladder jump'");
            }

        } else {
            appendToOutputString("Climb what? ");
            
        }

        return false;
    }

}
