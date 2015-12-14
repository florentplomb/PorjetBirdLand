package controller;

import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Player;

/**
 * Implementation of *Help* Command.
 */
public class HelpCmd extends Command {

    private Set<String> commandWords;

    public HelpCmd() {
    }

    /*
     * Sets the list of command words available on this game.
     */
    public void setCommandWords(Set<String> words) {
        commandWords = words;
    }

    /*
     * Outputs some help information. Here we print some message and a list of
     * the command words. Returns always false.
     */
    public boolean execute(Player player) {
        clearOutputString();
        appendToOutputString("You are lost. You are alone. You wander\n");
        appendToOutputString("In the prison.\n");
        appendToOutputString("\n");
        appendToOutputString("Your command words are:\n");

        String help = "- go: the player can go to 4 different directions (north, south, west, east), the selected \n"
                + "\n"
                + "direction should be written as second word after ?go?.\n"
                + "\n"
                + "- quit: by entering this word, the player can exits the game at any time.\n"
                + "\n"
                + "- look: the player gets the name of the current room, where he stands.\n"
                + "\n"
                + "- back: the player returns in the previous room.\n"
                + "\n"
                + "- take: it allows the player to pick up transportable objects distributed in rooms. The \n"
                + "\n"
                + "object should be written as a second word. Ex: take ladder.\n"
                + "\n"
                + "- drop: it allows the player to drop a transported object in the current room. The object \n"
                + "\n"
                + "should be written as a second word. Ex: drop ladder.\n"
                + "\n"
                + "- climb: when the player is transporting the ladder, he can climbed at any moment. Two \n"
                + "\n"
                + "scenarios can happen: 1) the player is in the right room and uses the ladder to \n"
                + "\n"
                + "escape 2) the player is not in the right room and by climbing the ladder, he his \n"
                + "\n"
                + "teleported to a random room. This command has to be followed by these two words: \n"
                + "\n"
                + "climb ladder jump. Note that the ladder has to be dropped in the current room, before \n"
                + "\n"
                + "it can be used.\n"
                + "\n"
                + "- use: allows the player to use fixed objects (the alarm's button).";

        JOptionPane.showMessageDialog(new JFrame(), help, "Help command",
                JOptionPane.INFORMATION_MESSAGE);

        // appends all command words to output string.
        for (String commands : commandWords) {
            appendToOutputString(commands + "  ");
        }

        appendToOutputString("\n");
        return false;
    }
}
