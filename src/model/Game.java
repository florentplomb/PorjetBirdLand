package model;

import communication.GameViewProxy;
import controller.Parser;
import iphone.IOSGameViewProxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.GameListener;
import view.GameView;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initializes all the others: it creates all rooms,
 * creates the parser and starts the game.
 *
 * Based on Michael Kolling's and David J. Barnes' original source codes.
 */
public class Game {

    private Parser parser;
    private Player player;
    private GameEngine engine;

    // Create the Game and initialize its internal map.     
    public Game() {
        player = new Player();
        parser = new Parser();
        engine = new GameEngine(parser, player);
        setFirstOutput();
        createRooms();

        // GUI must be created last since it needs all above classes instances (engine, player, rooms) to display game.
        GameListener localView = new GameView(engine);


//******************* CODE FOR IPHONE PART OF THE PROJECT ***********************************      
//******************* opens sockets for possible remote Java views      

        GameListener remoteJavaView = new GameViewProxy(engine);
        // calls the run method of GameViewProxy
        ((Thread) remoteJavaView).start(); 
        try {
            //opens sockets for possible remote iOS views
            GameListener remoteiOSView = new IOSGameViewProxy(engine, player);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

//*******************************************************************************************
    }

    // Create all the rooms and link their exits together.
    private void createRooms() {
        Room mainCell, cellEast1, cellEast2, cellWest1, cellWest2, mainCorridorBegin, mainCorridorMiddle, endCorridor, alarmRoom, outside;

        /*
         * Create all the rooms
         * The first parameter is the description of the room and the second parameter is the path of the picture of the room.
         * If you want to have your own pictures, you should put them in the Resource Packages/images folder.
         */
        mainCell = new Room("Player's cell, the cell where the escape starts", "/images/outside.gif");
        cellEast1 = new Room("First east cell", "/images/castle.gif");
        cellEast2 = new Room("Second east cell", "/images/courtyard.gif");
        cellWest1 = new Room("First west cell", "/images/stairs.gif");
        cellWest2 = new Room("Second west cell", "/images/dungeon.gif");
        mainCorridorBegin = new Room("Beginin of the main corridor, the prison backbone", "/images/dungeon.gif");
        mainCorridorMiddle = new Room("Middle of the main corridor" , "/images/prison.png");
        endCorridor = new Room("The end of the corridor, almost outside", "/images/dungeon.gif");
        alarmRoom = new Room("A little enclosure outside, with the alarm button", "/images/dungeon.gif");
        outside = new Room("Outside, the place for escaping!", "/images/dungeon.gif");

        // Link exits of romms together
        mainCell.setExit("north", mainCorridorBegin);

        mainCorridorBegin.setExit("north", mainCorridorMiddle);
        mainCorridorBegin.setExit("south", mainCell);
        mainCorridorBegin.setExit("east", cellEast1);
        mainCorridorBegin.setExit("west", cellWest1);
        
        mainCorridorMiddle.setExit("north", endCorridor);
        mainCorridorMiddle.setExit("south", mainCorridorBegin);
        mainCorridorMiddle.setExit("east", cellEast2);
        mainCorridorMiddle.setExit("west", cellWest2);
        
        cellEast1.setExit("west", mainCorridorBegin);
        cellEast2.setExit("west", mainCorridorMiddle);
        cellWest1.setExit("east", mainCorridorBegin);
        cellWest2.setExit("east", mainCorridorMiddle);
        
        endCorridor.setExit("south", mainCorridorMiddle);
        endCorridor.setExit("east", alarmRoom);
        endCorridor.setExit("west", outside);
        
        alarmRoom.setExit("west", endCorridor);
        
        outside.setExit("east", endCorridor);

    

        // the player starts from room **outside**.
        player.setCurrentRoom(mainCell);
    }

    // Initialize the first room.
    private void setFirstOutput() {
        engine.appendToOutputString("Welcome to the World of Zuul!\n");
        engine.appendToOutputString("World of Zuul is a great adventure game.\n");
        engine.appendToOutputString("Type 'help' if you need help.\n");
    }
}
