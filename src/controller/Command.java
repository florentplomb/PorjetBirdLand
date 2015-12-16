package controller;

import model.Player;

/**
 * This class is an abstract superclass for all command classes in the game.
 * Each user command is inherited from this abstract class.
 *
 * Objects of class Command can have two optional argument words (a second and a
 * third word entered on the command line). If the command had only one word,
 * the second and the third words are *null*.
 *
 * Objects of class Command can have Output (in response of the execution of a
 * command).
 */
public abstract class Command {

    private String secondWord;
    private String thirdWord;
    private String outputString;

    /*
     * Create a command object. First second and third words must be supplied, but
     * either one, two (or the three of them) can be null. The command word should be null to
     * indicate that this was a command that is not recognized by this game.
     */
    public Command() {
        secondWord = null;
        thirdWord = null;
        outputString = new String();
    }

    /*
     * Return the second word of this command. If no second word was entered,
     * the result is null.
     */
    public String getSecondWord() {
        return secondWord;
    }

    /*
     * Return the third word of this command. If no third word was entered,
     * the result is null.
     */
    public String getThirdWord() {
        return thirdWord;
    }

    // Check whether a second word was entered for this command.
    public boolean hasSecondWord() {
        return secondWord != null;
    }

    // Check whether a third word was entered for this command.
    public boolean hasThirdWord() {
        return thirdWord != null;
    }

    /*
     * Define the second word of this command (the word entered after the
     * command word). Null indicates that there was no second word.
     */
    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    /*
     * Define the third word of this command (the word entered after the
     * command word and the second word). Null indicates that there was no third word.
     */
    public void setThirdWord(String thirdWord) {
        this.thirdWord = thirdWord;
    }

    // Clear the outputString
    public void clearOutputString() {
        outputString = "";
    }

    // Append String to the outputString
    public void appendToOutputString(String myString) {
        outputString += myString;
    }

    // Get field outputString
    public String getOutputString() {
        return outputString;
    }

    /*
     * Execute this command. A flag is returned indicating whether the game is
     * over as a result of this command.
     *
     * @return True, if game should exit; and false otherwise.
     */
    public abstract boolean execute(Player player);
}
