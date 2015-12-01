package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.item.BananaPeel;
import model.item.Item;
import model.item.Ladder;
import model.item.Transportable;

/**
 * This class represents players in the game. Each player has a current location
 * and also a list contains which rooms he where in before.
 */
public class Player {
    
    private int point;
    private Room currentRoom;
    private ArrayList<Room> previousRooms;
    private HashMap<String, Item> items;

    //This is Constructor.
    public Player() {
        currentRoom = null;
        previousRooms = new ArrayList<Room>();
        items = new HashMap<String, Item>();
        this.addItem(new BananaPeel("BananaPeel","BananaPeel",1,false,"/images/banana.jpg"));
        this.addItem(new Ladder("Ladder", "You can climb on ladder",5,true,"url"));
        this.point = 0;
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

    public void addItem(Item i) {
        items.put(i.getNAME().toLowerCase(), i);
    }

    public void removeItem(String itemName) {
        
        items.remove(itemName);
    }

    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    public Integer getWeightItems() {
        int weight = 0;
        
        for (Map.Entry element : items.entrySet()) {
            Transportable item = (Transportable) element.getValue();
            weight += item.getWEIGHT();
        }
        return weight;
    }
    
    public void addPoint(Integer point){
        this.point += point;
    }

    /**
     * @return the point
     */
    public int getPoint() {
        return point;
    }
}
