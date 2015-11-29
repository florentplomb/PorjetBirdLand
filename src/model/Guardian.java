/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Random;
/**
 *
 * @author Matthieu
 */
public class Guardian extends Person {

    public Guardian(String name, Room currentRoom) {
        super(name, currentRoom);
    }
   
    
    public void setNextRoom(){
        Room room = this.getCurrentRoom();
        HashMap<String, Room> exits = room.getExits();
        Random random = new Random();
        Object[] rooms = exits.values().toArray();
        int randomInt = random.nextInt(rooms.length);
        Room randomRoom = (Room)rooms[randomInt];
        this.setCurrentRoom(randomRoom);
    }
   

    
}
