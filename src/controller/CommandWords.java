package controller;

import java.util.HashMap;

/*
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game.
 *
 * This class holds a collection of all command words known to the game. It is
 * used to recognize commands as they are typed in.
 *
 * @author Michael Kolling and David J. Barnes
 */
public class CommandWords {

    private HashMap<String, Command> commands;

    public CommandWords() {
        commands = new HashMap<String, Command>();
        commands.put("go", new GoCmd());
        commands.put("quit", new QuitCmd());

        // The 'help' command is created differently since it needs to receive the list of command words
        HelpCmd helpCmd = new HelpCmd();
        commands.put("help", helpCmd);
        helpCmd.setCommandWords(commands.keySet());
    }

    /*
     * Given a command word, find and return the matching command object. Return
     * null if there is no command with this name.
     */
    public Command get(String word) {
        return commands.get(word);
    }
}
