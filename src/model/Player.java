package model;

import java.util.ArrayList;

/**
 * This class represents players in the game. Each player has a current location
 * and also a list contains which rooms he where in before.
 */
public class Player {

    private Room currentRoom;
    private ArrayList<Room> previousRooms;

    //This is Constructor.
    public Player() {
        currentRoom = null;
        previousRooms = new ArrayList<Room>();
    }

    // Return the current room for this player.
    public Room getCurrentRoom() {
        return currentRoom;
    }

    //Set the current room for this player.     
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    // Returns the list of previous room that this player where in before.
    public ArrayList getPreviousRooms() {
        return previousRooms;
    }

    // Adds the list of previously visited rooms.
    public void addPreviousRoom(Room room) {
        previousRooms.add(room);
    }
}
