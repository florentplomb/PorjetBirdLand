/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



import java.util.ArrayList;
import model.item.Item;

/**
 * Implementation of the *Person* model
 * @author Bryan Cornelius
 */
public abstract class Person {

    private String name;
    private ArrayList<Item> items;
    private Room currentRoom;
    
    /**
     * 
     * @param name the person's name
     * @param currentRoom the room where the new person appears
     */

    public Person(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.items = new ArrayList<Item>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getItem() {
        return this.items;
    }
    
    public void setCurrentRoom(Room room){
        this.currentRoom = room; 
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setItem(Item item) {
        items.add(item);
    }
    
    
}
