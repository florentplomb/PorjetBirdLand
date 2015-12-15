package model;

import communication.GameViewProxy;
import controller.GameParams;
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
        engine = new GameEngine(parser, player, guardian01);
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
        Room mainCell, cellEast1, cellEast2, cellWest1, cellWest2, mainCorridorBegin, mainCorridorMiddle, outside, alarmRoom, outsideEscape;

        /*
         * Create all the rooms
         * The first parameter is the description of the room and the second parameter is the path of the picture of the room.
         * If you want to have your own pictures, you should put them in the Resource Packages/images folder.
         */

        mainCell = new Room("Player's cell, the cell where the escape starts", "/images/mainCell.jpg","/plan/mainCell.png","mainCell");
        cellEast1 = new Room("First  cell", "/images/cellule1.jpg","/plan/cell1.png","cellEast1");
        cellEast2 = new Room("third cell", "/images/cellule3.jpg","/plan/cell3.png","cellEast2");
        cellWest1 = new Room("second  cell", "/images/cellule2.jpg","/plan/cell2.png","cellEast3");
        cellWest2 = new Room("fourth  cell", "/images/cellule4.jpg","/plan/cell4.png","cellWest2");
        mainCorridorBegin = new Room("Beginin of the main corridor, the prison backbone", "/images/mainCorridorBegin.jpg","/plan/begin.png","mainCorridorBegin");
        mainCorridorMiddle = new Room("Middle of the main corridor" , "/images/mainCorridorMiddle.jpg","/plan/middle.png","mainCorridorMiddle");
        outside = new Room("outside!", "/images/outside.jpg","/plan/outside.png","outside");
        alarmRoom = new Room("A little enclosure outside, with the alarm button", "/images/alarme.jpg","/plan/alarm.png","alarmRoom");
        outsideEscape = new Room("In front of the liberty wall! ", "/images/murEscape.jpg","/plan/escape.png","libertyWall");
        
        rooms.add(outsideEscape);

        rooms.add(alarmRoom);
        rooms.add(outside);
        rooms.add(mainCorridorBegin);
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

        
        mainCorridorMiddle.setExit("north", outside);

        mainCorridorMiddle.setExit("south", mainCorridorBegin);
        mainCorridorMiddle.setExit("east", cellEast2);
        mainCorridorMiddle.setExit("west", cellWest2);

        cellEast1.setExit("west", mainCorridorBegin);
        cellEast2.setExit("west", mainCorridorMiddle);
        cellWest1.setExit("east", mainCorridorBegin);
        cellWest2.setExit("east", mainCorridorMiddle);

        
        outside.setExit("south", mainCorridorMiddle);
        outside.setExit("east", alarmRoom);
        outside.setExit("west", outsideEscape);
        
        alarmRoom.setExit("west", outside);
        
        outsideEscape.setExit("east", outside);
        
        
   

        // the player starts from room **outside**.
        player.setCurrentRoom(mainCell);
        
        Blanket b =  new Blanket("blanket", "You have to use to escape ", 5, true, "/images/blanket.jpg");
        Ladder l = new Ladder("ladder", "You can climb on ladder", 8, true, "/images/ladder.jpg");
        alarmRoom.addItem(Alarm.getInstance());
        
        if (GameParams.DemoGame) {
            cellEast1.addItem(b);
            cellWest2.addItem(l);
            
        }
        else{
            this.getRandomRooms().addItem(b);
            this.getRandomRooms().addItem(l);
        }

        // Set start room of guardian
    }

    private void createGuardian() {
        if (GameParams.DemoGame) {
           guardian01 = new Guardian("Joe", rooms.get(4)); 
        }
        else{
            guardian01 = new Guardian("Joe", getRandomRooms()); 
        }
        
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
