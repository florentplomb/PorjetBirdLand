package controller;

import DataBaseManager.DataBaseController;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Game;
import model.Player;
import view.ScoreView;

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
                        if (player.getCurrentRoom().getId().equals("libertyWall")) {
                            if (player.getCurrentRoom().getItem("blanket") != null) {

                                DataBaseController.insertDataPlayer(player);

                                ScoreView scoreView = new ScoreView(player);

                                ImageIcon icon = new ImageIcon(ClimbCmd.class.getResource("/images/winner.jpg"));
                                JOptionPane.showMessageDialog(null, "",
                                        "You are free! Enjoy life...", JOptionPane.PLAIN_MESSAGE, icon);
                                scoreView.dispose();
                                System.exit(0);
                                return true;
                            } else {
                                ScoreView scoreView = new ScoreView(player);
                                JOptionPane.showMessageDialog(null, "You forgot the blanket you die on the barbed.",
                                        "Game Over", JOptionPane.PLAIN_MESSAGE, null);
                                scoreView.dispose();
                                System.exit(0);

                            }
                        } else {
                            appendToOutputString("Wow... You were teleported to a random room of the game.");
                            player.setCurrentRoom(Game.getRandomRooms());
                        }

                    } else {
                        appendToOutputString("If you climb the ladder, then you have to jump. ");
                    }
                } else {
                    appendToOutputString("Climb on what? If you carry a ladder, drop it and try again ;) ");
                }

            } else {
                appendToOutputString("You have to climb the ladder and jump => 'climb ladder jump'.");
            }

        } else {
            appendToOutputString("Climb what? ");

        }

        return false;
    }

}
