package model;

import java.util.ArrayList;
import java.util.HashMap;
import model.item.Item;
/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. For each existing exit, the room stores a reference
 * to the neighboring room.
 *
 * @author Michael Kolling and David J. Barnes
 */
public class Room {

    private String description;
    // stores exits of this room.
    private HashMap<String, Room> exits;
    private String imageName;
    private HashMap<String, Item>  items = new HashMap<String, Item>();

    /*
     * Create a new room described "description" with a given image. Firstly, it
     * has no exits.
     */
    public Room(String description, String imageName) {
        this.description = description;
        this.imageName = imageName;
        exits = new HashMap<String, Room>();

    }

    // Defines an exit from this room.
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // Returns the description of the room (the one that was defined in the Constructor).
    public String getDescription() {
        return description;
    }

    // Returns room exits.
    public HashMap getExits() {
        return exits;
    }

    /*
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    // Return a string describing the room's image name
    public String getImageName() {
        return imageName;
    }
    
    public Item getItem(String it){
       if(items.containsKey(it)){
             return items.get(it);
       }
        return null;
    }
     public void addItem(Item item) {
        items.put(item.getNAME(), item);
    }
     public void removeItem(String it) {
        items.remove(it);
    }
    
}
