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

        String help = "Bellow is the list of all possible commands in the game:\n"
                + "Note: All words entered should be written in small letters and most of the commands should"
                + "be followed by a second word.\n"
                + "- go: the player can go to 4 different directions (north, south, west, east), the selected"
                + "direction should be written as second word after ?go?.\n"
                + "- quit: by entering this word, the player can exits the game at any time.\n"
                + "- look: the player gets the name of the current room, where he stands.\n"
                + "- back: the player returns in the previous room.\n"
                + "- take: it allows the player to pick up transportable objects distributed in rooms. The"
                + "object should be written as a second word. Ex: take ladder.\n"
                + "- drop: it allows the player to drop a transported object in the current room. The object"
                + "should be written as a second word. Ex: drop ladder.\n"
                + "- climb: when the player is in the room with a ladder, he can climbed at any moment."
                + "Two scenarios can happen:\n 1) the player is in the right room and uses the ladder to"
                + "escape\n 2) the player is not in the right room and by climbing the ladder, he his"
                + "teleported to a random room.\n This command has to be followed by these two words:"
                + "climb ladder jump.\n Note that the ladder has to be dropped in the current room, before"
                + "it can be used.\n"
                + "- use: allows the player to use fixed objects (the alarm's button).";

        JOptionPane.showMessageDialog(new JFrame(), help, "Help command",
                JOptionPane.INFORMATION_MESSAGE, null);

        // appends all command words to output string.
        for (String commands : commandWords) {
            appendToOutputString(commands + "  ");
        }

        appendToOutputString("\n");
        return false;
    }
}
