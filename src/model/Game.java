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
        Room outside, theatre, pub, lab, office;

        /*
         * Create all the rooms
         * The first parameter is the description of the room and the second parameter is the path of the picture of the room.
         * If you want to have your own pictures, you should put them in the Resource Packages/images folder.
         */
        outside = new Room("outside the main entrance of the university", "/images/outside.gif");
        theatre = new Room("in a lecture theatre", "/images/castle.gif");
        pub = new Room("in the campus pub", "/images/courtyard.gif");
        lab = new Room("in a computing lab", "/images/stairs.gif");
        office = new Room("the computing admin office", "/images/dungeon.gif");

        // Link exits of romms together
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        // the player starts from room **outside**.
        player.setCurrentRoom(outside);
    }

    // Initialize the first room.
    private void setFirstOutput() {
        engine.appendToOutputString("Welcome to the World of Zuul!\n");
        engine.appendToOutputString("World of Zuul is a great adventure game.\n");
        engine.appendToOutputString("Type 'help' if you need help.\n");
    }
}
