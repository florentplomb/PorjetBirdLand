package model;

import controller.Command;
import controller.Parser;
import java.util.ArrayList;
import view.GameListener;

/**
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * This class is the main engine of the game. It keeps a list of all
 * GameListeners and notifies them each time the game state is modified. It also
 * evaluates and executes the commands that the parser returns.
 */
public class GameEngine implements Model {

    private String outputString;
    private boolean finished;
    private Parser parser;
    private Player player;
    private ArrayList<GameListener> gameListeners;

    //Constructor
    public GameEngine(Parser parser, Player player) {
        outputString = new String();
        finished = false;
        this.parser = parser;
        this.player = player;
        gameListeners = new ArrayList<GameListener>();
    }

    // Returns the current OutputString.     
    private void clearOutputString() {
        outputString = "";
    }

    // Returns the current OutputString.
    public void appendToOutputString(String myString) {
        outputString += myString;
    }

    // Returns the current OutputString.
    public String getOutputString() {
        return outputString;
    }

    // Returns the game state "finished". Tests if the game has ended.
    public boolean isFinished() {
        return finished;
    }

    // Returns the current player.
    public Player getPlayer() {
        return player;
    }

    // Adds a game listener to the list of listeners.
    public void addGameListener(GameListener gameListener) {
        gameListeners.add(gameListener);
        notifyGameListeners();
    }

    // Notifies all game listeners of game modifications.
    public void notifyGameListeners() {
        for (GameListener gl : gameListeners) {
            String imageName = getPlayer().getCurrentRoom().getImageName();
            gl.gameStateModified(imageName);
        }
    }

    /*
     * Given a command, execute the command. If this command ends the game,
     * finished is set to true.
     */
    public void interpretCommand(String commandLine) {
        clearOutputString();
        outputString += commandLine + "\n";

        Command command = parser.getCommand(commandLine);

        if (command == null) {
            appendToOutputString("I don't know what you mean...");
        } else {
            finished = command.execute(player);
            appendToOutputString(command.getOutputString());
        }
        notifyGameListeners();
    }
}
