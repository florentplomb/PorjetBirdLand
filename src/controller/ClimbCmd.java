package controller;

import javax.swing.JOptionPane;
import model.Game;
import model.Player;

/**
 * Implementation of *Climb* command. With this command, the player can climb
 * the ladder and jump from the wall, so that he can escape the prison and win
 * the game.
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class ClimbCmd extends Command {

    public ClimbCmd() {
    }

    /**
     * Allows the player to climb the prison wall to escape, if the player isn't
     * in the escape room , he will be teleported in the random room
     *
     * @param player the current player
     * @return true if the player success to espace (the game is won), if not
     * false.
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
                            if (player.getCurrentRoom().getItem("blanket") != null) {

                                JOptionPane.showMessageDialog(null, "You win",
                                        "You are escaped!! Enjoy your life...", JOptionPane.PLAIN_MESSAGE, null);
                                System.exit(0);
                                return true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Game over",
                                        "You forgot the blanket you die on the barbed", JOptionPane.PLAIN_MESSAGE, null);
                                System.exit(0);

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
