/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Random;

/**
 * Implementation of *Guardian* model.
 *
 * @author Matthieu Harbich
 */
public class Guardian extends Person {

    /**
     * Constructor
     *
     * @param name the guardian's name
     * @param currentRoom the room where the new guardian appears
     */
    public Guardian(String name, Room currentRoom) {
        super(name, currentRoom);
        this.getCurrentRoom();
    }

    /**
     * Allows the guardian to move in the serveralsrooms respecting the plan.
     */
    public void setNextRoom() {
        Room room = this.getCurrentRoom();
        HashMap<String, Room> exits = room.getExits();
        Random random = new Random();
        Object[] rooms = exits.values().toArray();
        int randomInt = random.nextInt(rooms.length);
        Room randomRoom = (Room) rooms[randomInt];
        this.setCurrentRoom(randomRoom);
    }

}
