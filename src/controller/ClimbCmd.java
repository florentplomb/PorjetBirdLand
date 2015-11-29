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
            String climbedObject = getSecondWord();
            String action = getThirdWord();

            if (climbedObject.equals("ladder")) {
                if (action.equals("jump")) { 
                    if (player.getCurrentRoom().getDescription().equals("outside")) {

                        appendToOutputString("You Win ! ");
                        return true;
                    } else {
                        appendToOutputString("You are ejected in the random room ");
                        player.setCurrentRoom(Game.getRandomRooms());
                    }

                } else {
                    appendToOutputString("If you climb on the ladder then, you have to jump. 'climb ladder jump' ");
                }
            } else {
                appendToOutputString("There is no ladder to climb in this room, if you carry one , drop it and try again ");
            }

        } else {
            appendToOutputString("Climb on what?");
        }

        return false;
    }

}
