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
 *
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

    /**
     * Return the person's name
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     *Return the list of person's items
     * @return list of item
     */

    public ArrayList<Item> getItem() {
        return this.items;
    }

    /**
     *Set the current room of person
     * 
     */

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Get the current room of person
     * @return the current room
     */

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     *
     * @return
     */

    public void setItem(Item item) {
        items.add(item);
    }

}
