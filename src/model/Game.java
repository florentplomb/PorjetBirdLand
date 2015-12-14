package model;

import communication.GameViewProxy;
import controller.GameParms;
import controller.Parser;
import iphone.IOSGameViewProxy;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.item.Alarm;
import model.item.Blanket;
import model.item.Ladder;
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
    private static ArrayList<Room> rooms;
    private Guardian guardian01;

    // Create the Game and initialize its internal map.     
    public Game(String playerName) {
        player = new Player(playerName);
        parser = new Parser();
        rooms = new ArrayList<Room>();
        createRooms();
        createGuardian();
        engine = new GameEngine(parser, player,guardian01);
        setFirstOutput();
      
      

        // GUI must be created last since it needs all above classes instances (engine, player, rooms) to display game.
        GameListener localView = new GameView(engine);
        engine.setGv((GameView) localView);
        engine.InitItemView();
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
        mainCell = new Room("Player's cell, the cell where the escape starts", "/images/mainCell.jpg","mainCell");
        cellEast1 = new Room("First east cell", "/images/cellule1.jpg","cellEast1");
        cellEast2 = new Room("Second east cell", "/images/cellule3.jpg","cellEast2");
        cellWest1 = new Room("First west cell", "/images/cellule2.jpg","cellEast3");
        cellWest2 = new Room("Second west cell", "/images/cellule4.jpg","cellWest2");
        mainCorridorBegin = new Room("Beginin of the main corridor, the prison backbone", "/images/mainCorridorBegin.jpg","mainCorridorBegin");
        mainCorridorMiddle = new Room("Middle of the main corridor" , "/images/mainCorridorMiddle.jpg","mainCorridorMiddle");
        endCorridor = new Room("The end of the corridor, almost outside", "/images/endCorridor.png","endCorridor");
        alarmRoom = new Room("A little enclosure outside, with the alarm button", "/images/alarmRoom.png","alarmRoom");
        outside = new Room("Outside, the place for escaping!", "/images/outside.png","outside");
        
        rooms.add(outside);
        rooms.add(alarmRoom);
        rooms.add(endCorridor);
        rooms.add(mainCorridorMiddle);
        rooms.add(mainCorridorMiddle);
        rooms.add(cellEast1);
        rooms.add(cellEast2);
        rooms.add(cellWest1);
        rooms.add(cellWest2);
        rooms.add(mainCell);
        
        

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
       
 
        mainCell.addItem(new Blanket ("blanket", "You have to use to escape ",5,true,"/images/blanket.jpg"));
        mainCell.addItem(new Ladder("ladder", "You can climb on ladder",8,true,"/images/ladder.jpg"));
        alarmRoom.addItem(Alarm.getInstance());
        
       
                
        // Set start room of guardian
        
        
    }
    
    private void createGuardian() {
      guardian01 = new Guardian("Joe", rooms.get(4));
    }

    // Initialize the first room.
    private void setFirstOutput() {
        engine.appendToOutputString("Welcome to PrisonBreak!\n");
        engine.appendToOutputString("PrisonBreak is a great adventure game.\n");
        engine.appendToOutputString("Type 'help' if you need help.\n");
    }
    

    public static Room getRandomRooms() {
        
        Random rdm = new Random();
        int listSize = rooms.size();
        Room rdmRoom = rooms.get(rdm.nextInt(listSize));
        return rdmRoom;
    }

    

}
