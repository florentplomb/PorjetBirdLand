package controller;

import java.util.Set;
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
        appendToOutputString("around at the university.\n");
        appendToOutputString("\n");
        appendToOutputString("Your command words are:\n");

        // appends all command words to output string.
        for (String commands : commandWords) {
            appendToOutputString(commands + "  ");
        }

        appendToOutputString("\n");
        return false;
    }
}
