package controller;

/*
 * This class is the main class of the "World of Zuul" application.
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and tries
 * to interpret the line as a two word command. It returns the command as an
 * object of class Command.
 *
 * The parser has a set of known command words. It checks user input against the
 * known commands, and if the input is not one of the known commands, it returns
 * a command object that is marked as an unknown command.
 *
 * @author Michael Kolling and David J. Barnes
 */
public class Parser {

    private CommandWords commands;  // holds all valid command words

    public Parser() {
        commands = new CommandWords();
    }

    public Command getCommand(String inputLine) {
        String word1 = null;
        String word2 = null;

        String[] words = inputLine.split(" ");

        if (words.length > 0) {
            // get first word
            word1 = words[0];
        }
        if (words.length > 1) {
            // get second word
            word2 = words[1];
        }
        // note: we just ignore the rest of the input line.

        Command command = commands.get(word1);
        if (command != null) {
            command.setSecondWord(word2);
        }
        return command;
    }
}
