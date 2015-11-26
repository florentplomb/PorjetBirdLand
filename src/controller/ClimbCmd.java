package controller;

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
        if (player.getCurrentRoom().getItem("ladder") != null) {

            if (hasSecondWord()) {
                String climbedObject = getSecondWord();
                String action = getThirdWord();

                if (climbedObject.equals("ladder")) {
                    if (action.equals("jump")) {
                        if (player.getCurrentRoom().getDescription().equals("outside")  ) {
                            return true;
                        }else{
                            player.setCurrentRoom(null);
                        }

                    }
                } else {
                    appendToOutputString("");
                }

            }

        } else {
                appendToOutputString("There is no ladder in this room, if you carry one , drop it and try again");
        }

        return false;
    }

}
