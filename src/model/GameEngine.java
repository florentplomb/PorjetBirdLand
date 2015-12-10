package model;

import controller.Command;
import controller.DropCmd;
import controller.GoCmd;
import controller.Parser;
import controller.TakeCmd;
import controller.UseCmd;
import java.util.ArrayList;
import model.item.Alarm;
import model.item.Item;
import model.item.Transportable;
import view.GameListener;
import view.GameView;
import view.QuizzUserInterface;

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
    private Guardian guardian01;
    private GameView gv;

    //Constructor
    public GameEngine(Parser parser, Player player, Guardian guardian01) {
        outputString = new String();
        finished = false;
        this.parser = parser;
        this.player = player;
        this.guardian01 = guardian01;
        gameListeners = new ArrayList<GameListener>();
    }

    // Returns the current OutputString.     
    private void clearOutputString() {
        outputString = "";
    }

    public void setGm(GameView gv) {
        this.gv = gv;
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

    public void alarm() {
        gv.alarmeOn();
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
        //  System.out.println(command.getSecondWord());
        if (command == null) {
            appendToOutputString("I don't know what you mean...");
        } else {
            finished = command.execute(player);
            appendToOutputString(command.getOutputString());
            if (command instanceof UseCmd) {
                if (command.getSecondWord() != null) {
                    if (Alarm.getState()) {
                        gv.alarmeOn();
                    } else {
                        gv.alarmeOff();
                    }
                }

            }
            if(command instanceof GoCmd){
                guardian01.setNextRoom();
               // appendToOutputString("\n"+guardian01.getCurrentRoom().getId());
                if (guardian01.getCurrentRoom().getId().equals(player.getCurrentRoom().getId())) {
                    appendToOutputString("\n Guardian is HERE \n");
                    if (player.getItem("bananapeel") != null) {
                       interpretCommand("drop bananapeel");
                       appendToOutputString("You used the bananpeal to skip the guardian.. \n");
                       gv.removePlayerItem("H");
                       //player.removeItem("bananapeel");
                    }else{
                      gv.enable(false);
                     new QuizzUserInterface(this,player);
                    }     
                }
            }else if(command instanceof TakeCmd){
                gv.setPlayerItems(((TakeCmd)command).getLastTake());
            }
        }
        notifyGameListeners();
    }

    public void setGV(boolean b) {
        gv.enable(b);
    }
    
    public void InitItemView(){
        System.out.println("InitItemView");
        for (Transportable t : player.getAllItems()) {
            gv.setPlayerItems(t);
            System.out.println(t.getNAME());
        }
    }
    
    public GameView getGameView(){
        return gv;
    }
}
